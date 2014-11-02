package org.yankun.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yankun.dashboard.model.User;

@Controller
public class APIController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getUser() {
		User user = new User();
		user.setUsername("Yankun");
		user.setPassword("123432");
		return user;
	}
}
