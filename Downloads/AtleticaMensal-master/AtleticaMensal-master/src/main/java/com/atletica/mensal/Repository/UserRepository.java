package com.atletica.mensal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atletica.mensal.Entities.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
	Optional<UserEntity> findByEmail(String email);	
}