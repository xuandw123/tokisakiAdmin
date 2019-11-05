package com.tokisaki.superadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.domain.UserGroup;
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
    
}
