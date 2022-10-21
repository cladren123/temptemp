package com.ssafy.ws.step3.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.ws.step3.dao.BookDao;
import com.ssafy.ws.step3.dto.Book;

public class BookServiceImpl implements BookService {

	BookDao dao;
	
	public BookServiceImpl() {	}

	public BookServiceImpl(BookDao dao) {
		this.dao = dao;
	}

	public void setDao(BookDao dao) {
		this.dao = dao;
	}

	@Override
	public int insert(Book book) throws SQLException {
		return dao.insert(book);
	}

	@Override
	public List<Book> select() throws SQLException {
		return dao.select();
	}

	@Override
	public Book getBook(String isbn) throws SQLException {
		return dao.getBook(isbn);
	}

	@Override
	public int remove(String isbn) throws SQLException {
		return dao.remove(isbn);
	}

	@Override
	public int update(Book book) throws SQLException {
		return dao.update(book);
	}

	@Override
	public List<Book> select(String condition, String keyword) throws SQLException {
		return dao.select(condition, keyword);
	}



}
