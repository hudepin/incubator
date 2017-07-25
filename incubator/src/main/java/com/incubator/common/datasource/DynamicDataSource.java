/**
 * 
 */
package com.incubator.common.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author HDP23
 *
 */
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSource {
	String name() default DynamicDataSource.master;
	 
    public static String master = "dataSourceMaster";
 
    public static String slave1 = "dataSourceSlave1";
 
    public static String slave2 = "dataSourceSlave2";
}
