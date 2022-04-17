package com.urs.bsi.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class BsiFileUtility {
	
	private static List<SimpleDateFormat>
		dateFormats = new ArrayList<SimpleDateFormat>() {{
		add(new SimpleDateFormat("dd/MM/yyyy"));
		add(new SimpleDateFormat("dd-MM-yyyy"));
		add(new SimpleDateFormat("yyyy-MM-dd"));
		add(new SimpleDateFormat("MM/dd/yyyy"));
		add(new SimpleDateFormat("dd/MM/yyyy"));
    }};
	private static List<SimpleDateFormat>
		dateTimeFormats = new ArrayList<SimpleDateFormat>() {{
		add(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"));
		add(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"));
		add(new SimpleDateFormat("MM/dd/yyyy HH:MM:SS"));
	}};

	
	private static void printCellValue(Cell cell) {
	    switch (cell.getCellTypeEnum()) {
	        case BOOLEAN:
	            System.out.print(cell.getBooleanCellValue());
	            break;
	        case STRING:
	            System.out.print(cell.getRichStringCellValue().getString());
	            break;
	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	                System.out.print(cell.getDateCellValue());
	            } else {
	                System.out.print(cell.getNumericCellValue());
	            }
	            break;
	        case FORMULA:
	            System.out.print(cell.getCellFormula());
	            break;
	        case BLANK:
	            System.out.print("");
	            break;
	        default:
	            System.out.print("");
	    }

	    System.out.print("\t");
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getPropertyValue(Object object, String fieldName) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return (T) field.get(object);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static boolean setPropertyValue(Object object, String fieldName, Object fieldValue) {
		try {
			if(object==null || object.getClass()==null || null==fieldName) return false;
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(object, fieldValue);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	public static BigDecimal convertToBigDecimal(String numberString) {
		if (StringUtils.isEmpty(numberString)) {
			return null;
		}
		try { 
            return new BigDecimal(numberString); 
        }  
        catch (NumberFormatException e) {
        	e.printStackTrace();
        }
		return null; 
	}
	
	public static Integer convertToInteger(String numberString) {
		if (StringUtils.isEmpty(numberString)) {
			return null;
		}
		try { 
            return new Integer(numberString); 
        }  
        catch (NumberFormatException e) {
        	e.printStackTrace();
        }
		return null; 
	}

	/**
	 * Convert String with various formats into java.util.Date
	 * 
	 * @param input
	 *            Date as a string
	 * @return java.util.Date object if input string is parsed 
	 * 			successfully else returns null
	 */
	public static Date convertToDate(String input) {
		Date date = null;
		if(null == input) {
			return null;
		}
		for (SimpleDateFormat format : dateFormats) {
			try {
				format.setLenient(false);
				date = format.parse(input);
			} catch (ParseException e) {
				//... try other formats
			}
			if (date != null) {
				break;
			}
		}
		return date;
	}
	
	/**
	 * Convert String with various formats into java.util.Date
	 * 
	 * @param input
	 *            Date as a string
	 * @return java.util.Date object if input string is parsed 
	 * 			successfully else returns null
	 */
	public static Timestamp convertToTimestamp(String input) {
		Timestamp date = null;
		if(null == input) {
			return null;
		}
		for (SimpleDateFormat format : dateTimeFormats) {
			try {
				format.setLenient(false);
				date = new Timestamp(format.parse(input).getTime());
			} catch (ParseException e) {
				//... try other formats
			}
			if (date != null) {
				break;
			}
		}
		return date;
	}
	
	public static Timestamp convertToTimestampForHistorical(String input) {
		Timestamp date = null;
		if(null == input) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				format.setLenient(false);
				date = new Timestamp(format.parse(input).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return date;
	}
	
	public static String getUuidBatchId() {
	    UUID uuid = UUID.randomUUID();
	    return uuid.toString();
	}
	
//	public static String encrypt(String value) {
//	    try {
//	    	KeyGenerator kgen = KeyGenerator.getInstance("AES");
//	    	SecretKey skey = kgen.generateKey();
//	        IvParameterSpec iv = new IvParameterSpec(ApplicationConstants.URS_ENCRPTION_DECRYPTION_INT_VEC.getBytes("UTF-8"));
//	        SecretKeySpec skeySpec = new SecretKeySpec(ApplicationConstants.URS_ENCRPTION_DECRYPTION_KEY.getBytes("UTF-8"), "AES");
//	 
//	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//	 
//	        byte[] encrypted = cipher.doFinal(value.getBytes());
//	        return Base64.getEncoder().encode(encrypted);
//	    } catch (Exception ex) {
//	        ex.printStackTrace();
//	    }
//	    return null;
//	}
	
	
	
	private static final String key = "a6bcv1fQchVxZ!N4Wu2Kl51yS40mmmZ0";
	private static final String initVector = "AJf3QItKM7+Lkh/BZT2xNg==";

	
	public static String decrypt(String encrypted) {
	   try {
	       IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(initVector));
	       SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

	       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	       cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	       byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

	       return new String(original);
	   } catch (Exception ex) {
	       ex.printStackTrace();
	   }

	   return null;
	}
	
	
	public static String getCurrentTimeMMDDYYYYHHMMSS() {
		Instant now = Instant.now();
		ZonedDateTime zdt = now.atZone(ZoneId.of("UTC+00:00"));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		return dtf.format(zdt);
	}
	
	public static String getCurrentTimeYYYYMMDDHHMMSS() {
		Instant now = Instant.now();
		ZonedDateTime zdt = now.atZone(ZoneId.of("UTC+00:00"));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		return dtf.format(zdt);
	}
	
	public static String getCurrentMSTTimeMMDDYYYYHHMMSS() {
		Instant now = Instant.now();
		ZonedDateTime zdt = now.atZone(ZoneId.of("US/Mountain"));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		return dtf.format(zdt);
	}
	
	

	public static void main(String[] s) {
//		String enc = encrypt("VENETIAN NAILS SPA PLANTATION");
//		System.out.println(enc);
//		System.out.println(decrypt(enc));
//		System.out.println(getCurrentMSTTimeMMDDYYYYHHMMSS());
		double d = 3.12d;
		
	}
	
	private static String formatDateToMMDDYYYY(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(date);
	}
}
