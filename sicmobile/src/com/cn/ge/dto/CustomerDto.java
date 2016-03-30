package com.cn.ge.dto;

import com.cn.common.dto.BaseDto;

/**
 * 客户信息Dto
 * @author Frank
 * @version 1.0
 * @create 2016-2-18下午3:45:23
 */
public class CustomerDto extends BaseDto {

	private static final long serialVersionUID = -5142792566985144735L;

	/**
	 * 客户ID
	 */
	private Integer id;

	/**
	 * 客户名
	 */
	private String customername;
	
	/**
	 * 客户提交申请的资料名
	 */
	private String docname;

	/**
	 * 客户公司名称
	 */
	private String companyname;

	/**
	 * 客户公司地址
	 */
	private String companyaddress;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 联系电话
	 */
	private String tell;

	/**
	 * 传真
	 */
	private String fax;

	/**
	 * 邮箱
	 */
	private String mail;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 状态：0无效，10申请，20审核通过，30审核驳回
	 */
	private Integer status;

	/**
	 * 备用字段1
	 */
	private String res01;

	/**
	 * 备用字段2
	 */
	private String res02;

	/**
	 * 备用字段3
	 */
	private String res03;

	/**
	 * 备用字段4
	 */
	private String res04;

	/**
	 * 备用字段5
	 */
	private String res05;

	/**
	 * 备用字段6
	 */
	private String res06;

	/**
	 * 备用字段7
	 */
	private String res07;

	/**
	 * 备用字段8
	 */
	private String res08;

	/**
	 * 备用字段9
	 */
	private String res09;

	/**
	 * 备用字段10
	 */
	private String res10;

	/**
	 * 创建者
	 */
	private String createuser;

	/**
	 * 创建时间
	 */
	private String createdate;

	/**
	 * 更新者
	 */
	private String updateuser;

	/**
	 * 更新时间
	 */
	private String updatedate;

	public Integer getId() {
		return id;
	}

	public String getCustomername() {
		return customername;
	}

	public String getCompanyname() {
		return companyname;
	}

	public String getCompanyaddress() {
		return companyaddress;
	}

	public String getPhone() {
		return phone;
	}

	public String getTell() {
		return tell;
	}

	public String getFax() {
		return fax;
	}

	public String getMail() {
		return mail;
	}

	public String getNote() {
		return note;
	}

	public Integer getStatus() {
		return status;
	}

	public String getRes01() {
		return res01;
	}

	public String getRes02() {
		return res02;
	}

	public String getRes03() {
		return res03;
	}

	public String getRes04() {
		return res04;
	}

	public String getRes05() {
		return res05;
	}

	public String getRes06() {
		return res06;
	}

	public String getRes07() {
		return res07;
	}

	public String getRes08() {
		return res08;
	}

	public String getRes09() {
		return res09;
	}

	public String getRes10() {
		return res10;
	}

	public String getCreateuser() {
		return createuser;
	}

	public String getCreatedate() {
		return createdate;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setRes01(String res01) {
		this.res01 = res01;
	}

	public void setRes02(String res02) {
		this.res02 = res02;
	}

	public void setRes03(String res03) {
		this.res03 = res03;
	}

	public void setRes04(String res04) {
		this.res04 = res04;
	}

	public void setRes05(String res05) {
		this.res05 = res05;
	}

	public void setRes06(String res06) {
		this.res06 = res06;
	}

	public void setRes07(String res07) {
		this.res07 = res07;
	}

	public void setRes08(String res08) {
		this.res08 = res08;
	}

	public void setRes09(String res09) {
		this.res09 = res09;
	}

	public void setRes10(String res10) {
		this.res10 = res10;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

}
