package com.tts.stock.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	long timestamp;
	int status;
	String error;
	String message;
	String path;

}
