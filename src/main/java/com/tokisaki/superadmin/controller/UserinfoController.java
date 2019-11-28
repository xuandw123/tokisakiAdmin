package com.tokisaki.superadmin.controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.repository.UserTaskRepository;

@RestController()
public class UserinfoController {
	@Autowired
	private UserTaskRepository userTaskRepository;

	@SuppressWarnings("rawtypes")
	@GetMapping("/api/me")
	public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
		Map<Object, Object> model = new HashMap<>();
		User user = (User) userDetails;
		BigDecimal score = userTaskRepository.sumTaskScore(user.getId());
		user.setTotalScore(score);
		model.put("username", userDetails.getUsername());
		model.put("user", user);
		model.put("roles", userDetails.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority())
				.collect(toList()));
		return ok(model);
	}
}
