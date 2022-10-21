package com.ssafy.ws.step3.service;

import java.sql.SQLException;

import com.ssafy.ws.step3.dao.UserDao;
import com.ssafy.ws.step3.dto.User;

public class UserServiceImpl implements UserService {

	UserDao dao; // setter 또는 생성자를 통해 주소주입(바인딩)
	
	public UserServiceImpl() {	}
	
	public UserServiceImpl(UserDao dao) {
		this.dao = dao;
	}

	public UserDao getDao() {
		return dao;
	}
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public User select(String id) throws SQLException {
		return dao.select(id);
	}

}
