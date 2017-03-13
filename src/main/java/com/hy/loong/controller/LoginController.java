package com.hy.loong.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.loong.pojo.User;

@Controller
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	@RequestMapping("/")
	public String indexMatch(User user){
		log.info("进入LoginController：index");
		return "index";
	}
	
	@RequestMapping("/demo")
	public String demo(User user){
		log.info("进入LoginController：demo");
		return "demo";
	}
}
