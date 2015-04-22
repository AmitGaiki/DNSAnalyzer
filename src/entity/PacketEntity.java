/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author amit
 */
public class PacketEntity {
	
	private Long timeStamp;
	private String transactionId;
	private String packetType;
	private int queryType;
	private boolean authResponse;
	private boolean recursionDesired;
	private boolean recursionAvailable;
	private int responseCode;
	private int questionCount;
	private int responseCount;
	private int authCount;
	private int additionalCount;
	private String questionName;
	private int questionType;
	private int questionClass;
	private byte[] payload;

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPacketType() {
		return packetType;
	}

	public void setPacketType(String packetType) {
		this.packetType = packetType;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public boolean isAuthResponse() {
		return authResponse;
	}

	public void setAuthResponse(boolean authResponse) {
		this.authResponse = authResponse;
	}

	public boolean isRecursionDesired() {
		return recursionDesired;
	}

	public void setRecursionDesired(boolean recursionDesired) {
		this.recursionDesired = recursionDesired;
	}

	public boolean isRecursionAvailable() {
		return recursionAvailable;
	}

	public void setRecursionAvailable(boolean recursionAvailable) {
		this.recursionAvailable = recursionAvailable;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public int getResponseCount() {
		return responseCount;
	}

	public void setResponseCount(int responseCount) {
		this.responseCount = responseCount;
	}

	public int getAuthCount() {
		return authCount;
	}

	public void setAuthCount(int authCount) {
		this.authCount = authCount;
	}

	public int getAdditionalCount() {
		return additionalCount;
	}

	public void setAdditionalCount(int additionalCount) {
		this.additionalCount = additionalCount;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public int getQuestionClass() {
		return questionClass;
	}

	public void setQuestionClass(int questionClass) {
		this.questionClass = questionClass;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}
	
	
}
