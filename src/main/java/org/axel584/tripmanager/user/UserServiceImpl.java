package org.axel584.tripmanager.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	

	
	@Override
	public User createUser(User user) {
		user.setRegisterDate(new Date());
		// hacher le mot de passe en md5 TODO : trouver une méthode plus fiable qu'un simple MD5
		String encodedPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

}
