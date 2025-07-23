package com.tts.stock.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tts.stock.util.Cryption;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
   // private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            	ObjectMapper objectMapper = new ObjectMapper();
            	String subject = tokenProvider.getSubject(jwt);
//            	if("".equals(subject)) {
//            		
//            	}
            	TokenData tokenData = objectMapper.readValue(subject, TokenData.class); 
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(tokenData.getUserId()+"", tokenData.getPassword());//Cryption.decryption(
                authentication.setDetails(tokenData);  
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (BadCredentialsException bce) {
    		bce.printStackTrace();
    	}
        catch (Exception ex) {
            //logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) ) {//&& bearerToken.startsWith("chat")
            return bearerToken;
        }
        return null;
    }
}