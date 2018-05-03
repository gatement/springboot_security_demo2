package lgh.springboot.sec2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
// @PreAuthorize("hasRole('ADMIN')")
public class TestController {
	@RequestMapping(path = "/open", method = RequestMethod.GET)
	public String testOpen() {
		return "open";
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public String testUser() {
		return "user";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/admin", method = RequestMethod.GET)
	public String testAdmin() {
		return "admin";
	}
}
