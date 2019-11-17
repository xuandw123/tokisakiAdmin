package com.tokisaki.superadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.common.InviteCodeUtil;
import com.tokisaki.superadmin.domain.UserGroup;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.exception.NotValidException;
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


	public UserGroup updateUserGroupInviteCode(String groupId) {
		UserGroup userGroup =this.userGroupRepository.findById(groupId).orElseThrow( () -> new NotFoundException("UserGroup",groupId));
		if(StatusEnum.Frozen.equals(userGroup.getGroupStatus())) {
			throw  new NotValidException("UserGroup"+groupId +" is frozen,update invitecode is not vaild." );
		}
		userGroup.setGroupInviteCode(InviteCodeUtil.generateShortUuid());
		return this.userGroupRepository.save(userGroup);
	}
    
}
