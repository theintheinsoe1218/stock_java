package com.tts.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tts.stock.dto.UserAccountDto;
import com.tts.stock.security.CustomUserDetailsService;
import com.tts.stock.security.LoginDto;
import com.tts.stock.service.UserAccountService;

@RestController
@RequestMapping("/api/v1/")
public class UserAccountController {
	@Autowired
	UserAccountService uaService;
	@Autowired 
	CustomUserDetailsService customUserDetailService;
	


	
	@GetMapping("user")
	public List<UserAccountDto> getUserAccounts(@RequestParam(name="userType",defaultValue = "ALL")String userType){
		return uaService.getUserAccounts(userType);
	}
	
	@GetMapping("user/login")
	public LoginDto login(@RequestParam("userName")String userName,@RequestParam("password")String password) {

		try {
				LoginDto loginDto = new LoginDto();
				loginDto.setUserName(userName.trim());
				loginDto.setPassword(password.toLowerCase().trim());
				LoginDto login = customUserDetailService.loginAccount(loginDto);
				return login;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("UserName and Password is wrong!", e);
		}
	}
	@PostMapping("userAccount")
	public UserAccountDto addUserAccounts(@RequestBody UserAccountDto dto){
		try {
			dto.setPassword(dto.getPassword().toLowerCase().trim());
			return uaService.addUserAccounts(dto);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Add, UserAccount Name is the same with other", e);
		}	
	}
	
	 @GetMapping("userAccount")
	 public List<UserAccountDto> getUserList(@RequestParam(name="userType",defaultValue = "ALL")String userType,
			 @RequestParam(name="userName",defaultValue = "none")String userName,
			 @RequestParam(name="userId",defaultValue = "0")String userIdSt){
		 int userId=Integer.parseInt(userIdSt);
		 return uaService.getUserList(userType,userName,userId);

	 }
	 
	 @PutMapping("userAccount/{userAccountId}")
	 public UserAccountDto updateUserList(@PathVariable("userAccountId")int userAccountId,@RequestBody UserAccountDto dto) {
		dto.setPassword(dto.getPassword().toLowerCase().trim());
		dto.setUserAccountId(userAccountId);

		return uaService.updateUserList(dto);

	 }
	 
	 @DeleteMapping("userAccount/{userAccountId}")
	 public int deleteUserList(@PathVariable("userAccountId")int userAccountId) {
		 return uaService.deleteUserList(userAccountId);
	 }
}
