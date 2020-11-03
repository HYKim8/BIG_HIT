package com.bighit.on.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Repository;

@Repository
public class FileExtractor {
	public static byte[] extractBytes(String imageName) throws IOException {
		File imgPath = new File(imageName);
		FileInputStream fis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		fis = new FileInputStream(imgPath);
		
		int len = 0;
		byte[] buf = new byte[1024];
		
		while((len = fis.read(buf)) != -1) {
			baos.write(buf,0,len);
		}
		byte[] fileArray = baos.toByteArray();
		
		return fileArray;
		
	}
	
	public static byte[] encodingBase64(byte[] targetBytes) {
		Encoder encoder = Base64.getEncoder();
		return encoder.encode(targetBytes);
	}
}
