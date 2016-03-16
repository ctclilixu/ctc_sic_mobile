package com.cn.ge.service.impl;

import java.util.List;

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
	public Page queryDocByPage(String docname, Page page) {
		docname = StringUtil.replaceDatabaseKeyword_mysql(docname);
		//查询总记录数
		int totalCount = docDao.queryDocCountByPage(docname);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<DocDto> list = docDao.queryDocByPage(docname,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public DocDto queryDocByID(String id) {
		return docDao.queryDocByID(id);
	}

	@Override
	public void deleteDoc(String id) {
		docDao.deleteDoc(id);
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
