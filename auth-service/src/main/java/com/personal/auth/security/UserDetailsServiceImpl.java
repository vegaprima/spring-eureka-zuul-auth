package com.personal.auth.security;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.personal.auth.domain.User;
import com.personal.auth.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			user = userRepository.findByPhoneNumber(username);
		}
		if(user != null) {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_USER");
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}

		// hard coding the users. All passwords must be encoded.
//		final List<AppUser> users = Arrays.asList(new AppUser(1, "omar", encoder.encode("12345"), "USER"),
//				new AppUser(2, "admin", encoder.encode("12345"), "ADMIN"));
//
//		for (AppUser appUser : users) {
//			if (appUser.getUsername().equals(username)) {
//
//				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole
//				// (i.e. "ROLE_ADMIN")
//				// So, we need to set it to that format, so we can verify and compare roles
//				// (i.e. hasRole("ADMIN")).
//				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//						.commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
//
//				// The "User" class is provided by Spring and represents a model class for user
//				// to be returned by UserDetailsService
//				// And used by auth manager to verify and check user authentication.
//				return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
//			}
//		}

		// If user not found. Throw this exception.
//		throw new UsernameNotFoundException("Username: " + username + " not found");
	}

	// A (temporary) class represent the user saved in the database.
	private static class AppUser {
		private Integer id;
		private String username, password;
		private String role;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public AppUser(Integer id, String username, String password, String role) {
			this.id = id;
			this.username = username;
			this.password = password;
			this.role = role;
		}

		// getters and setters ....
	}

}
