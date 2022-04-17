package com.urs.bsi.model.vo;

import java.util.List;

public class DocumentRejectionVo {

	private String event;
	private String journeyTrackingNumber;
	private String journey;
	private String status;
	private List<RejectedReason> rejectReason;
	private boolean isSpreadComplete;

	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getJourneyTrackingNumber() {
		return journeyTrackingNumber;
	}
	public void setJourneyTrackingNumber(String journeyTrackingNumber) {
		this.journeyTrackingNumber = journeyTrackingNumber;
	}
	public String getJourney() {
		return journey;
	}
	public void setJourney(String journey) {
		this.journey = journey;
	}
	
	public List<RejectedReason> getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(List<RejectedReason> rejectReason) {
		this.rejectReason = rejectReason;
	}
	public boolean getIsSpreadComplete() {
		return isSpreadComplete;
	}
	public void setIsSpreadComplete(boolean isSpreadComplete) {
		this.isSpreadComplete = isSpreadComplete;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
