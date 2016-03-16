package com.cn.ge.dao;

import java.util.List;

import com.cn.ge.dto.DocDto;

/**
 * DocDao
 * @author Frank
 * @version 1.0
 * @create 2016-2-26下午3:32:26
 */
public interface DocDao {

	/**
	 * 翻页查询数据
	 * @param docname
	 * @param start
	 * @param end
	 * @return
	 */
	public List<DocDto> queryDocByPage(String docname, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param docname
	 * @return
	 */
	public int queryDocCountByPage(String docname);
	
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
