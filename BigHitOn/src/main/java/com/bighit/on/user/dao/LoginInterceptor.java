package com.bighit.on.user.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
	            throws Exception {
	        
		 LOG.debug("----------------------------");
		 LOG.debug("-LoginInterceptor-");
		 LOG.debug("----------------------------");
	 
	        if(req.getSession().getAttribute("usersVO") == null) {
	            res.sendRedirect("/on/users/loginView.do");
	            
	            return false;
	        } else {
	        	LOG.debug("-usersVO-" + req.getSession().getAttribute("usersVO"));
	        }
	        return true;
	    }
}
