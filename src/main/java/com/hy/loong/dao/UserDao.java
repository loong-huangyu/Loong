package com.hy.loong.dao;

import com.hy.loong.pojo.User;

public interface UserDao extends SqlMapper {
	public void addUser(User user);
	public User findUser(User user);
}
