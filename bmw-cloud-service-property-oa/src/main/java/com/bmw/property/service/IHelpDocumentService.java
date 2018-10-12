package com.bmw.property.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.property.model.Annex;
import com.bmw.property.model.HelpDocument;

/**
 * <p>
 * 帮助文档 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-10
 */
public interface IHelpDocumentService  {
	/**
     * 列表
     */
	JSONArray findList(HelpDocument condition);
	
	/**
     * 详情
     */
	HelpDocument getdetail(Integer helpDocumentId);
	
	
	/**
     * 新增信息
     */
	void add(HelpDocument helpDocument);
	
	/**
	 * 修改信息
	 */
	void update(HelpDocument helpDocument);
	
	/**
	 * 删除信息
	 */
	void del(Integer helpDocumentId);

	/**
	 * 添加附件
	 */
	void addAnnex(Annex annex);

	/**
	 * 查询附件
	 */
	JSONArray findFileById(Integer helpDocumentId);
}
