package com.tokisaki.superadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.common.CommonUtil;
import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.domain.UserGroup;
import com.tokisaki.superadmin.model.UserScore;
import com.tokisaki.superadmin.repository.UserTaskRepository;

@Component
public class RankService  {
	@Autowired
    private UserTaskRepository userTaskRepository;

    public RankService(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }



	public Object rankForGroup() {
		User user=CommonUtil.getCurrentUser().get();
		//List<UserScore> list=userTaskRepository.selectScore();
		UserGroup usergroup=user.getUserGroup();
		
		return null;
	}
    
}
