package com.cn.ge.action;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.StringUtil;
import com.cn.ge.dto.DocDto;
import com.cn.ge.service.DocService;

/**
 * @name WebDocInfoAction.java
 * @author Frank
 * @time 2016-2-28下午3:07:15
 * @version 1.0
 */
public class WebDocInfoAction extends BaseAction {

	private static final long serialVersionUID = -6485340654986127190L;
	private static final Logger log = LogManager.getLogger(WebDocInfoAction.class);
	
	private DocService docService;
	
	private String strDocId;
	
	private DocDto docInfoDto;

	/**
	 * 资料信息页面
	 * @return
	 */
	public String showDocInfoAction() {
		try {
			this.clearMessages();
			docInfoDto = new DocDto();
			if(StringUtil.isBlank(strDocId)) {
				return "checkerr";
			}
			//docInfoDto = docService.queryDocByID(strDocId);
			//默认查询概要版数据----以后会做修改
			docInfoDto = docService.queryDocByLogicID(strDocId, "10");
			if(docInfoDto == null) {
				return "checkerr";
			}
		} catch(Exception e) {
			log.error("showDocInfoAction error:" + e);
			return "checkerr";
		}
		return SUCCESS;
	}

	public String getStrDocId() {
		return strDocId;
	}

	public void setStrDocId(String strDocId) {
		this.strDocId = strDocId;
	}

	public DocService getDocService() {
		return docService;
	}

	public void setDocService(DocService docService) {
		this.docService = docService;
	}

	public DocDto getDocInfoDto() {
		return docInfoDto;
	}

	public void setDocInfoDto(DocDto docInfoDto) {
		this.docInfoDto = docInfoDto;
	}
}
