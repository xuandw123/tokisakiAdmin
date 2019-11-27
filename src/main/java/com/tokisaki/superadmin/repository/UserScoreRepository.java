package com.tokisaki.superadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tokisaki.superadmin.model.UserScore;
@RepositoryRestResource(path = "userScore", collectionResourceRel = "userScore", itemResourceRel = "userScore")
public interface UserScoreRepository extends JpaRepository<UserScore, String> {

    
	@Query(value ="select t.user_id as user_id,sum(t.task_Score)as task_score from t_usertask t group by t.user_id order by sum(t.task_Score) desc", nativeQuery=true)
	List<UserScore> selectScore();
	
}
