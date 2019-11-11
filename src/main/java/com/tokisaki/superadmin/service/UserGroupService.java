package com.tokisaki.superadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.domain.UserGroup;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.repository.UserGroupRepository;

@Component
public class UserGroupService  {
	@Autowired
    private UserGroupRepository userGroupRepository;

    public UserGroupService(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }


    public UserGroup save(UserGroup userGroup) throws NotFoundException {
    	
        return this.userGroupRepository.save(userGroup);
    }


	public UserGroup changeUserGroupStatus(StatusEnum statusEnum, String groupId) {
		UserGroup userGroup =this.userGroupRepository.findById(groupId).orElseThrow( () -> new NotFoundException("UserGroup",groupId));
		userGroup.setGroupStatus(statusEnum);
		 return this.userGroupRepository.save(userGroup);
	}
    
}
