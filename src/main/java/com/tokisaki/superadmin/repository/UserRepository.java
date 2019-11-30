package com.tokisaki.superadmin.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.model.UserScore;
@RepositoryRestResource(path = "user", collectionResourceRel = "user", itemResourceRel = "user")
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
    List<User> findByUserGroupId(@Param("groupId") String groupId);
	List<User> findByUserStatus(@Param("userStatus") StatusEnum userStatus);
	List<User> findByUserStatusAndUserGroupId(@Param("userStatus") StatusEnum userStatus, @Param("groupId")String groupId);
	Optional<User> findByOpenid(@Param("openid")String openid);
	@Query("select t.user,sum(t.taskScore)as taskScore from Usertask t group by t.user order by sum(t.taskScore) desc")
	List<Object[]> selectScore();
	@Query("select t.user,sum(t.taskScore)as taskScore from Usertask t where t.task.id =:taskId group by t.user order by sum(t.taskScore) desc")
	List<Object[]> selectScoreByTaskId(@Param("taskId") String taskId);
	@Query("select t.user,sum(t.taskScore)as taskScore from Usertask t  where t.user.id in(select  id from User  t2 where t2. userGroup.id=:groupId) group by t.user order by sum(t.taskScore) desc")
	List<Object[]> selectScoreByGroupId(@Param("groupId") String groupId);
	@Query("select t.user,sum(t.taskScore)as taskScore from Usertask t  where t.task.id =:taskId  and t.user.id in(select  id from User  t2 where t2. userGroup.id=:groupId) group by t.user order by sum(t.taskScore) desc")
	List<Object[]> selectScoreByGroupIdAndTaskId(@Param("groupId") String groupId,@Param("taskId") String taskId);
	@Query("select t.user,sum(t.taskScore)as taskScore from Usertask t where  t.finishedDate >=:from and   t.finishedDate  <=:to group by t.user order by sum(t.taskScore) desc")
	List<Object[]> selectScoreAndTimeLimit(@Param("from") Date from, @Param("to") Date to);
	@Query("select t.user,sum(t.taskScore)as taskScore from Usertask t  where t.user.id in(select  id from User  t2 where t2. userGroup.id=:groupId) and  t.finishedDate >=:from and   t.finishedDate  <=:to  group by t.user order by sum(t.taskScore) desc")
	List<Object[]> selectScoreByGroupIdAndTimeLimit(@Param("groupId") String groupId,@Param("from") Date from, @Param("to") Date to);
}
