package com.tts.stock.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tts.stock.domain.UserAccount;
import com.tts.stock.util.Cryption;
import com.tts.stock.util.DateTimeFormatDeserializer;
import com.tts.stock.util.DateTimeFormatSerializer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto{
	public UserAccountDto(UserAccount ua) {
		// TODO Auto-generated constructor stub
		this.userAccountId = ua.getUserAccountId();
//		this.branchId = ua.getBranchId();
		this.profileName = ua.getProfileName();
		this.userName = ua.getUsername();
		this.phone = ua.getPhone();
		this.address = ua.getAddress();
		this.userType  = ua.getUserType();
		this.status = ua.isStatus();
		this.date = ua.getDate();
		this.modifiedDate = ua.getModifiedDate();
	}
	public UserAccountDto(int userAccountId, String profileName, String userName, String phone, String nrcNo,
			String usertype, boolean status, String address, String remark, String password, String epassword) {
		// TODO Auto-generated constructor stub
		this.userAccountId = userAccountId;
		this.profileName = profileName;
		this.userName = userName;
		this.phone = phone;
		this.userType = usertype;
		this.status = status;
		this.address = address;
		this.remark = remark;
	}
	public UserAccountDto(int userAccountId, String profileName) {
		// TODO Auto-generated constructor stub
		this.userAccountId = userAccountId;
		this.profileName = profileName;
	}
	public UserAccountDto(int userAccountId, String profileName, String userName, String phone, String nrcNo,
			String usertype, boolean status, String address, String remark,Date date) {
		// TODO Auto-generated constructor stub
		this.userAccountId = userAccountId;
		this.profileName = profileName;
		this.userName = userName;
		this.phone = phone;
		this.userType = usertype;
		this.status = status;
		this.address = address;
		this.remark = remark;
		this.date = date;
	}
	public UserAccountDto(int userAccountId, String profileName, String userName, String phone, String usertype,
			boolean status, String address, String remark, String password, String epassword) {
		// TODO Auto-generated constructor stub
		this.userAccountId = userAccountId;
		this.profileName = profileName;
		this.userName = userName;
		this.phone = phone;
		this.userType = userType;
		this.status = status;
		this.address = address;
		this.remark = remark;
		this.password = password;
		this.encryptPassword = epassword;
	}
	public UserAccountDto(int userAccountId, String profileName, String userName, String phone, String usertype,
			boolean status, String address, String remark, Date date) {
		// TODO Auto-generated constructor stub
		this.userAccountId = userAccountId;
		this.userName = userName;
		this.phone = phone;
		this.userType = userType;
		this.status = status;
		this.address = address;
		this.remark = remark;
		this.date = date;
	}
	private int userAccountId;
	private String profileName;
	private String userName;
	private String password;
	private String encryptPassword;
	private String phone;
	private String address;
	private String remark;
	private String userType;
	private boolean status;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date date;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date modifiedDate;
}
