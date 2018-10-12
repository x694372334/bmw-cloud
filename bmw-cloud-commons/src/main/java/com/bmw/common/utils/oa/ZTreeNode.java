package com.bmw.common.utils.oa;

/**
 * 
 * jquery ztree 插件的节点,OA系统用
 * 
 * @author lyl
 * @date 2017年2月17日 下午8:25:14
 */
public class ZTreeNode {

	private Long id; // 节点id

	private Long pId;// 父节点id

	private String name;// 节点名称

	private String pcode;

	private Boolean open;// 是否打开节点

	private Boolean checked;// 是否被选中

	private String ext_attr = "";

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

	public static ZTreeNode createParent() {
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setChecked(true);
		zTreeNode.setId(0L);
		zTreeNode.setName("顶级");
		zTreeNode.setOpen(true);
		zTreeNode.setpId(0L);
		return zTreeNode;
	}
}
