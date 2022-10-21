package com.ssafy.ws.step3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private String isbn;
	private String title;
	private String author;
	private int price;
	private String desc;
	private String img;
	
	// 다른 테이블과 조인한다면 해당 칼럼명이 추가로 들어올 수 있다.
	// DB의 칼럼명을 그대로 사용하기 : 언더바 => 대문자
}

/*

`isbn` CHAR(12) NOT NULL,
`title` VARCHAR(100) NOT NULL,
`author` VARCHAR(50) NOT NULL,
`price` INT NOT NULL,
`desc` TEXT NULL,
`img` VARCHAR(100) NULL

*/