package com.pactera.ssm.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pactera.ssm.entities.UserInfo;
import com.pactera.ssm.mapper.UserDao;
import com.pactera.ssm.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao; 
	@Override
	public UserInfo getUserInfoByUsername(String username) {
		return userDao.getUserInfoByUsername(username);
	}

	@Override
	public int addUserInfo(UserInfo userInfo) {
		return userDao.addUserInfo(userInfo);
	}

	@Override
	public int deleteUserInfo(UserInfo userInfo) {
		return userDao.deleteUserInfo(userInfo);
	}

	@Override
	public int updateUserInfo(UserInfo userInfo) {
		return userDao.updateUserInfo(userInfo);
	}

	@Override
	public UserInfo getUserInfoByEmployeeno(int employeeno) {
		return userDao.getUserInfoByEmployeeno(employeeno);
	}

}
