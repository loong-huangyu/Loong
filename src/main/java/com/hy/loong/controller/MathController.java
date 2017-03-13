package com.hy.loong.controller;



import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.loong.pojo.Page;
import com.hy.loong.service.MathService;

@Controller
@RequestMapping("/math")
public class MathController {
	private static final Logger log = LoggerFactory.getLogger(MathController.class);
	
	@Autowired
	private MathService mathService;
	
	@RequestMapping("/page")
	public String page(Model model){
		log.info("进入LoginController：page");
		Page<Math> pages = mathService.getMathPage("1", "5");
		model.addAttribute("pages", pages);
		return "page";
	}
	
	@RequestMapping("/pages")
	public String pages(@Param(value="pageNo") String nowPage,@Param(value="pageSize") String pageSize,Model model){
		log.info("进入LoginController：page");
		Page<Math> pages = mathService.getMathPage(nowPage, "5");
		model.addAttribute("pages", pages);
		return "page";
	}
}
