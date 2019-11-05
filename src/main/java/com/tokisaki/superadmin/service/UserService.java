package com.tokisaki.superadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.domain.UserGroup;
import com.tokisaki.superadmin.entity.UserChangeGroupEntity;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.repository.UserGroupRepository;
import com.tokisaki.superadmin.repository.UserRepository;

@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserGroupRepository userGroupRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save(User task) throws InvalidInputParamException {

		return this.userRepository.save(task);
	}

	public User changeUserGroup(UserChangeGroupEntity userChangeGroupEntity) throws InvalidInputParamException {
		User user = this.userRepository.findById(userChangeGroupEntity.getUserId())
				.orElseThrow(() -> new NotFoundException("User", userChangeGroupEntity.getUserId()));
		UserGroup userGroup = this.userGroupRepository.findById(userChangeGroupEntity.getGroupId())
				.orElseThrow(() -> new NotFoundException("UserGroup", userChangeGroupEntity.getGroupId()));
		user.setUserGroup(userGroup);
		return this.userRepository.save(user);
	}
}
