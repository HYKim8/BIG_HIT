package com.bighit.on.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
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
	
	public int doFileUpload(String filePath, String upPath) {
		int flag = 0;
		String accessKey = ack.getACCESS_KEY();
		String secretKey = ack.getSECRET_KEY();
		
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		
		String bucket_name = "kghbucket";
        String key_name = upPath;
		
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_NORTHEAST_2).build();
        
        try {
        	s3.putObject(bucket_name, key_name, new File(filePath));
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
	
	public File doFileDownload(String upPath, String downloadPath) throws IOException {
		int flag = 0;
		String bucket_name = "kghbucket";
		String accessKey = ack.getACCESS_KEY();
		String secretKey = ack.getSECRET_KEY();
		
		S3Object downloadFile = new S3Object();
		
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_NORTHEAST_2).build();
		
		downloadFile = s3.getObject(new GetObjectRequest(bucket_name, upPath));
        System.out.println("Content-Type: " + downloadFile.getObjectMetadata().getContentType());
		
        S3ObjectInputStream s3is = downloadFile.getObjectContent();
        FileOutputStream fos = new FileOutputStream(new File("./src/main/java/com/bighit/on/file/TestDownload.jpg"));
        byte[] read_buf = new byte[1024];
        int read_len = 0;
        while ((read_len = s3is.read(read_buf)) > 0) {
            fos.write(read_buf, 0, read_len);
        }
        s3is.close();
        fos.close();
        
        return (new File("./src/main/java/com/bighit/on/file/TestDownload.jpg"));
        
	}
	
	
}

