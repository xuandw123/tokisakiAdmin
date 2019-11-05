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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.enums.TaskTypeEnum;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.repository.TaskRepository;
import  com.tokisaki.superadmin.service.TaskService;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
	@Autowired
    private TaskRepository taskRepository;
	@Autowired
    private TaskService taskService;
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @GetMapping("")
    public ResponseEntity<Object>  all() {
        return ok(this.taskRepository.findAll());
    }
    @GetMapping("/search/")
    public ResponseEntity<Object>  searchByType(@RequestParam("taskType") TaskTypeEnum taskType) {
        return ok(this.taskRepository.findByTaskType(taskType));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object>  getById(@PathVariable("id") String id) {
        return ok(this.taskRepository.findById(id).orElseThrow( () -> new NotFoundException("Task",id)));
    }
    
    @PostMapping(name = "Save Task", value = "/{taskId}",  consumes =
    "application/json", produces = "application/json")
    public ResponseEntity<Object>  updateTask(@RequestBody Task taskForm, HttpServletRequest request) throws InvalidInputParamException{
    	Task saved = this.taskService.save(taskForm);
        return created(
            ServletUriComponentsBuilder
                .fromContextPath(request)
                .path("/v1/task/{id}")
                .buildAndExpand(saved.getId())
                .toUri())
            .build();
    }
    
    @PostMapping(name = "Save Task", value = "",  consumes =
    "application/json", produces = "application/json")
    public ResponseEntity<Object>  insertTask(@RequestBody Task taskForm, HttpServletRequest request) throws InvalidInputParamException{
    	Task saved = this.taskService.save(taskForm);
        return created(
            ServletUriComponentsBuilder
                .fromContextPath(request)
                .path("/v1/task/{id}")
                .buildAndExpand(saved.getId())
                .toUri())
            .build();
    }

}
