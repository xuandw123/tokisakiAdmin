package com.tokisaki.superadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tokisaki.superadmin.domain.User;

import java.util.Optional;
@RepositoryRestResource(path = "user", collectionResourceRel = "user", itemResourceRel = "user")
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

}
