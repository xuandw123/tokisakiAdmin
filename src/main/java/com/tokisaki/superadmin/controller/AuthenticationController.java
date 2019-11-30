package com.tokisaki.superadmin.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.domain.UserGroup;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.repository.UserGroupRepository;
import com.tokisaki.superadmin.repository.UserRepository;
import com.tokisaki.superadmin.security.jwt.JwtTokenProvider;
import com.tokisaki.superadmin.web.AuthenticationRequest;
import com.tokisaki.superadmin.web.AuthenticationRequestBlind;
import com.tokisaki.superadmin.service.UserService;

import io.swagger.annotations.Api;

@Api(tags = { "Login controller" })
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository users;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userservice;

	@Autowired
	UserGroupRepository userGroup;

	@PostMapping("/signin")
	public ResponseEntity<Map<Object, Object>> signin(@RequestBody AuthenticationRequest data) {

		try {
			String username = data.getUsername();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}
	}

	@GetMapping("/qqlogin")
	public ResponseEntity<Map<Object, Object>> qqlogin(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String response_type = "code";
		String client_id = "101825291";
		String redirect_uri = "https://tokisaki.cn/api/auth/qqloginCallback";
		String state = new Date().toString();
		req.getSession().setAttribute("state", state);
		String url = String.format(
				"https://graph.qq.com/oauth2.0/authorize" + "?response_type=%s&client_id=%s&redirect_uri=%s&state=%s",
				response_type, client_id, redirect_uri, state);
		Map<Object, Object> model = new HashMap<>();
		model.put("url", url);
		return ok(model);
	}

	@GetMapping("/qqloginCallback")
	public ResponseEntity<Map<Object, Object>> qqloginCallback(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String authorization_code = req.getParameter("code");
		if (authorization_code != null && !authorization_code.trim().isEmpty()) {
			// client端的状态值。用于第三方应用防止CSRF攻击。
			String state = req.getParameter("state");
			if (!state.equals(req.getParameter("state"))) {
				throw new RuntimeException("client端的状态值不匹配！");
			}
			String urlForAccessToken = getUrlForAccessToken(authorization_code);
			System.out.println("------------------------------------authorization_code" + authorization_code);
			// 向腾讯发起请求,获取access_token
			RestTemplate restTemplate = new RestTemplate();
			String firstCallbackInfo = restTemplate.getForObject(urlForAccessToken, String.class);
			System.out.println("------------------------------------" + firstCallbackInfo);
			String[] params = firstCallbackInfo.split("&");
			String access_token = null;
			for (String param : params) {
				String[] keyvalue = param.split("=");
				if (keyvalue[0].equals("access_token")) {
					access_token = keyvalue[1];
					break;
				}
			}
			System.out.println("------------------------------------access_token" + access_token);
			//
			if (access_token != null && !access_token.trim().isEmpty()) {
				String url = String.format("https://graph.qq.com/oauth2.0/me?access_token=%s", access_token);
				// 第二次模拟客户端发出请求后得到的是带openid的返回数据,格式如下
				// callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
				String secondCallbackInfo = restTemplate.getForObject(url, String.class);
				String regex = "\\{.*\\}";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(secondCallbackInfo);
				if (!matcher.find()) {
					throw new RuntimeException("异常的回调值: " + secondCallbackInfo);
				}

				// 调用jackson
				ObjectMapper objectMapper = new ObjectMapper();
				HashMap hashMap = objectMapper.readValue(matcher.group(0), HashMap.class);

				String qqopenid = ((String) hashMap.get("openid"));
				System.out.println("------------------------------------openid" + qqopenid);

				try {
					Optional<User> qqusers = this.users.findByOpenid(qqopenid);
					// 如果用户不存在，创建新用户
					if (qqusers.isEmpty()) {
						System.out.println("------------------------------------用户不存在");
						User newuser = new User();
						long nowtime = System.currentTimeMillis();
						String username = Long.toString(nowtime);
						String password = this.passwordEncoder.encode("password");
						String nickName = username;
						UserGroup userGroup = this.userGroup.findByGroupName("1组").get();
						newuser = this.users.save(
								User.builder().username(username).password(this.passwordEncoder.encode("password"))
										.nickName(username).selfIcon(false).userGroup(userGroup)
										.userStatus(StatusEnum.Normal).roles(Arrays.asList("ROLE_USER")).build());
						String id = newuser.getId();
						Map<Object, Object> model = new HashMap<>();
						model.put("id", id);
						return ok(model);
					} else {
						User qquser = qqusers.orElse(null);
						String username = qquser.getUsername();
						String ifblind = qquser.getIfblind();
						String id = qquser.getId();
						// 如果用户未绑定，则跳转到绑定页面
						if ("0".equals(ifblind)) {
							Map<Object, Object> model = new HashMap<>();
							model.put("id", id);
							return ok(model);
						} else {
							String password = qquser.getPassword();
							String userId = qquser.getId();
							// authenticationManager.authenticate(new
							// UsernamePasswordAuthenticationToken(username, password));
							String token = jwtTokenProvider.createToken(username,
									this.users.findByUsername(username).orElseThrow(
											() -> new UsernameNotFoundException("Username " + username + "not found"))
											.getRoles());
							System.out.println("------------------------------------username" + username);
							Map<Object, Object> model = new HashMap<>();
							model.put("username", username);
							model.put("token", token);
							return ok(model);
						}
					}
				} catch (AuthenticationException e) {
					throw new BadCredentialsException("Invalid username/password supplied");
				}

			}
		}
		return null;
	}

	@PostMapping("/qqblind")
	public ResponseEntity<Map<Object, Object>> blind(@RequestBody AuthenticationRequestBlind data) {
		String id = data.getId();
		System.out.println("-----------------------------id" + id);
		String username = data.getUsername();
		String password = data.getPassword();
		String groupInvite = data.getGroupInvite();
		Optional<User> users = this.users.findById(id);
		try {
			UserGroup userGroup = this.userGroup.findBygroupInviteCode(groupInvite).get();
			User user = users.orElse(null);
			user.setUsername(username);
			user.setPassword(this.passwordEncoder.encode("password"));
			user.setUserGroup(userGroup);
			System.out.println("-----------------------------user" + user.toString());
			this.users.save(user);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid GroupInvitedCode supplied");
		}
	}

	public String getUrlForAccessToken(String authorization_code) {
		String grant_type = "authorization_code";
		String client_secret = "e9b5d23847167b381c792d71052a0773";
		String client_id = "101825291";
		String redirect_uri = "https://tokisaki.cn/qqloin";

		String url = String.format(
				"https://graph.qq.com/oauth2.0/token"
						+ "?grant_type=%s&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s",
				grant_type, client_id, client_secret, authorization_code, redirect_uri);
		return url;
	}

}
