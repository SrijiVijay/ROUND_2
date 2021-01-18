package com.kgisl.filters;

import javax.servlet.http.HttpServletRequest;

import com.kgisl.utils.ZuulConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * PreFilter class which will be invoked before the request is routed.
 *
 * @author sriji
 * @version 1.0
 * @since Jan, 05 2021
 */
public class PreFilter extends ZuulFilter {
	
	/**
	 * Filter type.
	 *
	 * @return the string
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		return ZuulConstant.PRE;
	}

	/**
	 * Filter order.
	 *
	 * @return the int
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

	/**
	 * Should filter.
	 *
	 * @return true, if successful
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * Run.
	 *
	 * @return the object
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		System.out.println(
				"Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
		return null;
	}
}
