package com.cn.ge.action;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.mail.MailSender;
import com.cn.common.util.StringUtil;
import com.cn.ge.dto.CustomerDto;
import com.cn.ge.service.CustomerService;

/**
 * @name WebApplyAction.java
 * @author Frank
 * @time 2016-2-28下午3:07:15
 * @version 1.0
 */
public class WebApplyAction extends BaseAction {

	private static final long serialVersionUID = 3499144847487497501L;
	private static final Logger log = LogManager.getLogger(WebApplyAction.class);
	
	private CustomerService customerService;
	
	private CustomerDto addCustomerDto;
	
	/**
	 * 申请页面
	 * @return
	 */
	public String showApplyAction() {
		try {
			this.clearMessages();
			addCustomerDto = new CustomerDto();
		} catch(Exception e) {
			log.error("showApplyAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 申请提交
	 * @return
	 */
	public String applyAction() {
		try {
			this.clearMessages();
			//数据验证
			if(addCustomerDto == null) {
				this.addActionMessage("客户名称不能为空！");
				return "checkerr";
			}
			if(StringUtil.isBlank(addCustomerDto.getCustomername())) {
				this.addActionMessage("客户名称不能为空！");
				return "checkerr";
			}
			if(StringUtil.isBlank(addCustomerDto.getCompanyname())) {
				this.addActionMessage("企业名称不能为空！");
				return "checkerr";
			}
			if(StringUtil.isBlank(addCustomerDto.getPhone())) {
				this.addActionMessage("手机号码不能为空！");
				return "checkerr";
			}
			if(!StringUtil.isMobile(addCustomerDto.getPhone())) {
				this.addActionMessage("手机号码格式不正确！");
				return "checkerr";
			}
			if(StringUtil.isBlank(addCustomerDto.getMail())) {
				this.addActionMessage("邮箱不能为空！");
				return "checkerr";
			}
			if(!StringUtil.isEmail(addCustomerDto.getMail())) {
				this.addActionMessage("邮箱格式不正确！");
				return "checkerr";
			}
			//新增客户信息
			//客户手机号码为唯一
			
			//状态=申请
			addCustomerDto.setStatus(10);
			addCustomerDto.setCreateuser(this.getIP());
			addCustomerDto.setUpdateuser(this.getIP());
			customerService.insertCustomer(addCustomerDto);
			addCustomerDto = new CustomerDto();
			
//			//邮件发送人，MailSender有默认发送人。
//			String from = "";
//			//收件人姓名，MailSender有默认收件人。
//			String to = "";
//			String subject = "邮件主题";
//			String body = "邮件内容";
//			//发件人名
//			String username = "发件人名";
//			//附件，格式：filename1,filename2,filename3...（这里需要在global.properties配置文件中指定附件目录）
//			String attachfile = "";
//			MailSender.send(from, to, subject, body, username, attachfile);
		} catch(Exception e) {
			log.error("applyAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public CustomerDto getAddCustomerDto() {
		return addCustomerDto;
	}

	public void setAddCustomerDto(CustomerDto addCustomerDto) {
		this.addCustomerDto = addCustomerDto;
	}
}
