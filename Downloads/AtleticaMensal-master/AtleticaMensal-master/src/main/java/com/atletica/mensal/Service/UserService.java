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

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserEntity findByEmail(String email) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("O email não pode estar vazio");
		}
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}

	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}

	public void deletarUser(Long id) {
		UserEntity user = buscarUserPorId(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		userRepository.delete(user);
	}

	public Optional<UserEntity> buscarUserPorId(Long id) {
		return userRepository.findById(id);
	}
}