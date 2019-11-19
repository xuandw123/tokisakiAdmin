package com.tokisaki.superadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tokisaki.superadmin.domain.ScoreAward;
@RepositoryRestResource(path = "scoreAward", collectionResourceRel = "scoreAward", itemResourceRel = "scoreAward")
public interface ScoreAwardRepository extends JpaRepository<ScoreAward, String> {

    
    
}
