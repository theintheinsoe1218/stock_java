package com.tts.stock.service;

import java.util.List;

import com.tts.stock.dto.UserAccountDto;

public interface UserAccountService {

	UserAccountDto addUserAccounts(UserAccountDto dto);

	List<UserAccountDto> getUserAccounts(String userType);

	List<UserAccountDto> getUserList(String userType, String userName, int userId);

	int deleteUserList(int userAccountId);

	UserAccountDto updateUserList(UserAccountDto dto);
}
