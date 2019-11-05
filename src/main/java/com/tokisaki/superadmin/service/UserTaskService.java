package com.tokisaki.superadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.domain.UserTask;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.repository.UserTaskRepository;

@Component
public class UserTaskService  {
	@Autowired
    private UserTaskRepository userTaskRepository;

    public UserTaskService(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }


    public UserTask save(UserTask userTask) throws InvalidInputParamException {
    	
        return this.userTaskRepository.save(userTask);
    }
    
}
