package com.tokisaki.superadmin.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tokisaki.superadmin.domain.UserGroup;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.repository.UserGroupRepository;
import com.tokisaki.superadmin.service.FileUploadService;
import com.tokisaki.superadmin.service.UserGroupService;


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
	    public  ResponseEntity<Object>  upload(@RequestParam(value = "file") MultipartFile file, @RequestParam("taskId") String taskId){
		 String newFileName = UUID.randomUUID().toString();
		 File localFile = null;
		 try {
	            localFile = File.createTempFile("temp",null);
	            file.transferTo(localFile);
	            
		 fileUploadService.fileUpload(newFileName, localFile);
		 }catch (Exception e){
			 logger.error(e.getMessage());
		 }
		 return ok(null);
	}

	

}
