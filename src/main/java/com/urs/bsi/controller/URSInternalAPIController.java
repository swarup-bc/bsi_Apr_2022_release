package com.urs.bsi.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
public class URSInternalAPIController {
	
	
	@ApiIgnore
	@RequestMapping("/validateUser")
	public Principal user(Principal user) {
		return user;
	}
	


}
