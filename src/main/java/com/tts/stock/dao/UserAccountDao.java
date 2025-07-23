package com.tts.stock.dao;

import java.util.List;

import com.tts.stock.domain.UserAccount;
import com.tts.stock.dto.UserAccountDto;

public interface UserAccountDao {
	UserAccount getLoginAccount(String userName, String password);
	UserAccount getUserAccountsById(int userId);
	void addUserAccounts(UserAccount ua);
	List<UserAccountDto> getUserAccounts(String userType);
	List<UserAccountDto> getUserList(String userType);
	void deleteUserList(int userAccountId);
	void updateUserList(UserAccount ua);
	List<UserAccountDto> getUserListByName(String userName);
	List<UserAccountDto> getUserListById(int userId);
}
