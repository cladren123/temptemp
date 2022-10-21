package com.ssafy.ws.step3.service;

import java.sql.SQLException;

import com.ssafy.ws.step3.dto.User;

public interface UserService {

	User select(String id) throws SQLException;
	
}
