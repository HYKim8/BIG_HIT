package com.bighit.on.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.bighit.on.cmn.AccessKey;
import com.bighit.on.reminder.ReminderDaoImpl;

public class S3Util {

	final static Logger LOG = LoggerFactory.getLogger(S3Util.class);
	AccessKey accessKey = new AccessKey();
	
	private String acKey = accessKey.getACCESS_KEY();
	private String scKey = accessKey.getSECRET_KEY();
	
	private AmazonS3 conn;
	
	public S3Util() {
		AWSCredentials credentials = new BasicAWSCredentials(acKey, scKey);
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTP);
		this.conn = new AmazonS3Client(credentials, clientConfig);
		conn.setEndpoint("s3.ap-northeast-2.amazonaws.com");
	}
	
	// 버킷 리스트를 가져오는 메서드
	public List<Bucket> getBucketList(){
		return conn.listBuckets();
	}
	
	// 버킷을 생성하는 메서드
	public Bucket createBucket(String bucketName) {
		return conn.createBucket(bucketName);
	}
	
	// 폴더를 생성하는 메서드(폴더는 파일명 뒤에 "/"를 붙여야 한다.)
	public void createFolder(String bucketName, String folderName) {
		conn.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
	}
	
	// 파일 업로드
	public void fileUpload(String bucketName, String fileName, byte[] fileData) {
		// 파일 구별자를 '/'로 설정(\ -> /) 이게 기존에 / 여도 넘어오면서 \로 변환
		String filePath = (fileName).replace(File.separatorChar, '/');
		ObjectMetadata metaData = new ObjectMetadata();
		
		// 메타 데이터 설정 --> 기존 128kB -> 파일 크기만큼 버퍼 설정
		metaData.setContentLength(fileData.length);
		// 파일 넣음
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData);
		
		conn.putObject(bucketName, filePath, byteArrayInputStream, metaData);
		
		
	}
	
	// 파일 삭제
	public void fileDelete(String bucketName, String fileName) {
		String imgName = (fileName).replace(File.separatorChar, '/');
		conn.deleteObject(bucketName, imgName);
		LOG.debug(imgName + "을(를) 삭제 성공");
	}
	
	// 파일 URL
	public String getFileURL(String bucketName, String fileName) {
		LOG.debug("넘어오는 파일명 : " + fileName);
		String imgName = (fileName).replace(File.separatorChar, '/');
		return conn.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, imgName)).toString();
	}
}



















