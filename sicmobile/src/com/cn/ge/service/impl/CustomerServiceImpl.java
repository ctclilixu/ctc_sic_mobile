package com.cn.ge.service.impl;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.ge.dao.CustomerDao;
import com.cn.ge.dto.CustomerDto;
import com.cn.ge.service.CustomerService;

/**
 * CustomerServiceImpl
 * @author Frank
 * @version 1.0
 * @create 2016-2-19下午4:46:31
 */
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;

	@Override
	public Page queryCustomerByPage(String companyname, Page page) {
		companyname = StringUtil.replaceDatabaseKeyword_mysql(companyname);
		//查询总记录数
		int totalCount = customerDao.queryCustomerCountByPage(companyname);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<CustomerDto> list = customerDao.queryCustomerByPage(companyname,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public CustomerDto queryCustomerByID(String id) {
		return customerDao.queryCustomerByID(id);
	}

	@Override
	public void deleteCustomer(String id) {
		customerDao.deleteCustomer(id);
	}

	@Override
	public void insertCustomer(CustomerDto customer) {
		customerDao.insertCustomer(customer);
	}

	@Override
	public void updateCustomer(CustomerDto customer) {
		customerDao.updateCustomer(customer);
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
}
