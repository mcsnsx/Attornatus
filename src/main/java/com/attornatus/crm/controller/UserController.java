package com.attornatus.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.crm.model.Address;
import com.attornatus.crm.model.User;
import com.attornatus.crm.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
	public List<User> all() {
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws NotFoundException {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new NotFoundException());
			return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/user/nome/{nome}")
	public ResponseEntity<User> getUserByNome(@PathVariable(value = "nome") String nome) throws NotFoundException {
			User user = userRepository.findByNome(nome)
					.orElseThrow(() -> new NotFoundException());
			return ResponseEntity.ok().body(user);
	}

	@PostMapping("/user")
	public User register(@Validated @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> update(@PathVariable(value = "id") Long userId, @Validated @RequestBody User userDetails, Address endereco) throws NotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException());
		
		user.setNome(userDetails.getNome());
		user.setDataNascimento(userDetails.getDataNascimento());
		final User updateUser = userRepository.save(user);
		return ResponseEntity.ok(updateUser);
	}
	
	
}
