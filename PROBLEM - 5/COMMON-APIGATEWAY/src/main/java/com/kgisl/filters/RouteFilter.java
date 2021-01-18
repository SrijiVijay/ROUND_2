package com.kgisl.filters;

import com.kgisl.utils.ZuulConstant;
import com.netflix.zuul.ZuulFilter;

/**
 * RouteFilter class which will be used to route the request.
 *
 * @author sriji
 * @version 1.0
 * @since Jan, 05 2021
 */
public class RouteFilter extends ZuulFilter {
	
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
		return ZuulConstant.ROUTE;
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
		System.out.println("Inside Route Filter");
		return null;
	}

}
