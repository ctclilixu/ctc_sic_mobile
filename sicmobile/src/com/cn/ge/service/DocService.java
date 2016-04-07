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
	 * @param createdatelow
	 * @param createdatehigh
	 * @param page
	 * @return
	 */
	public Page queryDocByPage(String docname, String createdatelow, String createdatehigh, Page page);
	
	/**
	 * 根据登录ID查询资料信息
	 * @param id
	 * @return
	 */
	public DocDto queryDocByID(String id);
	
	/**
	 * 根据逻辑主键查询数据
	 * @param docname
	 * @param doctype
	 * @return
	 */
	public DocDto queryDocByLogicID(String docname, String doctype);
	
	/**
	 * 逻辑删除资料信息
	 * @param id
	 * @param userid
	 */
	public void deleteDoc(String id, String userid);
	
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
