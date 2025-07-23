package com.tts.stock.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenData {
	String userId;
	String userName;
	String password;
	String role;
	String profileName;
	String profilePhoto;
}
