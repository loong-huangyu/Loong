package com.hy.loong.controller;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.loong.pojo.User;
import com.hy.loong.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String orderMatch(User user,HttpSession session,Model model){
		log.info("进入orderMatch：login");
		//userService.addUser(user);
		//System.out.println("user.getid:"+user.getId());
		User uu= userService.findUser(user);
		if(uu==null){
			log.info("登录失败");
			return "error";
		}
		log.info("登录成功");
		session.setAttribute("user", user);
		model.addAttribute("username", user.getUsername());
		model.addAttribute("password", user.getPassword());
		return "login";
	}
}
