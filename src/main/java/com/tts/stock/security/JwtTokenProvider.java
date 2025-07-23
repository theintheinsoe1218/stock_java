package com.tts.stock.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tts.stock.domain.UserAccount;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication, String password) {
        UserAccount userPrincipal = (UserAccount) authentication.getPrincipal();

        //Date now = new Date();
        //Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        ObjectMapper mapper = new ObjectMapper();
        TokenData tokenData = new TokenData();
        tokenData.setUserId(userPrincipal.getUserAccountId()+"");
        tokenData.setPassword(password);
        tokenData.setUserName(userPrincipal.getUserName());
        tokenData.setRole(userPrincipal.getUserType());
        tokenData.setProfileName(userPrincipal.getProfileName());
        String subject = "";
        try {
			subject = mapper.writeValueAsString(tokenData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setIssuer(userPrincipal.getUserAccountId()+"")
                //.setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    
    public String getSubject(String token) {
    	 Claims claims = Jwts.parser()
                 .setSigningKey(jwtSecret)
                 .parseClaimsJws(token)
                 .getBody();
         return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}