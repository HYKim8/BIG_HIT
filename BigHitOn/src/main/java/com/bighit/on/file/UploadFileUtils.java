package com.bighit.on.file;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadFileUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(String uploadPath, String originalName, byte[] byteData) {
		S3Util s3 = new S3Util();
		String bucketName = "kghbucket";
		
		// uid 생성
		UUID uid = UUID.randomUUID();
		
		String savedName = "/" + uid.toString() + "_" + originalName;
		
		LOG.debug("업로드 경로 : " + uploadPath);
		
		String savedPath = makePath(uploadPath);
		
		String uploadedFileName = null;
		
		uploadedFileName = (savedPath + savedName).replace(File.separatorChar, '/');
		
		LOG.debug(uploadedFileName);
		
		return uploadedFileName;
		
	}
	
	private static String makePath(String uploadPath) {
		
		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);

		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);

		LOG.debug(datePath);

		return datePath;
		
	}
	
	private static void makeDir(String uploadPath, String... paths) {

		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		for (String path : paths) {

			File dirPath = new File(uploadPath + path);

			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	
}
