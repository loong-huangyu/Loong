package com.hy.loong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MathDao extends SqlMapper{
	
	public void updateMath();
	
	//获取总条数
	public Integer findAllNum();
	
	public List<Math> getMathPage(@Param(value="pageNo") Integer pageNo,@Param(value="pageSizeInt") Integer pageSizeInt);
}
