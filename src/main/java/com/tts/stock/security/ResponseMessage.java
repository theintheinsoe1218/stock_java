package com.tts.stock.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
	public ResponseMessage(String message) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}

	private String message = "";
	private ResponseMessageType type;
	
	public static enum ResponseMessageType{
		SUCCESS, ERROR 
	}

}
