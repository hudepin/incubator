/**
 * 
 */
package com.incubator.common.datasource;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;


/**
 * @author HDP23
 *
 */
public class DataSourceContextHolder {
	
		  public static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);
		  private static ThreadLocal<Stack<String>> DS_CTX_STACK_HOLDER = new ThreadLocal()
		  {
		    protected Stack<String> initialValue()
		    {
		      return new Stack();
		    }
		  };
		  
		  public static void setDataSource(String dataSource)
		  {
		    Assert.notNull(dataSource, "dataSource cannot be null");
		    
		    Stack<String> ctxStatck = (Stack)DS_CTX_STACK_HOLDER.get();
		    
		    ctxStatck.push(dataSource);
		    
		    logger.debug("Stack 'After Push DataSource' Count {{}}", new Object[] { Integer.valueOf(ctxStatck.size()) });
		  }
		  
		  public static String getDataSource()
		  {
		    Stack<String> ctxStatck = (Stack)DS_CTX_STACK_HOLDER.get();
		    if (ctxStatck == null)
		    {
		      DS_CTX_STACK_HOLDER.remove();
		      ctxStatck = (Stack)DS_CTX_STACK_HOLDER.get();
		    }
		    if (ctxStatck.isEmpty()) {
		      return null;
		    }
		    return (String)ctxStatck.peek();
		  }
		  
		  public static void clearDataSource()
		  {
		    Stack<String> ctxStack = (Stack)DS_CTX_STACK_HOLDER.get();
		    
		    logger.debug("Stack 'Before POP DataSource' Size {{}}", new Object[] { Integer.valueOf(ctxStack.size()) });
		    if ((ctxStack != null) && (!ctxStack.isEmpty())) {
		      ctxStack.pop();
		    }
		  }
}