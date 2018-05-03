package lgh.springboot.sec2;

public interface AuthService {

	User register(User userToAdd);

	String login(String username, String password);

	String refresh(String oldToken);

}
