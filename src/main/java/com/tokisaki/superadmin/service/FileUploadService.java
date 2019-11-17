package com.tokisaki.superadmin.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.common.CommonUtil;
import com.tokisaki.superadmin.common.CosFileUtil;
import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.repository.TaskRepository;

@Component
public class FileUploadService  {



    public void fileUpload(String key,File file) throws InvalidInputParamException {
    String aa=CosFileUtil.SimpleUploadFileFromStream(key, file);
    }
   
    
}
