package com.tokisaki.superadmin;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.domain.UserGroup;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.enums.TaskTypeEnum;
import com.tokisaki.superadmin.repository.TaskRepository;
import com.tokisaki.superadmin.repository.UserGroupRepository;
import com.tokisaki.superadmin.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserGroupRepository userGroup;
    @Autowired
    UserRepository users;
    @Autowired
    TaskRepository tasks;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
    	long taskCount=this.tasks.count();
    	if(taskCount>0) {
    		log.info("initializing already...");
    		return;
    	}
    	log.info("initializing task data...");
    	 this.tasks.save(Task.builder().taskName("shorttask1").taskScore(new BigDecimal(5))
    			 .startDate(new Date()).endDate(new Date()).taskStatus(StatusEnum.Normal).taskType(TaskTypeEnum.ShortTerm) .build());
    	 this.tasks.save(Task.builder().taskName("shorttask2").taskScore(new BigDecimal(5))
    			 .startDate(new Date()).endDate(new Date()).taskStatus(StatusEnum.Normal).taskType(TaskTypeEnum.ShortTerm) .build());
    	 this.tasks.save(Task.builder().taskName("longtask1").taskScore(new BigDecimal(5))
    			 .startDate(new Date()).endDate(new Date()).taskStatus(StatusEnum.Normal).taskType(TaskTypeEnum.LongTerm) .build());
    	log.info("initializing group data...");
        Arrays.asList("1组", "2组", "3组").forEach(v -> this.userGroup.saveAndFlush(UserGroup.builder().groupName(v)
        		.groupStatus(StatusEnum.Normal).build()));
   

        this.users.save(User.builder()
            .username("user1")
            .password(this.passwordEncoder.encode("password"))
            .nickName("test")
            .selfIcon(false)
            .userStatus(StatusEnum.Normal)
            .roles(Arrays.asList( "ROLE_USER"))
            .build()
        );
        this.users.save(User.builder()
                .username("user2")
                .password(this.passwordEncoder.encode("password"))
                .nickName("test")
                .selfIcon(false)
                .userStatus(StatusEnum.Normal)
                .roles(Arrays.asList( "ROLE_USER"))
                .build()
            );
        this.users.save(User.builder()
            .username("admin")
            .password(this.passwordEncoder.encode("password"))
            .userStatus(StatusEnum.Normal)
            .roles(Arrays.asList("ROLE_USER", "ROLE_LEADER","ROLE_ADMIN"))
            .selfIcon(false)
            .build()
        );

        log.info("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
