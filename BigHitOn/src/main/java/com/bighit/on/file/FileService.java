package com.bighit.on.file;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

	public List<FileVO> doSelectListChLink(FileVO fileVO) {
		return fileDao.doSelectListChLink(fileVO);
	}

	public List<FileVO> doSelectListThrKey(FileVO fileVO) {
		return fileDao.doSelectListThrKey(fileVO);
	}

	/**
	 * 
	 * @param filePath (file URL)
	 * @param upPath   (S3 URL(key_name))
	 * @return flag
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public int doFileUpload(String key_name, MultipartFile multiFile) throws IllegalStateException, IOException {

		return fileDao.doFileUpload(key_name, multiFile);
	}

	/**
	 * 파일 다운로드
	 * 
	 * @param key_name
	 * @return pre-signed url
	 */
	public URL doFileDownload(String key_name) {

		return fileDao.doFileDownload(key_name);
	}

	
	/**
	 * doSaveDisk
	 * @param multipartFile
	 * @param keyName
	 * @throws IOException
	 */
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

	public MultipartFile doResizeProfile(MultipartFile multiFile) throws IOException {
		
		LOG.debug("-------------------------");
		LOG.debug("-doResizeProfile-");
		LOG.debug("-------------------------");
		
		int w;
		int h;
		
		File transferFile = new File(multiFile.getOriginalFilename());
		multiFile.transferTo(transferFile);
		
		Image img;
		img = ImageIO.read(transferFile);
		
		int originalWidth = img.getWidth(null);
		int originalHeight = img.getHeight(null);
		double ratio = (double)originalHeight / (double)originalWidth;
		
		if(originalWidth > originalHeight) {
			w = 350;
			h = (int) (350*ratio);
		}else if (originalWidth < originalHeight) {
			h = 350;
			w = (int) (350/ratio);
		}else {
			w = 350;
			h = 350;
		}
		
		Image imgResize = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		
		BufferedImage outImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = outImg.getGraphics();
		g.drawImage(imgResize, 0, 0, null);
		g.dispose();
		
		File file = new File("test_resize.jpg");
		try {
			ImageIO.write(outImg, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DiskFileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, "testYong.jpg",
				(int) file.length(), file.getParentFile());

		InputStream input = new FileInputStream(file);
		OutputStream os = fileItem.getOutputStream();
		IOUtils.copy(input, os);

		MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

		LOG.debug("-------------------------");
		LOG.debug("-End doResizeProfile-");
		LOG.debug("-------------------------");

		return multipartFile;
	}
	
	/**
	 * 이미지 thumbnail 크기로 조정
	 * @param multiFile
	 * @return MultipartFile
	 * @throws IOException
	 */
	public MultipartFile doResizeThumb(MultipartFile multiFile) throws IOException {

		LOG.debug("-------------------------");
		LOG.debug("-doResizeThumb-");
		LOG.debug("-------------------------");

		File transferFile = new File(multiFile.getOriginalFilename());
		multiFile.transferTo(transferFile);

		Image img;
		img = ImageIO.read(transferFile);

		int w = 60;
		int h = 60;

		Image imgResize = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		BufferedImage outImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = outImg.getGraphics();
		g.drawImage(imgResize, 0, 0, null);
		g.dispose();

		File file = new File("test_resize.jpg");
		try {
			ImageIO.write(outImg, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		DiskFileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, "testYong.jpg",
				(int) file.length(), file.getParentFile());

		InputStream input = new FileInputStream(file);
		OutputStream os = fileItem.getOutputStream();
		IOUtils.copy(input, os);

		MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

		LOG.debug("-------------------------");
		LOG.debug("-End doResizeThumb-");
		LOG.debug("-------------------------");

		return multipartFile;
	}

	/**
	 * getCalender
	 * @return 오늘 날짜 제공
	 */
	public String getCalender() {
		long time = System.currentTimeMillis();
		String formatType = "yy/MM/dd";

		SimpleDateFormat dayTime = new SimpleDateFormat(formatType);

		String nowTime = dayTime.format(new Date(time));

		return nowTime;
	}
	
	/**
	 * doMakeKeyName
	 * @param fileType
	 * @param fileName
	 * @return String keyName
	 */
	public String doMakeKeyName(String fileType, String fileName) {

		String calVal = getCalender();
		UUID uuid = UUID.randomUUID();
		String uid = uuid.toString();
		LOG.debug("uuid" + uid);

		String keyName = calVal + "/" + fileType + "/" + uid + "/" + fileName;

		return keyName;
	}

}
