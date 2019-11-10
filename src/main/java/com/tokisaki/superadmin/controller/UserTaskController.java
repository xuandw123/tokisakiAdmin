package com.tokisaki.superadmin.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tokisaki.superadmin.common.CommonUtil;
import com.tokisaki.superadmin.domain.UserTask;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.repository.UserTaskRepository;

@RestController
@RequestMapping("/api/v1/usertask")
public class UserTaskController {

    private UserTaskRepository userTaskRepository;

    public UserTaskController(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }


    @GetMapping("")
    public ResponseEntity<Object>  all() {
        return ok(this.userTaskRepository.findAll());
    }
    @GetMapping("/task/{taskId}/usertask/{id}")
    public ResponseEntity<Object>  getById(@PathVariable("id") String id) {
        return ok(this.userTaskRepository.findById(id).orElseThrow( () -> new NotFoundException("Task",id)));
    }
    @PostMapping(name = "Save  User Task", value = "task/{taskId}/",  consumes =
    "application/json", produces = "application/json")
    public ResponseEntity<Object>  save(@RequestBody UserTask form, @PathVariable("taskId") String taskId,HttpServletRequest request) {
    	form.setUser(CommonUtil.getCurrentUser().get());
    	UserTask saved = this.userTaskRepository.save(form);
        return created(
            ServletUriComponentsBuilder
                .fromContextPath(request)
                .path("/v1/usertask/{taskId}")
                .buildAndExpand(saved.getId())
                .toUri())
            .build();
    }
    @PostMapping(name = "Audit User Score", value = "task/{usertaskId}/audit",  consumes =
    	    "application/json", produces = "application/json")
    	    public ResponseEntity<Object>  AuditScore(@RequestBody UserTask form, @PathVariable("usertaskId") String userTaskId,HttpServletRequest request) {
    	    	form.setUser(CommonUtil.getCurrentUser().get());
    	    	UserTask saved = this.userTaskRepository.save(form);
    	        return ok(
    	        		saved);
    	    }
}
