package com.tts.stock.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {
	private String userName;
	private String password;
	private String role;
	private String profileName;
	private String domain;
	private int userId;
	private String profilePhoto;
}
