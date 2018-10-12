/**
 * Project Name:bmw-oa
 * File Name:TreeViewNode.java
 * Package Name:com.stylefeng.guns.core.node
 * Date:2018年6月27日下午1:11:21
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.stylefeng.guns.core.node;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名: TreeViewNode  
 * 类描述: bootstarp-treeview节点类
 * 创建人: wangliqing
 * 创建时间 : 2018年6月27日 下午1:20:20    
 *
 */
public class TreeViewNode {

	/**
	 * 列表树节点上的文本，通常是节点右边的小图标。
	 */
	private String text;
	
	/**
	 * 列表树节点上的图标，通常是节点左边的图标
	 */
	private String icon;
	
	/**
	 * 指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择。
	 */
	private boolean selectable;
	
	/**
	 * 子节点的集合
	 */
	private List<TreeViewNode> nodes = new ArrayList<TreeViewNode>();

	private List<TreeViewNode> linkedList = new ArrayList<TreeViewNode>();
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public List<TreeViewNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeViewNode> nodes) {
		this.nodes = nodes;
	}
	
	public List<TreeViewNode> getLinkedList() {
		return linkedList;
	}

	public void setLinkedList(List<TreeViewNode> linkedList) {
		this.linkedList = linkedList;
	}

	public static List<TreeViewNode> getTreeNode() {
		List<TreeModel> menus=new ArrayList<TreeModel>(); //定义TreeViewNode
		
		TreeModel treeModel = new TreeModel();
		treeModel.setId(1);
		treeModel.setPid(1);
		treeModel.setBid(1);
		treeModel.setBpid(1);
		treeModel.setName1("小区名称1");
		treeModel.setName2("楼宇名称1");
		treeModel.setName3("房屋名称1");
		menus.add(treeModel);
		
		treeModel = new TreeModel();
		treeModel.setId(2);
		treeModel.setPid(1);
		treeModel.setBid(2);
		treeModel.setBpid(1);
		treeModel.setName1("小区名称2");
		treeModel.setName2("楼宇名称2");
		treeModel.setName3("房屋名称2");
		menus.add(treeModel);
		
		treeModel = new TreeModel();
		treeModel.setId(3);
		treeModel.setPid(2);
		treeModel.setBid(3);
		treeModel.setBpid(2);
		treeModel.setName1("小区名称3");
		treeModel.setName2("楼宇名称3");
		treeModel.setName3("房屋名称3");
		menus.add(treeModel);
		return buildTree(menus);
	}
	
	public static List<TreeViewNode> buildTree(List<TreeModel> list){
		 List<TreeViewNode> menus=new ArrayList<TreeViewNode>(); //定义TreeViewNode
		 
		 TreeViewNode treeViewNode0 = null;
		 TreeViewNode treeViewNode1 = null;
		 TreeViewNode treeViewNode2 = null;
		      for (TreeModel menu : list) {
		    	  int id = menu.getId();//小区id
		    	  List<TreeViewNode> menus0=new ArrayList<TreeViewNode>(); //楼宇下级集合
		          for(TreeModel menu1 : list) {
		        	  int pid = menu1.getPid();//楼宇pid
		        	  int bid = menu1.getBid();//楼宇id
		        	  if(id == pid) {
		        		  treeViewNode1 = new TreeViewNode();
		        		  treeViewNode1.setText(menu1.getName2());
			              treeViewNode1.setSelectable(false);
			              treeViewNode1.setIcon("glyphicon glyphicon-stop");
			              menus0.add(treeViewNode1);
		        	  }
		        	  List<TreeViewNode> menus1=new ArrayList<TreeViewNode>(); //房屋下级集合
		        	  for(TreeModel menu2 : list) {
		        		  int bpid = menu2.getBpid();//房屋pid
		        		  if(bid == bpid) {
		        			  treeViewNode2 = new TreeViewNode();
			        		  treeViewNode2.setText(menu2.getName3());
				              treeViewNode2.setSelectable(true);
				              treeViewNode2.setIcon("glyphicon glyphicon-stop");
				              menus1.add(treeViewNode2);
		        		  }
		        	  }
		        	  treeViewNode1.setNodes(menus1);
		          }
		          
		          
		          treeViewNode0 = new TreeViewNode();
		    	  treeViewNode0.setText(menu.getName1());
		    	  treeViewNode0.setIcon("glyphicon glyphicon-stop");
		    	  treeViewNode0.setSelectable(false);
		          treeViewNode0.setNodes(menus0);
	        	  menus.add(treeViewNode0);
		      }
		return menus;
	}
	
	
}

