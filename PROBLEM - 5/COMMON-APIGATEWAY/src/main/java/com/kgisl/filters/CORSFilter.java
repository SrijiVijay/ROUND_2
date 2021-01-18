package com.kgisl.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * The Class CORSFilter.
 */
@Component
public class CORSFilter implements Filter {

	/** The config. */
	private FilterConfig config;

	/** The Constant CREDENTIALS_NAME. */
	public static final String CREDENTIALS_NAME = "Access-Control-Allow-Credentials";
	
	/** The Constant ORIGIN_NAME. */
	public static final String ORIGIN_NAME = "Access-Control-Allow-Origin";
	
	/** The Constant METHODS_NAME. */
	public static final String METHODS_NAME = "Access-Control-Allow-Methods";
	
	/** The Constant HEADERS_NAME. */
	public static final String HEADERS_NAME = "Access-Control-Allow-Headers";
	
	/** The Constant MAX_AGE_NAME. */
	public static final String MAX_AGE_NAME = "Access-Control-Max-Age";

	/**
	 * Destroy.
	 */
	@Override
	public void destroy() {

	}

	/**
	 * Do filter.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @param chain the chain
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers","*");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, resp);
		}

	}

	/**
	 * Inits the.
	 *
	 * @param filterConfig the filter config
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}
}