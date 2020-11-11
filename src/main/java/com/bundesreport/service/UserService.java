package com.bundesreport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public Long createUser(UserForm userForm) {
		User user = userForm.toEntity();
		userRepository.save(user);
		return user.getId();
	}

	@Override
	public User loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> userWrapper = userRepository.findByUserName(userName);
		User user = userWrapper.get();
		return user;
	}
}
