package com.bighit.on.file;

import com.bighit.on.cmn.DTO;

public class FileVO extends DTO {

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
	String regId;

	/**
	 * 작성 시간
	 */
	String regDt;

	/**
	 * 파일 아이디
	 */
	int fileId;

	/**
	 * 채널 링크
	 */
	String chLink;

	public FileVO() {
		super();
	}

	public FileVO(String fileName, String thrKey, String fileUrl, String regId, String regDt, int fileId,
			String chLink) {
		super();
		this.fileName = fileName;
		this.thrKey = thrKey;
		this.fileUrl = fileUrl;
		this.regId = regId;
		this.regDt = regDt;
		this.fileId = fileId;
		this.chLink = chLink;
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
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getChLink() {
		return chLink;
	}

	public void setChLink(String chLink) {
		this.chLink = chLink;
	}

	@Override
	public String toString() {
		return "FileVO [fileName=" + fileName + ", thrKey=" + thrKey + ", fileUrl=" + fileUrl + ", regId=" + regId
				+ ", regDt=" + regDt + ", fileId=" + fileId + ", chLink=" + chLink + "]";
	}

}
