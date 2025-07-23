package com.tts.stock.domain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tts.stock.dto.UserAccountDto;
import com.tts.stock.util.Cryption;
@Entity
@Table(name = "useraccount")
public class UserAccount implements UserDetails  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	private Date date;
	private Date modifiedDate;

	public UserAccount() {
		super();
	}

	public UserAccount(UserAccountDto dto) {
		// TODO Auto-generated constructor stub
		this.userAccountId=dto.getUserAccountId();
//		this.branchId =dto.getBranchId();
		this.profileName=dto.getProfileName();
		this.userName=dto.getUserName();
		this.password=dto.getPassword();
		this.encryptPassword=Cryption.encryption(dto.getPassword());
		this.phone=dto.getPhone();

		this.address=dto.getAddress();
		this.remark=dto.getRemark();
		if(this.userAccountId == 0) {
			this.date = new Date();
		}else {
			this.date = dto.getDate();
		}
		this.modifiedDate = new Date();
		this.userType = dto.getUserType();
		this.status = true;
	}

	public int getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}



	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority granted = new SimpleGrantedAuthority("ROLE_"+getUserType());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(granted);
		return authorities;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
			return this.isStatus();
	}
	@Transient
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getUserName(); 
	}
	
}
