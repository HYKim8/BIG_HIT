package com.bighit.on.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

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
	
}
