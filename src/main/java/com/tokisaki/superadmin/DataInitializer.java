package com.tokisaki.superadmin;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    
    	log.info("initializing group data...");
        Arrays.asList("1组", "2组", "3组").forEach(v -> this.userGroup.saveAndFlush(UserGroup.builder().groupName(v)
        		.groupStatus(StatusEnum.Normal).build()));
   
        UserGroup userGroup =this.userGroup.findByGroupName("1组").get();
        UserGroup userGroup2 =this.userGroup.findByGroupName("2组").get();
        this.users.save(User.builder()
            .username("user1")
            .password(this.passwordEncoder.encode("password"))
            .nickName("testUser")
            .selfIcon(false)
            .userGroup(userGroup)
            .userStatus(StatusEnum.Normal)
            .roles(Arrays.asList( "ROLE_USER"))
            .build()
        );
        this.users.save(User.builder()
                .username("user2")
                .password(this.passwordEncoder.encode("password"))
                .nickName("testUser")
                .selfIcon(false)
                .userGroup(userGroup)
                .userStatus(StatusEnum.Normal)
                .roles(Arrays.asList( "ROLE_USER"))
                .build()
            );
        this.users.save(User.builder()
                .username("user3")
                .password(this.passwordEncoder.encode("password"))
                .nickName("testUser")
                .selfIcon(false)
                .userGroup(userGroup)
                .userStatus(StatusEnum.Normal)
                .roles(Arrays.asList( "ROLE_USER"))
                .build()
            );
        this.users.save(User.builder()
                .username("leader1")
                .password(this.passwordEncoder.encode("password"))
                .nickName("testLeader")
                .selfIcon(false)
                .userGroup(userGroup)
                .userStatus(StatusEnum.Normal)
                .roles(Arrays.asList( "ROLE_USER","ROLE_LEADER"))
                .build()
            );
        this.users.save(User.builder()
                .username("user4")
                .password(this.passwordEncoder.encode("password"))
                .nickName("testUser")
                .selfIcon(false)
                .userGroup(userGroup2)
                .userStatus(StatusEnum.Normal)
                .roles(Arrays.asList( "ROLE_USER"))
                .build()
            );
        this.users.save(User.builder()
                .username("user5")
                .password(this.passwordEncoder.encode("password"))
                .nickName("testUser")
                .selfIcon(false)
                .userGroup(userGroup2)
                .userStatus(StatusEnum.Normal)
                .roles(Arrays.asList( "ROLE_USER"))
                .build()
            );
        this.users.save(User.builder()
                .username("user6")
                .password(this.passwordEncoder.encode("password"))
                .nickName("testUser")
                .selfIcon(false)
                .userGroup(userGroup2)
                .userStatus(StatusEnum.Normal)
                .roles(Arrays.asList( "ROLE_USER"))
                .build()
            );
        this.users.save(User.builder()
                .username("leader2")
                .password(this.passwordEncoder.encode("password"))
                .nickName("testLeader")
                .selfIcon(false)
                .userGroup(userGroup2)
                .userStatus(StatusEnum.Normal)
                .roles(Arrays.asList( "ROLE_USER","ROLE_LEADER"))
                .build()
            );
        this.users.save(User.builder()
            .username("admin")
            .password(this.passwordEncoder.encode("password"))
            .userStatus(StatusEnum.Normal)
            .nickName("testAdmin")
            .roles(Arrays.asList("ROLE_USER", "ROLE_LEADER","ROLE_ADMIN"))
            .selfIcon(false)
            .build()
        );

        log.info("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
        LocalDateTime localDate1 = LocalDateTime.of(2019, 11, 8,16,12,12);
        LocalDateTime localDate2 = LocalDateTime.of(2019, 11, 18,18,16,12);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant1 = localDate1.atZone(zone).toInstant();
        Date date1 = Date.from(instant1);
        Instant instant2 = localDate2.atZone(zone).toInstant();
        Date date2 = Date.from(instant2);
    	log.info("initializing task data...");
    	User user=this.users.findByUsername("admin").get();
   	 this.tasks.save(Task.builder().taskName("shorttask1").taskScore(new BigDecimal(5))
   			 .startDate(date1).endDate(date2).createUser(user).taskDetail("taskdetail1").taskStatus(StatusEnum.Normal).taskType(TaskTypeEnum.ShortTerm) .build());
   	 this.tasks.save(Task.builder().taskName("shorttask2").createUser(user).taskScore(new BigDecimal(5))
   			 .startDate(new Date()).endDate(new Date()).taskDetail("taskdetail2").taskStatus(StatusEnum.Normal).taskType(TaskTypeEnum.ShortTerm) .build());
   	 this.tasks.save(Task.builder().taskName("longtask1").createUser(user).taskScore(new BigDecimal(5))
   			 .startDate(new Date()).endDate(new Date()).taskDetail("taskdetail3").taskStatus(StatusEnum.Normal).taskType(TaskTypeEnum.LongTerm) .build());
    }
}
