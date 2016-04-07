package com.cn.ge.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.ge.dao.DocDao;
import com.cn.ge.dto.DocDto;

/**
 * DocDaoImpl
 * @author Frank
 * @version 1.0
 * @create 2016-2-26下午3:33:04
 */
public class DocDaoImpl extends BaseDao implements DocDao {

	@Override
	public List<DocDto> queryDocByPage(String docname, String createdatelow, String createdatehigh, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docname", docname);
		paramMap.put("createdatelow", createdatelow);
		paramMap.put("createdatehigh", createdatehigh);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<DocDto> list = getSqlMapClientTemplate().queryForList("queryDocByPage", paramMap);
		return list;
	}

	@Override
	public int queryDocCountByPage(String docname, String createdatelow, String createdatehigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docname", docname);
		paramMap.put("createdatelow", createdatelow);
		paramMap.put("createdatehigh", createdatehigh);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryDocCountByPage", paramMap);
	}

	@Override
	public DocDto queryDocByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<DocDto> list = getSqlMapClientTemplate().queryForList("queryDocByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public DocDto queryDocByLogicID(String docname, String doctype) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docname", docname);
		paramMap.put("doctype", doctype);
		@SuppressWarnings("unchecked")
		List<DocDto> list = getSqlMapClientTemplate().queryForList("queryDocByLogicID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteDoc(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		getSqlMapClientTemplate().delete("deleteDoc", paramMap);
	}

	@Override
	public void insertDoc(DocDto doc) {
		getSqlMapClientTemplate().insert("insertDoc", doc);
	}

	@Override
	public void updateDoc(DocDto doc) {
		getSqlMapClientTemplate().update("updateDoc", doc);
	}
}
