package com.bighit.on.file;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.bighit.on.cmn.AccessKey;
import com.bighit.on.reminder.ReminderDaoImpl;

@Repository("FileDaoImpl")
public class FileDaoImpl {
	final static Logger LOG = LoggerFactory.getLogger(ReminderDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	AccessKey ack;
	
	private final String NAMESPACE = "com.bighit.on.file.";
	
	public FileDaoImpl() {
		super();
	}
	
	public int doInsert(FileVO fileVO) {
		LOG.debug("=======================");
		LOG.debug("====doInsert====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doInsert";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		return sqlSessionTemplate.insert(statement, fileVO);
	}
	
	public int doDelete(FileVO fileVO) {
		LOG.debug("=======================");
		LOG.debug("====doDelete====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doDelete";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		return sqlSessionTemplate.delete(statement, fileVO);
	}

	public int doUpdate(FileVO fileVO) {
		LOG.debug("=======================");
		LOG.debug("====doUpdate====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doUpdate";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		return sqlSessionTemplate.update(statement, fileVO);
	}

	public FileVO doSelectOne(FileVO fileVO) {
		LOG.debug("=======================");
		LOG.debug("====doSelectOne====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doSelectOne";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		FileVO outVO = sqlSessionTemplate.selectOne(statement, fileVO);
		LOG.debug("====outVO====" + outVO);
		
		return outVO;
	}
	
	public List<FileVO> doSelectListThrKey(FileVO fileVO){
		LOG.debug("=======================");
		LOG.debug("====doSelectListThrKey====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doSelectListThrKey";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		List<FileVO> outList = sqlSessionTemplate.selectList(statement, fileVO);
		
		for(FileVO vo : outList) {
			LOG.debug("====outVO====" + vo);
		}
		
		return outList;
	}
	
	public List<FileVO> doSelectListChLink(FileVO fileVO){
		LOG.debug("=======================");
		LOG.debug("====doSelectListChLink====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doSelectListChLink";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		List<FileVO> outList = sqlSessionTemplate.selectList(statement, fileVO);
		
		for(FileVO vo : outList) {
			LOG.debug("====outVO====" + vo);
		}
		
		return outList;
	}
	
	public int doFileUpload(String key_name, MultipartFile multiFile) throws IllegalStateException, IOException {
		int flag = 0;
		String accessKey = ack.getACCESS_KEY();
		String secretKey = ack.getSECRET_KEY();
		
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		File transferFile = new File(multiFile.getOriginalFilename());;
		multiFile.transferTo(transferFile);
		
		String bucket_name = "kghbucket";
		
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_NORTHEAST_2).build();
        
        try {
        	s3.putObject(bucket_name, key_name, transferFile);
        	flag = 1;
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			return flag;
		}
        
        LOG.debug("=====================");
        LOG.debug("====end uploading====");
        LOG.debug("=====================");
        
        return flag;
	}
	
	public URL doFileDownload(String keyName){
		String bucket_name = "kghbucket";
		String accessKey = ack.getACCESS_KEY();
		String secretKey = ack.getSECRET_KEY();
		
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_NORTHEAST_2).build();

        // Set the presigned URL to expire after one hour.
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);

        // Generate the presigned URL.
        System.out.println("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket_name, keyName)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);

        LOG.debug("Pre-Signed URL : " + url.toString());
		
        return url;
	}
	
	
}

