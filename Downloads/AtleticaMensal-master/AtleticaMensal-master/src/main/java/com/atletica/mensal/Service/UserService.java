package com.atletica.mensal.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Repository.UserRepository;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity findByEmail(String email) {
        
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("o email nao pode estar vazio");
        }
        
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByEmail(email));
        
        return userRepository.findByEmail(email);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
    
    public void deletarUser(Long id) {
        UserEntity user = buscarUserPorId(id)
                .orElseThrow(() -> new RuntimeException("usuario não encontrado"));
        userRepository.delete(user);
    }
    
    public Optional<UserEntity> buscarUserPorId(Long id) {
        return userRepository.findById(id);
    }
}