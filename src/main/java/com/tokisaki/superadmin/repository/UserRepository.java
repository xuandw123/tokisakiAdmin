package com.tokisaki.superadmin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.enums.StatusEnum;
@RepositoryRestResource(path = "user", collectionResourceRel = "user", itemResourceRel = "user")
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
    List<User> findByUserGroupId(@Param("groupId") String groupId);
	List<User> findByUserStatus(@Param("userStatus") StatusEnum userStatus);
	List<User> findByUserStatusAndUserGroupId(@Param("userStatus") StatusEnum userStatus, @Param("groupId")String groupId);
}
