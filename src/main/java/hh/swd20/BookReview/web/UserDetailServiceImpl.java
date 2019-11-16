package hh.swd20.BookReview.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.swd20.BookReview.domain.User;
import hh.swd20.BookReview.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	
	private final UserRepository userRepo;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepo = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User currUser = userRepo.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(
				username, currUser.getPassword(), AuthorityUtils.createAuthorityList(currUser.getRole())
				);
		
		return user;
		
	}
	
	
}
