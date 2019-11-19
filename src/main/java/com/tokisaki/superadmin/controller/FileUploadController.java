package com.tokisaki.superadmin.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tokisaki.superadmin.common.CommonUtil;
import com.tokisaki.superadmin.domain.Attachment;
import com.tokisaki.superadmin.enums.FileTypeEnum;
import com.tokisaki.superadmin.repository.UserGroupRepository;
import com.tokisaki.superadmin.service.FileUploadService;


@RestController
@RequestMapping("/api/v1/fileupload")
public class FileUploadController {
	private static final Logger logger=LoggerFactory.getLogger(FileUploadController.class);
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private UserGroupRepository userGroupRepository;

	public FileUploadController(UserGroupRepository userGroupRepository) {
		this.userGroupRepository = userGroupRepository;
	}

	 @PostMapping(value = "/cosUpload")
	    public  ResponseEntity<Object>  upload(@RequestParam(value = "file") MultipartFile file, @RequestParam("fileType") FileTypeEnum fileType){
		 String newFileName = UUID.randomUUID().toString();
		 File localFile = null;
		 String extName=CommonUtil.ext(file.getOriginalFilename());
		 
		 try {
	            localFile = File.createTempFile("temp",null);
	            file.transferTo(localFile);
	            
	            Attachment attachment=fileUploadService.fileUpload(newFileName, localFile,extName,fileType);
	            return ok(attachment);
		 }catch (Exception e){
			 logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage()); 
		 }
		
	}

	

}
