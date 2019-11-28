package com.tokisaki.superadmin.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.domain.Usertask;
import com.tokisaki.superadmin.model.UserScore;
@RepositoryRestResource(path = "usertask", collectionResourceRel = "usertask", itemResourceRel = "usertask")
public interface UserTaskRepository extends JpaRepository<Usertask, String> {

	List<Usertask> findByUser(@Param("user") User user);
	List<Usertask> findByTaskAndUser(@Param("task") Task task,@Param("user") User user);
	List<Usertask> findByTask(@Param("task") Task task);
	@Query("select sum(t.taskScore)as taskScore from Usertask t where t.user.id=:userId")
	BigDecimal sumTaskScore(@Param("userId") String userId);

}
