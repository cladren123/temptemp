package com.ssafy.ws.step3.dao;

import java.sql.SQLException;

import com.ssafy.ws.step3.dto.User;

public interface UserDao {

	User select(String id) throws SQLException;
}
