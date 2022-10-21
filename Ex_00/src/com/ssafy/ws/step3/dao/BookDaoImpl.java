package com.ssafy.ws.step3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.ws.step3.dto.Book;
import com.ssafy.ws.step3.util.DBUtil;

public class BookDaoImpl implements BookDao {

	DBUtil util = DBUtil.getInstance();
	
	@Override
	public int insert(Book book) throws SQLException {
//		String sql = "insert into book values(?, ?, ?, ?, ?, ?)";
		String sql = "insert into book values(?, ?, ?, ?, ?, null)";

		Connection conn = null;
		PreparedStatement ps = null;
		int row = 0;
		
		try {
			conn = util.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setInt(4, book.getPrice());
			ps.setString(5, book.getDesc());
//			ps.setString(6, book.getImg());
			
			row = ps.executeUpdate();
			
		} finally {
			util.close(ps, conn);
		}
		return row;
	}

	@Override
	public List<Book> select() throws SQLException {
//		String sql = "select * from book limit ?, ?"; // 페이징 처리
		String sql = "select * from book limit 0, 10";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Book> books = new ArrayList<>();
		
		try {
			conn = util.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setDesc(rs.getString("desc"));
				book.setImg(rs.getString("img"));
				
				books.add(book);
			}
			
		} finally {
			util.close(rs, ps, conn);
		}
		
		return books;
	}

	@Override
	public Book getBook(String isbn) throws SQLException {
		String sql = "select * from book where isbn = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		
		try {
			conn = util.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			rs = ps.executeQuery();
			
		
			while (rs.next()) {
				book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setDesc(rs.getString("desc"));
				book.setImg(rs.getString("img"));
			}
			
		} finally {
			util.close(rs, ps, conn);
		}
		
		return book;
	}

	@Override
	public int remove(String isbn) throws SQLException {
		String sql = "delete from book where isbn=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		int row = -1;
		
		try {
			conn = util.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, isbn);
			row = ps.executeUpdate();
			
		} finally {
			util.close(ps, conn);
		}
		return row;
	}

	@Override
	public int update(Book book) throws SQLException {
		String sql = "update book set title=?, author=?, price=?, `desc`=? where isbn=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		int row = 0;
		
		System.out.println("BOOK_DAO : " + book);
		try {
			conn = util.getConnection();
			ps = conn.prepareStatement(sql);
			
			int idx = 0;
			ps.setString(++idx, book.getTitle());
			ps.setString(++idx, book.getAuthor());
			ps.setInt(++idx, book.getPrice());
			ps.setString(++idx, book.getDesc());
			ps.setString(++idx, book.getIsbn());
			
			row = ps.executeUpdate();
			
		} finally {
			util.close(ps, conn);
		}
		return row;
	}

	@Override
	public List<Book> select(String condition, String keyword) throws SQLException {
		String sql = "select * from book where " + condition + " like ? limit 0, 10";
//		String sql = "select * from book where " + condition + " like ? limit ?, ?"; // 페이징 처리 필요한 경우
//		String sql = "select * from book where ? like ? limit 0, 10"; // <주의> 이렇게 할 수 없다...!
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Book> books = new ArrayList<>();
		
		try {
			conn = util.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setDesc(rs.getString("desc"));
				book.setImg(rs.getString("img"));
				
				books.add(book);
			}
			
		} finally {
			util.close(rs, ps, conn);
		}
		
		return books;
	}




}
