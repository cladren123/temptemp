package com.ssafy.ws.step3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String id;
	private String pass;
	private String name;
	private String recId;
}

/*

`id` VARCHAR(50) NOT NULL,
`name` VARCHAR(45) NOT NULL,
`pass` VARCHAR(100) NOT NULL,
`rec_id` VARCHAR(50) NULL

*/