package com.hcl.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
