package test;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;

import com.ssafy.ws.step3.dao.UserDao;
import com.ssafy.ws.step3.dao.UserDaoImpl;
import com.ssafy.ws.step3.service.UserService;
import com.ssafy.ws.step3.service.UserServiceImpl;

public class UserTest {

	UserDao dao = new UserDaoImpl();
	UserService Service = new UserServiceImpl(dao);
	
	// @Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void select() throws SQLException {
		System.out.println(Service.select("ssafy"));
		System.out.println(Service.select("admin"));
		System.out.println(Service.select("hyewon"));
	}
}
