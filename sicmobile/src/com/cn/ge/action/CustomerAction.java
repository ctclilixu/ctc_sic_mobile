package com.cn.ge.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Page;
import com.cn.ge.dto.CustomerDto;
import com.cn.ge.service.CustomerService;

/**
 * @name CustomerAction.java
 * @author Frank
 * @time 2016-2-23下午9:43:35
 * @version 1.0
 */
public class CustomerAction extends BaseAction {
	
	private static final long serialVersionUID = -1503255595611882464L;
	private static final Logger log = LogManager.getLogger(CustomerAction.class);
	
	private CustomerService customerService;
	
	/**
	 * 页码
	 */
	private int startIndex;
	
	/**
	 * 翻页
	 */
	private Page page;
	
	private String queryCompanyname;
	
	private List<CustomerDto> customerList;

	/**
	 * 客户管理页面
	 * @return
	 */
	public String showCustomerManageAction() {
		try {
			this.clearMessages();
			queryCompanyname = "";
			customerList = new ArrayList<CustomerDto>();
			page = new Page();
			startIndex = 0;
			queryData();
		} catch(Exception e) {
			log.error("showCustomerManageAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询客户
	 * @return
	 */
	public String queryCustomerAction() {
		try {
			this.clearMessages();
			startIndex = 0;
			page = new Page();
			queryData();
		} catch(Exception e) {
			log.error("queryCustomerAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnCustomerAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnCustomerAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询数据
	 */
	@SuppressWarnings("unchecked")
	private void queryData() {
		customerList = new ArrayList<CustomerDto>();
		if(page == null) {
			page = new Page();
		}
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = customerService.queryCustomerByPage(queryCompanyname, page);
		customerList = (List<CustomerDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<CustomerDto> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerDto> customerList) {
		this.customerList = customerList;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public String getQueryCompanyname() {
		return queryCompanyname;
	}

	public void setQueryCompanyname(String queryCompanyname) {
		this.queryCompanyname = queryCompanyname;
	}
}
