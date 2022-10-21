package com.ssafy.ws.step3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.ws.step3.dto.User;
import com.ssafy.ws.step3.util.DBUtil;

public class UserDaoImpl implements UserDao {

	DBUtil util = DBUtil.getInstance();
	
	@Override
	public User select(String id) throws SQLException {
		String sql = "select * from user where id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			conn = util.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getNString("name");
				String pass = rs.getNString("pass");
				String recId = rs.getNString("rec_id");
				
				user = new User(id, pass, name, recId);
			}
			
		} finally {
			util.close(rs, ps, conn);
		}
		
		return user;
	}

}
