package com.cn.ge.service;

import com.cn.common.util.Page;
import com.cn.ge.dto.DocDto;

/**
 * DocService
 * @author Frank
 * @version 1.0
 * @create 2016-2-26下午3:35:56
 */
public interface DocService {

	/**
	 * 翻页查询数据
	 * @param docname
	 * @param page
	 * @return
	 */
	public Page queryDocByPage(String docname, Page page);
	
	/**
	 * 根据登录ID查询资料信息
	 * @param id
	 * @return
	 */
	public DocDto queryDocByID(String id);
	
	/**
	 * 删除资料信息
	 * @param id
	 */
	public void deleteDoc(String id);
	
	/**
	 * 新增资料信息
	 * @param doc
	 */
	public void insertDoc(DocDto doc);
	
	/**
	 * 修改资料信息
	 * @param doc
	 */
	public void updateDoc(DocDto doc);
}
