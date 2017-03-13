package com.hy.loong.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hy.loong.dao.MathDao;
import com.hy.loong.pojo.Page;
import com.hy.loong.service.MathService;

@Service("mathService")
public class MathServiceImp implements MathService {

	@Resource
	private MathDao mathDao;
	
	public void updateMath() {
		mathDao.updateMath();
	}

	public Page<Math> getMathPage(String nowPage, String pageSize) {
		//获取总条数
		Integer totalRecords = mathDao.findAllNum();
		//获取当前页
		Integer pageNo = Integer.parseInt(nowPage);   //页码
		Integer pageSizeInt =Integer.parseInt(pageSize);//数量
		//获取当前数据
		List<Math> maths =mathDao.getMathPage((pageNo-1)*pageSizeInt,pageSizeInt);
		
		//封装数据返回
		Page<Math> page = new Page<Math>();
		page.setPageIndex(pageNo);
		page.setPageSize(pageSizeInt);
		page.setPageDatas(maths);
		page.setTotalRecords(totalRecords);
		return page;
	}

}
