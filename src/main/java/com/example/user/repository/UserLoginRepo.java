package com.example.user.repository;

import com.example.user.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepo extends JpaRepository<UserLogin, Integer> {

    UserLogin findByUserName(String username);
}
