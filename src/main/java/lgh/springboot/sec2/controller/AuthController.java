package lgh.springboot.sec2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lgh.springboot.sec2.dto.JwtAuthenticationRequest;
import lgh.springboot.sec2.dto.JwtAuthenticationResponse;
import lgh.springboot.sec2.dto.User;
import lgh.springboot.sec2.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Value("${app.security.jwt.header}")
	private String header;

	@Value("${app.security.jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws AuthenticationException {
		final String token = authService.login(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());
		response.setHeader(header, tokenHead + token);

		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String token = request.getHeader(header);
		String refreshedToken = authService.refresh(token);
		if (refreshedToken == null) {
			return ResponseEntity.badRequest().body(null);
		} else {
			response.setHeader(header, tokenHead + refreshedToken);
			return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User register(@RequestBody User user) throws AuthenticationException {
		return authService.register(user);
	}
}