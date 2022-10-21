package com.ssafy.ws.step3.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.ws.step3.dao.BookDao;
import com.ssafy.ws.step3.dao.BookDaoImpl;
import com.ssafy.ws.step3.dao.UserDao;
import com.ssafy.ws.step3.dao.UserDaoImpl;
import com.ssafy.ws.step3.dto.Book;
import com.ssafy.ws.step3.dto.User;
import com.ssafy.ws.step3.service.BookService;
import com.ssafy.ws.step3.service.BookServiceImpl;
import com.ssafy.ws.step3.service.UserService;
import com.ssafy.ws.step3.service.UserServiceImpl;


@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDao userDao = new UserDaoImpl();
	UserService userService = new UserServiceImpl(userDao);
	
	BookDao bookDao = new BookDaoImpl();
	BookService bookService = new BookServiceImpl(bookDao);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);

	}


	private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String cmd = request.getParameter("action");
		
		// 핸들러 매핑 (URL에 따른 동작을 매핑)
		switch (cmd) {
		case "login": // 로그인
			doLogin(request, response);
			break;
		case "logout": // 로그아웃
			doLogout(request, response);
			break;
		case "regist": // 도서 등록 처리
			doRegist(request, response);
			break;
		case "list": // 도서 목록 출력, 키워드를 통한 도서 검색
			doList(request, response);
			break;
		case "view": // 자세히 보기
			doView(request, response);
			break;
		case "delete": // 삭제하기
			doDel(request, response);
			break;
		case "modify": // 수정하기(이동)
			doModify(request, response);
			break;
		case "update": // 수정사항 반영
			doUpdate(request, response);
			break;
		default:
			break;
		}
	}

	

	// 로그인 처리
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		User user;
		try {
			user = userService.select(id);
			
			System.out.println("로그인하려는 사용자 : " + user);
			if (user != null && pass.equals(user.getPass())) {
				
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);
				
				Cookie cookie = new Cookie("loginUser", URLEncoder.encode(id, "utf-8")); // 쿠키 생성
				cookie.setPath("/");
				cookie.setMaxAge(10*60); // 10분
				response.addCookie(cookie); // 클라이언트에게 만든 쿠키 보내주기
				
				response.sendRedirect(request.getContextPath() + "/index.jsp"); // 로그인 성공 후 메인으로 리다이렉트
				
			} else {
				request.setAttribute("msg", "로그인 실패");
				
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			// M1) 대략적인 오류 출력
			response.sendError(500);
			
			// M2) 구체적인 오류 출력 => 보안 취약점 발생
			// 서비스를 할 때에는 에러 메시지를 출력해주지 않도록 해야 한다 => 보안 취약점 발생
//			request.setAttribute("exception", e);
//			RequestDispatcher rd = request.getRequestDispatcher("/error/500.jsp");
//			rd.forward(request, response);
		}
		
	}
	
	// 로그아웃 처리
	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate(); // 세션 파괴
		
		response.sendRedirect(request.getContextPath() + "/index.jsp"); // 로그인 성공 후 메인으로 리다이렉트				
	}
	
	// 도서 등록 처리
	private void doRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request에서 정보 꺼내기
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String desc = request.getParameter("desc");
		
		Book book = new Book(isbn, title, author, price, desc, null);
		
		try {
			bookService.insert(book);
//			request.setAttribute("book", book);
//			RequestDispatcher rd = request.getRequestDispatcher("/regist_result.jsp");
//			rd.forward(request, response);
			
			// 책 등록 후 리스트 화면으로 이동(리다이렉트)
			response.sendRedirect(request.getContextPath() + "/main?action=list"); 

		} catch (SQLIntegrityConstraintViolationException e) {
			request.setAttribute("msg", "도서 등록 실패 " + isbn + "이미 등록된 isbn 번호입니다.");
			request.setAttribute("book", book); // 도서 등록 실패 시, 이미 입력한 데이터 페이지에 남겨주기 위해 request 객체에 저장
			
			RequestDispatcher rd = request.getRequestDispatcher("/regist.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
		
	}

	// 도서 목록 출력
	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 임의로 도서 목록을 아래처럼 구성한다.
		int pageNo = ( request.getParameter("pageNo") == null )? 1: Integer.parseInt(request.getParameter("pageNo"));
		int end = pageNo * 10;
		int start = end - 10;
		
		List<Book> books = null;
			
		try {
			if (request.getParameter("condition") == null) { // 검색 키워드가 없는 경우
//				books = bookService.select(start, end);
				books = bookService.select();
			} else {
//				books = bookService.select(request.getParameter("condition"), request.getParameter("keyword"), start, end);
				books = bookService.select(request.getParameter("condition"), request.getParameter("keyword"));
			}
			request.setAttribute("books", books);

			// forward를 통해 list.jsp를 호출한다.
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
		
	}
	
	
	// 상세보기
	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Book book = bookService.getBook(request.getParameter("isbn"));
			request.setAttribute("book", book);

			// forward를 통해 list.jsp를 호출한다.
			RequestDispatcher rd = request.getRequestDispatcher("/book_view.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	} // doView
	
	
	// 삭제하기
	private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] isbns = request.getParameterValues("isbn");
		
		try {
//			bookService.deleteBook(request.getParameter("isbn")); // 한 개만 삭제할 때
			for(String isbn :isbns) {
				bookService.remove(isbn);
			}
			response.sendRedirect(request.getContextPath()+"/main?action=list");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	} // doDel
	
	
	// 수정하기로 이동
	private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Book book = bookService.getBook(request.getParameter("isbn"));
			request.setAttribute("book", book);

			// forward를 통해 list.jsp를 호출한다.
			RequestDispatcher rd = request.getRequestDispatcher("/book_modify.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	} // doModify
	
	
	// 수정사항 반영
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request에서 정보 꺼내기
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String desc = request.getParameter("desc");
		
		Book book = new Book(isbn, title, author, price, desc, null);
		System.out.println(book);
		
		try {
			bookService.update(book);
			
			// 책 등록 후 리스트 화면으로 이동(리다이렉트)
			response.sendRedirect(request.getContextPath() + "/main?action=view&isbn=" + isbn);

		} catch (SQLIntegrityConstraintViolationException e) {
			request.setAttribute("msg", "도서 정보 수정 실패 " + isbn);
			request.setAttribute("book", book); // 도서 정보 수정 실패 시, 이미 입력한 데이터 페이지에 남겨주기 위해 request 객체에 저장
			
			response.sendRedirect(request.getContextPath() + "/main?action=view&isbn=" + isbn);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
		
	}

}
