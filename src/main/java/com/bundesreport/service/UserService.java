package com.bundesreport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	@Autowired
	private final UserRepository userRepository;

	@Transactional
	public User createUser(UserForm userForm) {
		User user = userForm.toEntity();
		userRepository.save(user);
		return user;
	}

	@Transactional
	public User updateUser(UserForm userForm) {
		User user = loadUserByUsername(userForm.getUserName());
		if (!StringUtils.isEmpty(userForm.getHashedPassword())) {
			user.setHashedPassword(new BCryptPasswordEncoder().encode(userForm.getHashedPassword()));
		}
		user.setEmail(userForm.getEmail());
		user.setLanguageStatus(userForm.getLanguageStatus());
		userRepository.save(user);
		return user;
	}

	@Transactional
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public User loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> userWrapper = userRepository.findByUserName(userName);
		User user = userWrapper.get();
		return user;
	}
}
