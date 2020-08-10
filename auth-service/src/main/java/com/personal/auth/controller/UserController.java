package com.personal.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.auth.domain.User;
import com.personal.auth.repository.UserRepository;
import com.personal.common.security.JwtTokenDeconstructor;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private JwtTokenDeconstructor jwtTokenDeconstructor;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public void signup(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	@RequestMapping(value = "/list")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@ResponseBody
	@RequestMapping(value = "/detail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getDetail(@RequestHeader("Authorization") String auth) {
		String username = jwtTokenDeconstructor.getAuthenticatedUserFromHeader(auth);
		User user = userRepository.findByUsername(username);
		return user;
	}
}
