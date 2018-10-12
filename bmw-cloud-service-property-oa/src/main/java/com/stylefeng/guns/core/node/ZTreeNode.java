package com.stylefeng.guns.core.node;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * jquery ztree 插件的节点
 * 
 * @author fengshuonan
 * @date 2017年2月17日 下午8:25:14
 */
public class ZTreeNode {

	private Long id;	//节点id
	
	private Long pId;//父节点id
	
	private String pcode;
	
	private String name;//节点名称
	
	private Boolean open;//是否打开节点
	
	private Boolean checked;//是否被选中
	
	private String ext_attr="";
	
	private Boolean nocheck  ;//是否可选
	
	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getExt_attr() {
		return ext_attr;
	}

	public void setExt_attr(String ext_attr) {
		this.ext_attr = ext_attr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getIsOpen() {
		return open;
	}

	public void setIsOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	public static ZTreeNode createParent(){
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setChecked(false);
		zTreeNode.setId(0L);
		zTreeNode.setNocheck(true);
		zTreeNode.setName("顶级");
		zTreeNode.setOpen(true);
		zTreeNode.setPcode("0");
		zTreeNode.setpId(0L);
		return zTreeNode;
	}
	
	/*
	 * 计算他的父节点
	 */
	public static List<ZTreeNode> createParent(List<ZTreeNode> zTreeNodeList ){
		List<ZTreeNode> result = new ArrayList<ZTreeNode>();
		if(0==zTreeNodeList.size()||null==zTreeNodeList) {
			ZTreeNode zTreeNode = new ZTreeNode();
			zTreeNode.setChecked(false);
			zTreeNode.setId(0L);
			zTreeNode.setNocheck(true);
			zTreeNode.setName("顶级");
			zTreeNode.setOpen(true);
			zTreeNode.setPcode("0");
			zTreeNode.setpId(0L);
			result.add(zTreeNode);
			return result;
		}
		List<Long> pIds = new ArrayList<Long>();
		int flag = 0;
		for(ZTreeNode node1 : zTreeNodeList) {
			for(ZTreeNode node2 : zTreeNodeList) {
				    flag = 0;
				if(node1.getpId() == node2.getId()) {
					flag = 1;
					break;
				}
			}
			if(0==flag) {
				ZTreeNode zTreeNode = new ZTreeNode();
				zTreeNode.setChecked(false);
				zTreeNode.setId(node1.getpId());
				zTreeNode.setName("顶级");
				zTreeNode.setNocheck(true);
				zTreeNode.setPcode("0");
				zTreeNode.setOpen(true);
				zTreeNode.setpId(node1.getpId());
				if(!pIds.contains(zTreeNode.getId())) {
					result.add(zTreeNode);
					pIds.add(zTreeNode.getId());
				}
				//result.add(zTreeNode);
				continue;
			}
		}
		
		return result;
	}
	
}
