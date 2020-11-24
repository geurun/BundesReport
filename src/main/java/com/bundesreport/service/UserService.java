package com.bundesreport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.bundesreport.domain.User;
import com.bundesreport.dto.UserForm;
import com.bundesreport.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	@Autowired
	private final UserRepository userRepository;

	public User save(UserForm userForm) {
		return userRepository.save(userForm.toEntity());
	}

	public User update(UserForm userForm) {
		User user = findByUsername(userForm.getUsername());
		if (!StringUtils.isEmpty(userForm.getPassword())) {
			user.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
		}
		user.setEmail(userForm.getEmail());
		user.setLanguageStatus(userForm.getLanguageStatus());
		userRepository.save(user);
		return user;
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public User findById(Long id) {
		Optional<User> userWrapper = userRepository.findById(id);
		return userWrapper.get();
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByUsername(username);
	}
}
