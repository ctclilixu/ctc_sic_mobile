package com.cn.ge.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.ge.dao.DocDao;
import com.cn.ge.dto.DocDto;
import com.cn.ge.service.DocService;

/**
 * DocServiceImpl
 * @author Frank
 * @version 1.0
 * @create 2016-2-26下午3:37:42
 */
public class DocServiceImpl implements DocService {
	
	private DocDao docDao;

	@Override
	public Page queryDocByPage(String docname, String createdatelow, String createdatehigh, Page page) {
		docname = StringUtil.replaceDatabaseKeyword_mysql(docname);
		if(StringUtil.isNotBlank(createdatelow)) {
			createdatelow = createdatelow + " 00:00:00";
		}
		if(StringUtil.isNotBlank(createdatehigh)) {
			createdatehigh = createdatehigh + " 23:59:59";
		}
		//查询总记录数
		int totalCount = docDao.queryDocCountByPage(docname, createdatelow, createdatehigh);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<DocDto> list = docDao.queryDocByPage(docname, createdatelow, createdatehigh,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public DocDto queryDocByID(String id) {
		return docDao.queryDocByID(id);
	}
	
	@Override
	public DocDto queryDocByLogicID(String docname, String doctype) {
		return docDao.queryDocByLogicID(docname, doctype);
	}

	@Override
	public void deleteDoc(String id, String userid) {
		DocDto doc = docDao.queryDocByID(id);
		if(doc != null) {
			//逻辑删除
			doc.setStatus(Constants.STATUS_DEL);
			doc.setUpdateuser(userid);
			docDao.updateDoc(doc);
		}
	}

	@Override
	public void insertDoc(DocDto doc) {
		docDao.insertDoc(doc);
	}

	@Override
	public void updateDoc(DocDto doc) {
		docDao.updateDoc(doc);
	}

	public DocDao getDocDao() {
		return docDao;
	}

	public void setDocDao(DocDao docDao) {
		this.docDao = docDao;
	}
}
