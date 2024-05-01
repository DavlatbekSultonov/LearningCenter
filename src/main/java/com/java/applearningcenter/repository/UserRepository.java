package com.java.applearningcenter.repository;

import com.java.applearningcenter.entity.authuser.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> getAuthUserByUsername(String username);
//    Optional<AuthUser> getAuthUserByRoleMento(String role);


}
