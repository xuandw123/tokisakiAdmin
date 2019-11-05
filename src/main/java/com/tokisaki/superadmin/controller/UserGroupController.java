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

import com.tokisaki.superadmin.common.InviteCodeUtil;
import com.tokisaki.superadmin.domain.UserGroup;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.repository.UserGroupRepository;
import com.tokisaki.superadmin.service.UserGroupService;

@RestController
@RequestMapping("/api/v1/usergroup")
public class UserGroupController {
	@Autowired
	private UserGroupService userGroupService;
	@Autowired
	private UserGroupRepository userGroupRepository;

	public UserGroupController(UserGroupRepository userGroupRepository) {
		this.userGroupRepository = userGroupRepository;
	}

	@PostMapping(name = "Save User Group", value = "/save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> insertUserGroup(@RequestBody UserGroup usergroupForm, HttpServletRequest request)
			throws InvalidInputParamException {
		usergroupForm.setGroupInviteCode(InviteCodeUtil.generateShortUuid());
		UserGroup saved = this.userGroupService.save(usergroupForm);
		return created(ServletUriComponentsBuilder.fromContextPath(request).path("/v1/usergroup/{id}")
				.buildAndExpand(saved.getId()).toUri()).build();
	}

	@PostMapping(name = "update user group inviteCode", value = "/updateInviteCode/{groupId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateUserGroupInviteCode(@RequestBody UserGroup usergroupForm,
			@PathVariable("groupId") String groupId) {
		
		usergroupForm.setGroupInviteCode(InviteCodeUtil.generateShortUuid());
		UserGroup saved = this.userGroupService.save(usergroupForm);
		return ok(saved);
	}

	@GetMapping("/listall")
	public ResponseEntity<Object> all() {
		return ok(this.userGroupRepository.findAll());
	}

}
