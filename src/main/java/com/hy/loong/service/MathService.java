package com.hy.loong.service;

import com.hy.loong.pojo.Page;

public interface MathService {
	
	public void updateMath();
	
	
	/**
	 * 查询学科数据
	 * @param nowPage		当前页
	 * @param pageSize		显示数量	
	 * @return
	 */
	public Page<Math> getMathPage(String nowPage,String pageSize);
	
}
