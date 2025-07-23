package com.tts.stock;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestServiceInterceptor implements HandlerInterceptor,Filter {
	@Value("${domain}")
	private String domain;
	//private static final Logger LOGGER = LogManager.getLogger(RequestServiceInterceptor.class);
	@Override
	  public void doFilter(ServletRequest request, ServletResponse response,
	                FilterChain chain) throws IOException, ServletException {
		      String domainName = domain;
		      if(!"".equals(domainName)) {
		    	  String requestURL =
				            ((HttpServletRequest) request).getRequestURL().toString();
		    if (requestURL.contains(domainName)) {
		    	if (!requestURL.contains("www")) {
		      String newRequestURL =
		              requestURL.replace(domainName, "www." + domainName);
		      ((HttpServletResponse) response).setStatus(301);
		      ((HttpServletResponse) response).setHeader("Location", newRequestURL);
		    	}
		    	chain.doFilter(request, response);
		    }else {
		    	((HttpServletResponse) response).setHeader("Location", requestURL);
			      chain.doFilter(request, response);
		    }
		      }else {
				     ((HttpServletResponse) response).setHeader("Location","localhost");
				    chain.doFilter(request, response);
		      }
	  }
   @Override
   public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      System.out.println("URL : "+request.getRequestURI() + " : " + request.getMethod());
      return true;
   }
   @Override
   public void postHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler, 
      ModelAndView modelAndView) throws Exception {}
   
   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
      Object handler, Exception exception) throws Exception {}
}