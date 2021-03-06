package com.bighit.on.test;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.bighit.on.file.FileDaoImpl;
import com.bighit.on.file.FileService;
import com.bighit.on.file.FileVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class JUnitTestFile {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired // 테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
	WebApplicationContext context;

	@Autowired
	FileDaoImpl fileDao;
	
	@Autowired
	FileService	fileService;

	FileVO fileVO01;
	FileVO fileVO02;
	FileVO fileVO03;

	@Before
	public void setUp() throws Exception {

		fileVO01 = new FileVO();
		fileVO02 = new FileVO();
		fileVO03 = new FileVO();

		fileVO01.setFileName("Test01");
		fileVO01.setThrKey("1");
		fileVO01.setFileUrl("Hello01.com");
		fileVO01.setRegId("KIM");
		fileVO01.setChLink("ChLink01");

		fileVO02.setFileName("Test02");
		fileVO02.setThrKey("1");
		fileVO02.setFileUrl("Hello02.com");
		fileVO02.setRegId("KIM");
		fileVO02.setChLink("ChLink02");

		fileVO03.setFileName("Test03");
		fileVO03.setThrKey("1");
		fileVO03.setFileUrl("Hello03.com");
		fileVO03.setRegId("KIM");
		fileVO03.setChLink("ChLink03");

		LOG.debug("---------------------------");
		LOG.debug("-In setUp Param-" + fileVO01);
		LOG.debug("-In setUp Param-" + fileVO02);
		LOG.debug("-In setUp Param-" + fileVO03);
		LOG.debug("---------------------------");

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void sth() {
		String str = "{}{}";
		System.out.println(str);
	}
	
	@Test
	@Ignore
	public void makeString() {
		String targetString = "https://kghbucket.s3.ap-northeast-2.amazonaws.com/profileImg/KIM_profile/029cb6f9-e07d-43b9-a738-47305a48544a_profile.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20201116T074840Z&X-Amz-SignedHeaders=host&X-Amz-Expires=259200&X-Amz-Credential=AKIA3I6CC67EHGOLPYFY%2F20201116%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Signature=ccc48804b6800476d7ed083748948983a25181c3b38c96d7ab07ee023762fd25";
		LOG.debug("targetString : " + targetString);
		
		
		String[] target2 = targetString.split("\\?")[0].split("/");
		
		String target = target2[3] + "/" + target2[4] + "/" + target2[5];
		LOG.debug("target : " + target);
		
		
	}
	
	@Test
	@Ignore
	public void file() throws IOException {
		fileDao.doFileDownload("profileimg/testimg.png");
	}
	
	@Test
	@Ignore
	public void serviceTest() {
		fileService.doInsert(fileVO01);
		fileService.doInsert(fileVO02);
		fileService.doInsert(fileVO03);

		fileVO01 = fileService.doSelectListThrKey(fileVO01).get(0);
		fileVO02 = fileService.doSelectListThrKey(fileVO02).get(1);
		fileVO03 = fileService.doSelectListThrKey(fileVO03).get(2);

		fileService.doSelectListChLink(fileVO01);
		fileService.doSelectListChLink(fileVO02);
		fileService.doSelectListChLink(fileVO03);

		fileVO01.setFileName(fileVO01.getFileName() + "Test");
		fileVO02.setFileName(fileVO02.getFileName() + "Test");
		fileVO03.setFileName(fileVO03.getFileName() + "Test");

		fileService.doUpdate(fileVO01);
		fileService.doUpdate(fileVO02);
		fileService.doUpdate(fileVO03);

		fileService.doSelectOne(fileVO01);
		fileService.doSelectOne(fileVO02);
		fileService.doSelectOne(fileVO03);

		fileService.doDelete(fileVO01);
		fileService.doDelete(fileVO02);
		fileService.doDelete(fileVO03);
	}
	
	
	
	@Test
	@Ignore
	public void mkDir() {
		String path = "./src/main/webapp/resources/thumbnail"; //폴더 경로
		File Folder = new File(path);

		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
	}
	
	@Test
	@Ignore
	public void daoTest() {

		fileDao.doInsert(fileVO01);
		fileDao.doInsert(fileVO02);
		fileDao.doInsert(fileVO03);

		fileVO01 = fileDao.doSelectListThrKey(fileVO01).get(0);
		fileVO02 = fileDao.doSelectListThrKey(fileVO02).get(1);
		fileVO03 = fileDao.doSelectListThrKey(fileVO03).get(2);

		fileDao.doSelectListChLink(fileVO01);
		fileDao.doSelectListChLink(fileVO02);
		fileDao.doSelectListChLink(fileVO03);

		fileVO01.setFileName(fileVO01.getFileName() + "Test");
		fileVO02.setFileName(fileVO02.getFileName() + "Test");
		fileVO03.setFileName(fileVO03.getFileName() + "Test");

		fileDao.doUpdate(fileVO01);
		fileDao.doUpdate(fileVO02);
		fileDao.doUpdate(fileVO03);

		fileDao.doSelectOne(fileVO01);
		fileDao.doSelectOne(fileVO02);
		fileDao.doSelectOne(fileVO03);

		fileDao.doDelete(fileVO01);
		fileDao.doDelete(fileVO02);
		fileDao.doDelete(fileVO03);
	}

}
