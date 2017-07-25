/**
 * 
 */
package com.incubator.common.datasource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author HDP23
 *
 */
public class DataSourceRouteInterceptor implements MethodInterceptor {

	public static Logger logger = LoggerFactory.getLogger(DataSourceRouteInterceptor.class);
	  private String defaultDsName = null;
	  
	  public Object invoke(MethodInvocation mi)
	    throws Throwable
	  {
	    Class<?> ifc = mi.getMethod().getDeclaringClass();
	    if (ifc.isInterface()) {
	      return mi.proceed();
	    }
	    String injectedDataSource = null;
	    DynamicDataSource dncDs;
	    if (mi.getMethod().isAnnotationPresent(DynamicDataSource.class))
	    {
	      dncDs = (DynamicDataSource)mi.getMethod().getAnnotation(DynamicDataSource.class);
	      injectedDataSource = dncDs.name();
	    }
	    if (StringUtils.isBlank(injectedDataSource)) {
	      injectedDataSource = getDefaultDsName();
	    }
	    DataSourceContextHolder.setDataSource(injectedDataSource);
	    
	    logger.debug("Inject DataSource from {{}} for method {{}} invoking", new Object[] { injectedDataSource, mi.getMethod() });
	    try
	    {
	      return mi.proceed();
	    }
	    finally
	    {
	      logger.debug("Reset DataSource from {{}} for method {{}} invoking", new Object[] { injectedDataSource, mi.getMethod() });
	      DataSourceContextHolder.clearDataSource();
	    }
	  }
	  
	  public String getDefaultDsName()
	  {
	    return this.defaultDsName;
	  }
	  
	  public void setDefaultDsName(String defaultDsName)
	  {
	    Assert.notNull(defaultDsName, "dataSource cannot be null");
	    this.defaultDsName = defaultDsName;
	  }

}
