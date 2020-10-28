package com.bighit.on.file;

import com.bighit.on.cmn.DTO;

public class FileVO extends DTO{

	/**
	 * 파일명
	 */
	String fileName;

	/**
	 * 쓰레드키
	 */
	String thrKey;

	/**
	 * 파일url
	 */
	String fileUrl;

	/**
	 * 작성자
	 */
	String RegId;

	/**
	 * 작성 시간
	 */
	String RegDt;

	
	public FileVO() {
		super();
	}

	public FileVO(String fileName, String thrKey, String fileUrl, String regId, String regDt) {
		super();
		this.fileName = fileName;
		this.thrKey = thrKey;
		this.fileUrl = fileUrl;
		RegId = regId;
		RegDt = regDt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getThrKey() {
		return thrKey;
	}

	public void setThrKey(String thrKey) {
		this.thrKey = thrKey;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getRegId() {
		return RegId;
	}

	public void setRegId(String regId) {
		RegId = regId;
	}

	public String getRegDt() {
		return RegDt;
	}

	public void setRegDt(String regDt) {
		RegDt = regDt;
	}

	@Override
	public String toString() {
		return "FileVO [fileName=" + fileName + ", thrKey=" + thrKey + ", fileUrl=" + fileUrl + ", RegId=" + RegId
				+ ", RegDt=" + RegDt + "]";
	}

}
