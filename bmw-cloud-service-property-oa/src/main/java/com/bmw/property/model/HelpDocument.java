package com.bmw.property.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 帮助文档
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-10
 */
@TableName("t_help_document")
public class HelpDocument extends Model<HelpDocument> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 分类名称
     */
    @TableField("help_document_type")
    private String helpDocumentType;
    /**
     * 分类ID
     */
    @TableField("help_document_type_id")
    private Integer helpDocumentTypeId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建人ID
     */
    @TableField("create_id")
    private Integer createId;
    /**
     * 创建人
     */
    @TableField("create_man")
    private String createMan;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改人id
     */
    @TableField("edit_man_id")
    private Integer editManId;
    /**
     * 修改人
     */
    @TableField("edit_man")
    private String editMan;
    /**
     * 修改时间
     */
    @TableField("edit_time")
    private Date editTime;
    /**
     * 附件ID
     */
    @TableField("file_id")
    private String fileId;
    /**
     * 附件文件名
     */
    @TableField("file_name")
    private String fileName;
    /**
     * 附件文件路径
     */
    @TableField("file_path")
    private String filePath;
    /**
     * 附件文件格式
     */
    @TableField("file_format")
    private String fileFormat;
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 企业ID
     */
    private Integer parentEId;
    
    public Integer getParentEId() {
		return parentEId;
	}

	public void setParentEId(Integer parentEId) {
		this.parentEId = parentEId;
	}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHelpDocumentType() {
        return helpDocumentType;
    }

    public void setHelpDocumentType(String helpDocumentType) {
        this.helpDocumentType = helpDocumentType;
    }

    public Integer getHelpDocumentTypeId() {
        return helpDocumentTypeId;
    }

    public void setHelpDocumentTypeId(Integer helpDocumentTypeId) {
        this.helpDocumentTypeId = helpDocumentTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEditManId() {
        return editManId;
    }

    public void setEditManId(Integer editManId) {
        this.editManId = editManId;
    }

    public String getEditMan() {
        return editMan;
    }

    public void setEditMan(String editMan) {
        this.editMan = editMan;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    

    public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Override
    public String toString() {
        return "HelpDocument{" +
        "id=" + id +
        ", helpDocumentType=" + helpDocumentType +
        ", helpDocumentTypeId=" + helpDocumentTypeId +
        ", title=" + title +
        ", content=" + content +
        ", createId=" + createId +
        ", createMan=" + createMan +
        ", createTime=" + createTime +
        ", editManId=" + editManId +
        ", editMan=" + editMan +
        ", editTime=" + editTime +
        ", fileName=" + fileName +
        ", filePath=" + filePath +
        ", fileFormat=" + fileFormat +
        ", keyword=" + keyword +
        "}";
    }
}
