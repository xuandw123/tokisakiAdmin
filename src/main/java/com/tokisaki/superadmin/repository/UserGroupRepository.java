package com.tokisaki.superadmin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tokisaki.superadmin.domain.UserGroup;
@RepositoryRestResource(path = "usergroup", collectionResourceRel = "usergroup", itemResourceRel = "usergroup")
public interface UserGroupRepository extends JpaRepository<UserGroup, String> {

    Optional<UserGroup> findByGroupName(String groupName);
    Optional<UserGroup> findBygroupInviteCode(@Param("groupInviteCode")String groupInviteCode);
}
