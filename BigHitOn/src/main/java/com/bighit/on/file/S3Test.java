package com.bighit.on.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

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

@Component
public class S3Test {
	private static void displayTextInputStream(InputStream input) throws IOException {
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println();
    }
	
    
    public static void main(String[] args) throws IOException {
        final String USAGE = "\n" +
                "To run this example, supply the name of an S3 bucket and a file to\n" +
                "upload to it.\n" +
                "\n" +
                "Ex: PutObject <bucketname> <filename>\n";
        AccessKey keys = new AccessKey();
    	
        String accessKey = keys.getACCESS_KEY();
        String secretKey = keys.getSECRET_KEY();
        S3Object fullObject = new S3Object();

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

      

        String bucket_name = "kghbucket";
        String file_path = "./src/main/java/com/bighit/on/file/Test.jpg";
        String key_name = "profileimg/"+Paths.get(file_path).getFileName().toString();
        
        System.out.format("Uploading %s to S3 bucket %s...\n", file_path, bucket_name);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_NORTHEAST_2).build();
        
        
        try {
        	// 업로드
            s3.putObject(bucket_name, key_name, new File(file_path));
            
            // 다운로드 시작
            System.out.println("Downloading an object");
            fullObject = s3.getObject(new GetObjectRequest(bucket_name, key_name));
            System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
            System.out.println("Content: ");

            S3ObjectInputStream s3is = fullObject.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File("./src/main/java/com/bighit/on/file/TestDownload.jpg"));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
            // 다운로드 끝
            
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("Done!");
    }
    
   

}