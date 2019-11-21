package com.tokisaki.superadmin.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.common.CommonUtil;
import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.domain.TaskAttachment;
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
    	Task insertTask =new Task();
    	task.setCreateUser(currentUser);
    	task.setDisabled(false);
    	task.setTaskStatus(StatusEnum.Normal);
    	/*insertTask.setStartDate(task.getStartDate());
    	insertTask.setEndDate(task.getEndDate());
    	insertTask.setTaskName(task.getTaskName());
    	insertTask.setTaskDetail(task.getTaskDetail());
    	insertTask.setTaskScore(task.getTaskScore());
    	insertTask.setTaskOrder(task.getTaskOrder());
    	insertTask=this.taskRepository.saveAndFlush(insertTask);
    	String id=insertTask.getId();
    	//String uuid =CommonUtil.generateUUID();
    	//task.setId(uuid);
    	Set<TaskAttachment> taskAttachmentSet= new HashSet<>();
    	for(TaskAttachment taskAttachment:task.getTaskAttachment()) {
    		TaskAttachment sTaskAttachment=new TaskAttachment();
    		Task ainsertTask =new Task();
    		
    		ainsertTask.setId(id);
    		sTaskAttachment.setAttachment(taskAttachment.getAttachment());
    		sTaskAttachment.setTask(ainsertTask);
    		taskAttachmentSet.add(sTaskAttachment);
    	}
    	insertTask.setTaskAttachment(taskAttachmentSet);*/
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
