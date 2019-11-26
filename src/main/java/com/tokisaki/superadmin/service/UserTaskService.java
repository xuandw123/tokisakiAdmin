package com.tokisaki.superadmin.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.common.CommonUtil;
import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.domain.Usertask;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.repository.TaskRepository;
import com.tokisaki.superadmin.repository.UserRepository;
import com.tokisaki.superadmin.repository.UserTaskRepository;

@Component
public class UserTaskService  {
	@Autowired
    private UserTaskRepository userTaskRepository;
	@Autowired
    private TaskRepository taskRepository;
	@Autowired
    private UserRepository userRepository;
    public UserTaskService(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }


    public Usertask save(Usertask userTask,String taskId) throws InvalidInputParamException {
    	userTask.setUser(CommonUtil.getCurrentUser().get());
    	userTask.setFinishedDate(new Date());
    	Task task=taskRepository.findById(taskId).orElseThrow( () -> new NotFoundException("Task",taskId));
    	userTask.setTask(task);
    	userTask.setTaskScore(task.getTaskScore());
        return this.userTaskRepository.save(userTask);
    }


	public List<Usertask> getUserTaskByTaskIdforme(String taskId) {
		Task task=taskRepository.findById(taskId).orElseThrow( () -> new NotFoundException("Task",taskId));
		User user=CommonUtil.getCurrentUser().get();
		
		return  this.userTaskRepository.findByTaskAndUser(task,user);
	}


	public List<Usertask> getUserTaskByUserId(String userId) {
		User user =userRepository.findById(userId).orElseThrow( () -> new NotFoundException("User",userId));
		return this.userTaskRepository.findByUser(user);
	}


	public List<Usertask> getUserTaskByUserIdAndTaskId(String userId, String taskId) {
		User user =userRepository.findById(userId).orElseThrow( () -> new NotFoundException("User",userId));
		Task task=taskRepository.findById(taskId).orElseThrow( () -> new NotFoundException("Task",taskId));
		return this.userTaskRepository.findByTaskAndUser(task,user);
	}


	public Usertask audit(Usertask form,String usertaskId) {
		Usertask usertask = this.userTaskRepository.findById(usertaskId).orElseThrow( () -> new NotFoundException("UserTask",usertaskId));
		Task task=usertask.getTask();
		if (task==null||task.getTaskScore()==null||form.getTaskScore()==null) {
			throw new InvalidInputParamException("task score is null");
		}
		BigDecimal taskScore =task.getTaskScore();
		if (form.getTaskScore().compareTo(BigDecimal.ZERO)<0) {
			throw new InvalidInputParamException("task score should be bigger than 0");
		}
		if (form.getTaskScore().compareTo(taskScore)>0) {
			throw new InvalidInputParamException("task score should be <= "+taskScore.toString());
		}
		User user=CommonUtil.getCurrentUser().get();
		usertask.setAuditUser(user);
		usertask.setTaskScore(form.getTaskScore());
		usertask.setAuditDate(new Date());
		return this.userTaskRepository.save(usertask);
	}


	public List<Usertask> getUserTaskByTaskId(String taskId) {
		Task task=taskRepository.findById(taskId).orElseThrow( () -> new NotFoundException("Task",taskId));
		return this.userTaskRepository.findByTask(task);
	}
    
}
