package lgh.springboot.sec2.service;

import lgh.springboot.sec2.dto.User;

public interface AuthService {

	User register(User userToAdd);

	String login(String username, String password);

	String refresh(String oldToken);

}
