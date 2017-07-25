/**
 * 
 */
package com.incubator.service.impl;

import org.springframework.stereotype.Service;

import com.incubator.common.datasource.DynamicDataSource;
import com.incubator.service.IUserService;

/**
 * @author HDP23
 *
 */
@Service
public class UserServiceImpl implements IUserService{
	@DynamicDataSource(name=DynamicDataSource.slave1)
	@Override
	public void getUser() {
		System.out.println("getUser slave1");
	}

}
