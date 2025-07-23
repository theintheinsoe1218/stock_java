package com.tts.stock.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tts.stock.dao.UserAccountDao;
import com.tts.stock.domain.UserAccount;
import com.tts.stock.util.Cryption;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Value("${staticUi}")
	private String staticUi;
    @Autowired
    UserAccountDao userAccountDao;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId)
            throws UsernameNotFoundException {
    	UserAccount userAcc = userAccountDao.getUserAccountsById(Integer.parseInt(userId));
    	if(userAcc == null) {
    		throw new UsernameNotFoundException("User not found \"" + userId+ "\"");
    	}
    	//userAcc.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_"+userAcc.getAccounttype().getAccountTypeName())));
    	return userAcc;
    }
    
    @Transactional(readOnly = true)
    public LoginDto loginAccount(LoginDto loginDto) {
    	UserAccount userAccount = userAccountDao.getLoginAccount(loginDto.getUserName(),loginDto.getPassword());
    	
    	if(userAccount == null) {
    		throw new UsernameNotFoundException("User not found \"" + loginDto.getUserName()+ "\"");
    	}
    	//String password1 = passEncoder.encode(loginDto.getPassword());
    	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userAccount.getUserAccountId()+"", loginDto.getPassword());
    	Authentication authentication = authenticationManager.authenticate(token);
    	SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtTokenProvider.generateToken(authentication,loginDto.getPassword());// Cryption.encryption(
		loginDto.setPassword(jwtToken);
		loginDto.setRole(userAccount.getUserType());
		loginDto.setUserName(userAccount.getUsername());
		loginDto.setProfileName(userAccount.getProfileName());
		loginDto.setUserId(userAccount.getUserAccountId());
		loginDto.setDomain(staticUi);
		return loginDto;
    }
}