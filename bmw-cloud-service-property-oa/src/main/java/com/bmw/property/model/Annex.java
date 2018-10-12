package com.bmw.property.model;

import java.sql.Timestamp;


/**
 * 附件表
 * 
 * @author zhangt
 * @date 2018-07-10 04:19:09
 */
public class Annex {


    /**
     * 主键id
     */
    private Integer id;


    /**
     * 创建人ID
     */
    private Integer createId;


    /**
     * 创建人
     */
    private String createMan;


    /**
     * 创建时间
     */
    private Timestamp createTime;


    /**
     * 修改人id
     */
    private Integer editManId;


    /**
     * 修改人
     */
    private String editMan;


    /**
     * 修改时间
     */
    private Timestamp editTime;


    /**
     * 附件文件名
     */
    private String fileName;


    /**
     * 附件文件路径
     */
    private String filePath;


    /**
     * 附件文件格式
     */
    private String fileFormat;



    /**
     * 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 创建人ID
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 创建人ID
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * 创建人
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * 创建人
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * 创建时间
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人id
     */
    public Integer getEditManId() {
        return editManId;
    }

    /**
     * 修改人id
     */
    public void setEditManId(Integer editManId) {
        this.editManId = editManId;
    }

    /**
     * 修改人
     */
    public String getEditMan() {
        return editMan;
    }

    /**
     * 修改人
     */
    public void setEditMan(String editMan) {
        this.editMan = editMan == null ? null : editMan.trim();
    }

    /**
     * 修改时间
     */
    public Timestamp getEditTime() {
        return editTime;
    }

    /**
     * 修改时间
     */
    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    /**
     * 附件文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 附件文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 附件文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 附件文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * 附件文件格式
     */
    public String getFileFormat() {
        return fileFormat;
    }

    /**
     * 附件文件格式
     */
    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat == null ? null : fileFormat.trim();
    }
}