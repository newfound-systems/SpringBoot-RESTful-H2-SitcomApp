/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.interceptors;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LogManager.getLogger(LogInterceptor.class);

	/**
	 * Before the actual handler will be executed
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);

		String reqMethod = request.getMethod();
		StringBuffer reqUrl = request.getRequestURL();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		String reqPath = reqUrl.toString();

		Principal user = request.getUserPrincipal();
		String username = "";
		if(user != null) {
			username =StringUtils.trimToEmpty(user.getName());
		}
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		logger.info("User: " + username + " Method: " + reqMethod + " from: " + ipAddress + " path: " + reqPath);

		return true;
	}

	/**
	 * Post Handler Execution
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;

		String reqMethod = request.getMethod();
		StringBuffer reqUrl = request.getRequestURL();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		String reqPath = reqUrl.toString();

		Principal user = request.getUserPrincipal();
		String username = StringUtils.trimToEmpty(user.getName());

		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		logger.info("User: " + username + " Method: " + reqMethod + " from: " + ipAddress + " path: " + reqPath
				+ " took " + executeTime + " msecs");
	}

	/**
	 * After Completion
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("---Request Completed---");
	}
}
