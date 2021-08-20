package com.cheetah.istat.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cheetah.istat.IstatApiApplication;

public class IstatInterceptor implements HandlerInterceptor {
	
	private Logger LOG = LoggerFactory.getLogger(IstatInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug("IstatInterceptor.preHandle");
		}
		if(IstatApiApplication.available) {
			return true;
		}
		throw new ServiceIstatUnavailableException("Service temporaly unavailable");
	}

	

	
}
