/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class SitcomUtils {

	private static final Logger logger = LogManager.getLogger(SitcomUtils.class);

	/**
	 * Get Remote IP Address
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
		if (ipFromHeader != null && ipFromHeader.length() > 0) {
			logger.info("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
			return ipFromHeader;
		}
		return request.getRemoteAddr();
	}
	
	/**
	 * Remove Duplicates from Generic List Using Native API
	 * 
	 * @param request
	 * @return
	 */
	public static <T> List<T> removeDuplicateItemsInList(List<T> request) {
		if (request == null) {
			return null;
		}
		return Lists.newArrayList(Sets.newHashSet(request));
	}
}
