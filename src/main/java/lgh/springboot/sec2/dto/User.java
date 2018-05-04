package lgh.springboot.sec2.dto;

import java.util.List;

import lombok.Data;

@Data
public class User {
	private String username;
	private String password;
	private List<String> roles;
}
