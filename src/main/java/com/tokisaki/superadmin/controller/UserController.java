package com.tokisaki.superadmin.controller;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tokisaki.superadmin.entity.UserChangeGroupEntity;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.repository.UserRepository;
import com.tokisaki.superadmin.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
    private UserRepository users;
	@Autowired
    private UserService userService;
    public UserController(UserRepository users) {
        this.users = users;
    }


    @GetMapping("")
    public ResponseEntity<Object>  all() {
        return ok(this.users.findAll());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Object>  findById(@PathVariable("userId") String userId) {
        return ok(this.userService.searchById(userId));
    }
    @GetMapping("/search/")
    public ResponseEntity<Object>  search(@RequestParam(value = "groupId", required = false) String groupId,@RequestParam(value = "userStatus", required = false) StatusEnum userStatus) {
        return ok(this.userService.search(groupId,userStatus));
    }
    @PostMapping("/changeusergroup/{userId}/newGroup/{groupId}/")
    public ResponseEntity<Object>  changeUserGroup(@PathVariable("userId") String userId,@PathVariable("groupId") String groupId)	throws InvalidInputParamException  {
    	UserChangeGroupEntity userChangeGroupEntity= new UserChangeGroupEntity(userId,groupId);
    	return ok(userService.changeUserGroup(userChangeGroupEntity));
    }
    
}
