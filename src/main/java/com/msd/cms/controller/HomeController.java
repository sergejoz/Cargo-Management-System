package com.msd.cms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("/")
	@PreAuthorize("isAnonymous()")
	public String index() {
		return "index";
	}
	
	@GetMapping("/home")
	@PreAuthorize("isAuthenticated()")
	public String home() {
		return "home";
	}
}
