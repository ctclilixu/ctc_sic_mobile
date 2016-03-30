package com.cn.ge.service;

import com.cn.common.util.Page;
import com.cn.ge.dto.CustomerDto;

public interface CustomerService {

	/**
	 * 翻页查询客户
	 * @param companyname
	 * @param page
	 * @return
	 */
	public Page queryCustomerByPage(String companyname, Page page);
	
	/**
	 * 根据登录ID查询客户
	 * @param id
	 * @return
	 */
	public CustomerDto queryCustomerByID(String id);
	
	/**
	 * 根据手机号码查询客户信息
	 * @param phone
	 * @return
	 */
	public CustomerDto queryCustomerByPhone(String phone);
	
	/**
	 * 删除客户
	 * @param id
	 */
	public void deleteCustomer(String id);
	
	/**
	 * 新增客户
	 * @param customer
	 */
	public void insertCustomer(CustomerDto customer);
	
	/**
	 * 修改客户
	 * @param customer
	 */
	public void updateCustomer(CustomerDto customer);
}
