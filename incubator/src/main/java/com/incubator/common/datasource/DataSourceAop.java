/**
 * 
 */
package com.incubator.common.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HDP23
 *
 */

//@Named("dsAspectBean")
public class DataSourceAop {
	public static Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

	@Before("@annotation(dynamicDataSource)")
	public void injectDataSource(JoinPoint jp, DynamicDataSource dynamicDataSource) {
		logger.debug("injectDataSource:" + dynamicDataSource.name());
		DataSourceContextHolder.setDataSource(dynamicDataSource.name());
	}

	@After("@annotation(dynamicDataSource)")
	public void restDataSource(JoinPoint jp, DynamicDataSource dynamicDataSource) {
		logger.debug("restDataSource:" + dynamicDataSource.name());

		DataSourceContextHolder.clearDataSource();
	}
}
