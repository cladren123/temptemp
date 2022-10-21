package com.ssafy.ws.step3.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.ws.step3.dto.Book;

public interface BookDao {

	int insert(Book book) throws SQLException;
	List<Book> select() throws SQLException;
	
	Book getBook(String isbn) throws SQLException; // 자세히 보기
	
	int remove(String isbn) throws SQLException; // 삭제
	int update(Book book) throws SQLException; // 업데이트
	
	List<Book> select(String condition, String keyword) throws SQLException;
}
