package com.tokisaki.superadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tokisaki.superadmin.domain.UserTask;
@RepositoryRestResource(path = "userTask", collectionResourceRel = "userTask", itemResourceRel = "userTask")
public interface UserTaskRepository extends JpaRepository<UserTask, String> {



}
