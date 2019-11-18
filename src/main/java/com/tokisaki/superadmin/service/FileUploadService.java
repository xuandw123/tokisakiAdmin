package com.tokisaki.superadmin.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.common.CosFileUtil;
import com.tokisaki.superadmin.exception.InvalidInputParamException;

@Component
public class FileUploadService  {
	@Autowired
	private CosFileUtil cosFileUtil;


    public String fileUpload(String key,File file,String extName) throws InvalidInputParamException {
    return cosFileUtil.SimpleUploadFileFromStream(key, file,  extName);
    }
   
    
}
