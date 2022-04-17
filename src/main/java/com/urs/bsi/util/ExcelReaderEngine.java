package com.urs.bsi.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelReaderEngine {
	
	private XSSFWorkbook xssfWorkbook;
	
	/**
	 * Generic method to read excel data and map to list of objects
	 * @param <T> - Type reference
	 * @param inputStream - inputStream object
	 * @param sheetIndex -  sheet to read
	 * @param excelToEntityMap - Map of excel index to object property
	 * @param clazz - entity class to map excel data
	 * @param rowStart - start index, defaults to 1 
	 * @param rowEnd - end index, defaults to number of rows in worksheet 
	 * @return List of entity class
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public <T> List<T> readExcelData(OPCPackage pkg, int sheetIndex, Map<Integer, String> excelToEntityMap,
			Class<T> clazz, Integer rowStart, Integer rowEnd, Integer colStart) throws InstantiationException, IllegalAccessException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(pkg);
		setXssfWorkbook(workbook);
		
		return readExistingExcelFileData(pkg, sheetIndex, excelToEntityMap, clazz, rowStart, rowEnd, colStart);
	}
	
	public <T> List<T> readExistingExcelFileData(OPCPackage pkg,int sheetIndex, Map<Integer, String> excelToEntityMap,
			Class<T> clazz, Integer rowStart, Integer rowEnd, Integer colStart) throws InstantiationException, IllegalAccessException, IOException {
		List<T> retList = new ArrayList<T>();
		XSSFWorkbook workbook = getXssfWorkbook();
		XSSFSheet worksheet = workbook.getSheetAt(sheetIndex);
		rowStart = (null == rowStart ? 1 : rowStart);
		rowEnd = (null == rowEnd ? worksheet.getPhysicalNumberOfRows() : rowEnd);
		colStart = (null == colStart ? 0 : colStart);
		
		for (int i = rowStart; i < rowEnd; i++) {
			T obj = clazz.newInstance();
		    XSSFRow row = worksheet.getRow(i);
		    if (null != row) {
			    for (int j=colStart; j<=excelToEntityMap.size(); j++) {
			    	Cell cell = row.getCell(j);
			    	if (null != cell) {
			    		switch (cell.getCellTypeEnum()) {
		                    case STRING:
		                        BsiFileUtility.setPropertyValue(obj, excelToEntityMap.get(j), cell.getStringCellValue().trim());
		                        break;
		                    case NUMERIC:
		        	            if (DateUtil.isCellDateFormatted(cell)) {
		        	            	BsiFileUtility.setPropertyValue(obj, excelToEntityMap.get(j), cell.getDateCellValue() != null ? new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(cell.getDateCellValue()): "");
		        	            } else {
		        	            	BsiFileUtility.setPropertyValue(obj, excelToEntityMap.get(j), new BigDecimal(cell.getNumericCellValue()).toPlainString().trim());
		        	            }
		        	            break;
		                    case _NONE:
		                    	break;
		                    case BLANK:
		                    	break;
		                    default:
		                    	break;
			    		}
	                }
			    }
			    retList.add(obj);
		    }
		}
		pkg.close();
		return CollectionUtils.isEmpty(retList) ? null : retList;
	}
	
	public Integer[] countParticularsOfAccount(int sheetIndex, int rowNumber) throws IllegalAccessException, IOException {
		XSSFWorkbook workbook = getXssfWorkbook();
		XSSFSheet worksheet = workbook.getSheetAt(sheetIndex);
		XSSFRow row =  worksheet.getRow(rowNumber);
		TreeMap<Integer, XSSFCell> mapObj = BsiFileUtility.getPropertyValue(row, "_cells");
		Set<Entry<Integer, XSSFCell>> entrySet = mapObj.entrySet();
		List<Entry<Integer, XSSFCell>> entryList = new ArrayList<Map.Entry<Integer,XSSFCell>>();
		entryList.addAll(entrySet);
		List<Entry<String,Integer>> accountSizeList = new ArrayList<Map.Entry<String,Integer>>();
		int counter = 0;
		for (int i=1; i<entryList.size(); i++ ) {
			counter++;
			Entry<Integer, XSSFCell> entry = entryList.get(i);
			if(null != entry.getValue() && StringUtils.isNotEmpty(entry.getValue().getStringCellValue())) {
				if (accountSizeList.size() > 0) {
					Entry<String,Integer> entryData = accountSizeList.get(accountSizeList.size()-1);
					if (accountSizeList.size()==1) {
						entryData.setValue(counter-1);
					} else {
						entryData.setValue(counter);
					}
					counter = 0;
				}
				accountSizeList.add(new AbstractMap.SimpleEntry(entry.getValue().getStringCellValue(), counter));
			}
			if (i == entryList.size()-1) {
				Entry<String,Integer> entryData = accountSizeList.get(accountSizeList.size()-1);
				entryData.setValue(counter+1);
			}
		}
		Integer[] retVal = new Integer[accountSizeList.size()];
		for (int i=0; i<accountSizeList.size(); i++) {
			retVal[i] = accountSizeList.get(i).getValue();
		}
		return retVal;
	}

	public XSSFWorkbook getXssfWorkbook() {
		return xssfWorkbook;
	}
	public void setXssfWorkbook(XSSFWorkbook xssfWorkbook) {
		this.xssfWorkbook = xssfWorkbook;
	}
}
