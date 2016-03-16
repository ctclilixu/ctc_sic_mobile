package com.cn.ge.dao;

import java.util.List;

import com.cn.ge.dto.CustomerDto;

public interface CustomerDao {

	/**
	 * 翻页查询客户
	 * @param companyname
	 * @param start
	 * @param end
	 * @return
	 */
	public List<CustomerDto> queryCustomerByPage(String companyname, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param companyname
	 * @return
	 */
	public int queryCustomerCountByPage(String companyname);
	
	/**
	 * 根据登录ID查询客户
	 * @param id
	 * @return
	 */
	public CustomerDto queryCustomerByID(String id);
	
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
