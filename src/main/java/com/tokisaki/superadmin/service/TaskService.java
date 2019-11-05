package com.tokisaki.superadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.repository.TaskRepository;

@Component
public class TaskService  {
	@Autowired
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task save(Task task) throws InvalidInputParamException {
    	
        return this.taskRepository.save(task);
    }
    
}
