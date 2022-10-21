package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.ssafy.ws.step3.dao.BookDao;
import com.ssafy.ws.step3.dao.BookDaoImpl;
import com.ssafy.ws.step3.dto.Book;
import com.ssafy.ws.step3.service.BookService;
import com.ssafy.ws.step3.service.BookServiceImpl;

public class BookTest {

	BookDao dao = new BookDaoImpl();
	BookService service = new BookServiceImpl(dao);

	@Test
	public void insert() throws SQLException {
		service.insert(new Book("2022001", "Java Programming", "홍길동", 50000, "자바기초를 다룬 교재입니다.", null));
	}

	//@Test
	public void select() throws SQLException {
		System.out.println("Book List");
		List<Book> books = service.select();
		
		System.out.println(new Book("2022001", "Java Programming", "홍길동", 50000, "자바기초를 다룬 교재입니다.", null));
		System.out.println(new Book("2022001", "Java Programming", "홍길동", 50000, "자바기초를 다룬 교재입니다.", null));
		System.out.println(new Book("2022001", "Java Programming", "홍길동", 50000, "자바기초를 다룬 교재입니다.", null));
		System.out.println(new Book("2022001", "Java Programming", "홍길동", 50000, "자바기초를 다룬 교재입니다.", null));
		System.out.println(new Book("2022001", "Java Programming", "홍길동", 50000, "자바기초를 다룬 교재입니다.", null));
		books.forEach(i -> System.out.println(i));
	}

	@Test
	public void getItem() throws SQLException {
		
		System.out.println(service.getBook("2022001"));
	}
}
