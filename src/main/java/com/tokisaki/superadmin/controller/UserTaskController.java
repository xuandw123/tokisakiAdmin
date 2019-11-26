package com.tokisaki.superadmin.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tokisaki.superadmin.common.CommonUtil;
import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.domain.Usertask;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.repository.UserTaskRepository;
import com.tokisaki.superadmin.service.UserTaskService;

@RestController
@RequestMapping("/api/v1/usertask")
public class UserTaskController {
	@Autowired
    private UserTaskService userTaskService;
	@Autowired
    private UserTaskRepository userTaskRepository;
    public UserTaskController(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    @GetMapping("/forme/")
    public ResponseEntity<Object>  getAllByMe() {
    	User userId=CommonUtil.getCurrentUser().get();
        return ok(this.userTaskRepository.findByUser(userId));
    }
    @GetMapping("")
    public ResponseEntity<Object>  all() {
        return ok(this.userTaskRepository.findAll());
    }
    @GetMapping("/user/{userId}/")
    public ResponseEntity<Object>  getUserTaskByUserId(@PathVariable("userId") String userId) {
        return ok(this.userTaskService.getUserTaskByUserId(userId));
    }
    @GetMapping("/user/{userId}/task/{taskId}/")
    public ResponseEntity<Object>  getUserTaskByUserIdAndTaskId(@PathVariable("userId") String userId,@PathVariable("taskId") String taskId) {
        return ok(this.userTaskService.getUserTaskByUserIdAndTaskId(userId,taskId));
    }
    @GetMapping("/task/{taskId}/")
    public ResponseEntity<Object>  getUserTaskByTaskId(@PathVariable("taskId") String taskId) {
        return ok(this.userTaskService.getUserTaskByTaskId(taskId));
    }
    @GetMapping("/task/{taskId}/forme/")
    public ResponseEntity<Object>  getUserTaskByTaskIdforme(@PathVariable("taskId") String taskId) {
        return ok(this.userTaskService.getUserTaskByTaskIdforme(taskId));
    }
    @GetMapping("/task/{taskId}/usertask/{id}")
    public ResponseEntity<Object>  getById(@PathVariable("id") String id) {
        return ok(this.userTaskRepository.findById(id).orElseThrow( () -> new NotFoundException("Task",id)));
    }
    
    @PostMapping(name = "Save  User Task", value = "/task/{taskId}/",  consumes =
    "application/json", produces = "application/json")
    public ResponseEntity<Object>  save(@RequestBody Usertask form, @PathVariable("taskId") String taskId,HttpServletRequest request) {
    	
    
    	Usertask saved = this.userTaskService.save(form,taskId);
        return created(
            ServletUriComponentsBuilder
                .fromContextPath(request)
                .path("/v1/usertask/{taskId}")
                .buildAndExpand(saved.getId())
                .toUri())
            .build();
    }
    @PostMapping(name = "Audit User Score", value = "task/audit/{usertaskId}/",  consumes =
    	    "application/json", produces = "application/json")
    	    public ResponseEntity<Object>  AuditScore(@RequestBody Usertask form, @PathVariable("usertaskId") String userTaskId,HttpServletRequest request) {
    	    	form.setUser(CommonUtil.getCurrentUser().get());
    	    	Usertask saved = this.userTaskService.audit(form, userTaskId);
    	        return ok(
    	        		saved);
    	    }
}
