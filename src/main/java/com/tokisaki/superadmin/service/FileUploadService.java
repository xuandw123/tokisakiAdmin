package com.tokisaki.superadmin.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tokisaki.superadmin.common.CosFileUtil;
import com.tokisaki.superadmin.domain.Attachment;
import com.tokisaki.superadmin.enums.FileTypeEnum;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.repository.AttachmentRepository;

@Component
public class FileUploadService  {
	@Autowired
	private CosFileUtil cosFileUtil;
	@Autowired
	AttachmentRepository attachmentRepository;

    public Attachment fileUpload(String key,File file,String extName,FileTypeEnum  fileType) throws InvalidInputParamException {
    String etag= cosFileUtil.SimpleUploadFileFromStream(key, file,  extName,fileType);
    Attachment attachment=new Attachment();
    	if(!StringUtils.isEmpty(etag)) {
    		
    		attachment.setAttachName(key);
    		attachment.setAttachType(fileType);
    		attachment.setAttachExtName(extName);
    		attachment =attachmentRepository.save(attachment);
    	}else {
    		throw new InvalidInputParamException("fileUpload failed");
    	}
    	return attachment;
    }
   
    
}
