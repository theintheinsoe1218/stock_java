package com.tts.stock.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tts.stock.dao.UserAccountDao;
import com.tts.stock.domain.UserAccount;
import com.tts.stock.dto.UserAccountDto;



@Service
public class UserAccountServiceImpl implements UserAccountService{
	@Autowired
	UserAccountDao uaDao;
	@Autowired
	PasswordEncoder passEncoder;
	
	@Transactional(readOnly=true)
	@Override
	public List<UserAccountDto> getUserAccounts(String userType) {
		// TODO Auto-generated method stub
		return uaDao.getUserAccounts(userType);
	}
	
	@Transactional(readOnly=false)
	@Override
	public UserAccountDto addUserAccounts(UserAccountDto dto) {
		// TODO Auto-generated method stub
		UserAccount ua = new UserAccount(dto);
		ua.setPassword(passEncoder.encode(dto.getPassword().toString().trim()));
//		ua.setBranchId(dto.getBranchDto().getBranchId());
		uaDao.addUserAccounts(ua);
		dto.setUserAccountId(ua.getUserAccountId());
		dto.setDate(new Date());
		dto.setModifiedDate(new Date());
		dto.setStatus(true);
		return dto;
	}

	@Transactional(readOnly=true)
	@Override
	public List<UserAccountDto> getUserList(String userType, String userName, int userId) {
		// TODO Auto-generated method stub
		if("none".equals(userName)) {
			if(userId==0) {
				return uaDao.getUserList(userType);
			}else {
				return uaDao.getUserListById(userId);
			}
		}else {
			return uaDao.getUserListByName(userName);
		}
		

	}

	
	@Transactional(readOnly=false)
	@Override
	public int deleteUserList(int userAccountId) {
		// TODO Auto-generated method stub
		//UserAccount ua = uaDao.getUserAccountsById(userAccountId);
		uaDao.deleteUserList(userAccountId);
		return userAccountId;

	}

	
	@Transactional(readOnly=false)
	@Override
	public UserAccountDto updateUserList(UserAccountDto dto) {
		// TODO Auto-generated method stub
		UserAccount ua=new UserAccount(dto);
		ua.setPassword(passEncoder.encode(dto.getPassword().toString().trim()));
//		ua.setBranchId(dto.getBranchDto().getBranchId());
		uaDao.updateUserList(ua);
		dto.setUserAccountId(ua.getUserAccountId());
		dto.setDate(ua.getDate());
		dto.setModifiedDate(new Date());
		dto.setStatus(true);
		return dto;
	}
}
