package com.bighit.on.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileDaoImpl fileDao;
	
	public int doInsert(FileVO fileVO) {
		return fileDao.doInsert(fileVO);
	}
	
	public int doDelete(FileVO fileVO) {
		return fileDao.doDelete(fileVO);
	}
	
	public int doUpdate(FileVO fileVO) {
		return fileDao.doUpdate(fileVO);
	}
	
	public FileVO doSelectOne(FileVO fileVO) {
		return (FileVO) fileDao.doSelectOne(fileVO);
	}
	
	public List<FileVO> doSelectListChLink(FileVO fileVO){
		return fileDao.doSelectListChLink(fileVO);
	}
	
	public List<FileVO> doSelectListThrKey(FileVO fileVO){
		return fileDao.doSelectListThrKey(fileVO);
	}
	
	/**
	 * 
	 * @param filePath (file URL)
	 * @param upPath (S3 URL(key_name))
	 * @return flag
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public int doFileUpload(String key_name, MultipartFile multiFile) throws IllegalStateException, IOException {
		
		return fileDao.doFileUpload(key_name, multiFile);
	}
	
	/**
	 * 파일 다운로드
	 * @param key_name
	 * @return pre-signed url
	 */
	public URL doFileDownload(String key_name){
		
		return fileDao.doFileDownload(key_name);
	}
	
	/**
	 * 폴더 만드는 함수
	 */
	public void doMakeDir() {
		String path = "C:\\BIGHIT_thumbnail"; // 폴더 경로
		File Folder = new File(path);

		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}

	}
	
	public void doSaveDisk(MultipartFile multipartFile, String keyName) throws IOException {
		LOG.debug("-------------------------");
		LOG.debug("-doSaveDisk-");
		LOG.debug("-------------------------");
		File file = new File(keyName);
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(multipartFile.getBytes());
		fos.close();
	}
	
}
