package com.tokisaki.superadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.common.CommonUtil;
import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.repository.TaskRepository;

@Component
public class TaskService  {
	@Autowired
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task insert(Task task) throws InvalidInputParamException {
    	User currentUser=CommonUtil.getCurrentUser().orElseThrow( () -> new NotFoundException("User",""));
    	task.setCreateUser(currentUser);
    	task.setDisabled(false);
    	task.setTaskStatus(StatusEnum.Normal);
    	return this.taskRepository.save(task);
    }
    public Task update(Task task) throws InvalidInputParamException {
    	Task dbTask =this.taskRepository.findById(task.getId()).orElseThrow( () -> new NotFoundException("Task",task.getId()));
    	dbTask.setStartDate(task.getStartDate());
    	dbTask.setEndDate(task.getEndDate());
    	dbTask.setTaskName(task.getTaskName());
    	dbTask.setTaskDetail(task.getTaskDetail());
    	dbTask.setTaskScore(task.getTaskScore());
    	dbTask.setTaskOrder(task.getTaskOrder());
    	dbTask.setTaskAttachment(task.getTaskAttachment());
    	return this.taskRepository.save(task);
    }
    
}
