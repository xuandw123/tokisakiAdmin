package com.tokisaki.superadmin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.enums.TaskTypeEnum;
@RepositoryRestResource(path = "task", collectionResourceRel = "task", itemResourceRel = "task")
public interface TaskRepository extends JpaRepository<Task, String> {

    
    Optional<List<Task>> findByTaskType(@Param("taskType") TaskTypeEnum taskType);
}
