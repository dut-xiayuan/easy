package me.wuyi.easyrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.wuyi.easyrent.bean.User;
import me.wuyi.easyrent.mapper.UserMapper;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUser(int id) {
		User user = userMapper.getUser(id);
		return user;
	}

}
