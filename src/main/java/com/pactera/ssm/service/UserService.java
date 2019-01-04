package com.pactera.ssm.service;

import com.pactera.ssm.entities.UserInfo;

public interface UserService {
	public UserInfo getUserInfoByUsername(String username);
	public int addUserInfo(UserInfo userInfo);
	public int deleteUserInfo(UserInfo userInfo);
	public int updateUserInfo(UserInfo userInfo);
	public UserInfo getUserInfoByEmployeeno(int username);
}
