package com.tts.stock.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        //logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
        
       ApiResponse response = new ApiResponse();
       response.setTimestamp(new Date().getTime());
       response.setStatus(401);
       response.setError("Unauthorized");
       response.setMessage(e.getMessage());
       response.setPath(httpServletRequest.getPathTranslated());
       httpServletResponse.setContentType("application/json");
       httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
       OutputStream out = httpServletResponse.getOutputStream();
       
       ObjectMapper mapper = new ObjectMapper();
       mapper.writeValue(out, response);
       out.flush();
        
    }
}