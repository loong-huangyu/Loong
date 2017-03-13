package com.hy.loong.service.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.loong.dao.UserDao;
import com.hy.loong.pojo.User;
import com.hy.loong.service.UserService;

@Service("userService")
public class UserServiceImp implements UserService{

	@Autowired 
	private UserDao userDao;
	
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	public User findUser(User user) {
		// TODO Auto-generated method stub
		return userDao.findUser(user);
	}

	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("111", "222");
		return map;
	}
	
	public User getBean() {
		User user = new User();
		user.setUsername("huangyu");
		user.setPassword("123456");
		return userDao.findUser(user);
	}


}
