package com.cn.ge.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.ge.dao.CustomerDao;
import com.cn.ge.dto.CustomerDto;

/**
 * CustomerDaoImpl
 * @author Frank
 * @version 1.0
 * @create 2016-2-19下午5:04:22
 */
public class CustomerDaoImpl extends BaseDao implements CustomerDao {

	@Override
	public List<CustomerDto> queryCustomerByPage(String companyname,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("companyname", companyname);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<CustomerDto> list = getSqlMapClientTemplate().queryForList("queryCustomerByPage", paramMap);
		return list;
	}

	@Override
	public int queryCustomerCountByPage(String companyname) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("companyname", companyname);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryCustomerCountByPage", paramMap);
	}

	@Override
	public CustomerDto queryCustomerByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<CustomerDto> list = getSqlMapClientTemplate().queryForList("queryCustomerByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public CustomerDto queryCustomerByPhone(String phone) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("phone", phone);
		@SuppressWarnings("unchecked")
		List<CustomerDto> list = getSqlMapClientTemplate().queryForList("queryCustomerByPhone", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteCustomer(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		getSqlMapClientTemplate().delete("deleteCustomer", paramMap);
	}

	@Override
	public void insertCustomer(CustomerDto customer) {
		getSqlMapClientTemplate().insert("insertCustomer", customer);
	}

	@Override
	public void updateCustomer(CustomerDto customer) {
		getSqlMapClientTemplate().update("updateCustomer", customer);
	}
}
