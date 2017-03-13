package com.hy.loong.task;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hy.loong.service.MathService;

@Component
public class LoongTask {
	
	@Resource
	MathService mathService;
	
	public void task(){
		mathService.updateMath();
	}
}
