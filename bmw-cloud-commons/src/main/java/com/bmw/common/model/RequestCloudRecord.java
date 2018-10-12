package com.bmw.common.model;

import java.sql.Timestamp;


/**
 * 务访问记录
 * 
 * @author lyl
 * @date 2017-03-13 05:28:02
 */
public class RequestCloudRecord {


    /**
     * 主键
     */
    private String requestId;


    /**
     * 访问方法
     */
    private String requestMethod;


    /**
     * 访问项目名称
     */
    private String requestName;


    /**
     * 访问的URI
     */
    private String requestUri;


    /**
     * 访问的URL
     */
    private String requestUrl;


    /**
     * 访问参数
     */
    private String requestParam;


    /**
     * 访问来源项目标识
     */
    private String requestProjectIdent;


    /**
     * 访问来源授权码
     */
    private String requestProjectCode;


    /**
     * 访问来源IP
     */
    private String requestIp;


    /**
     * 访问时间
     */
    private Timestamp requestDate;


    /**
     * 备注
     */
    private String remark;


    /**
     * 创建时间
     */
    private Timestamp createDate;



    /**
     * 主键
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * 主键
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    /**
     * 访问方法
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * 访问方法
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod == null ? null : requestMethod.trim();
    }

    /**
     * 访问项目名称
     */
    public String getRequestName() {
        return requestName;
    }

    /**
     * 访问项目名称
     */
    public void setRequestName(String requestName) {
        this.requestName = requestName == null ? null : requestName.trim();
    }

    /**
     * 访问的URI
     */
    public String getRequestUri() {
        return requestUri;
    }

    /**
     * 访问的URI
     */
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }

    /**
     * 访问的URL
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * 访问的URL
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    /**
     * 访问参数
     */
    public String getRequestParam() {
        return requestParam;
    }

    /**
     * 访问参数
     */
    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam == null ? null : requestParam.trim();
    }

    /**
     * 访问来源项目标识
     */
    public String getRequestProjectIdent() {
        return requestProjectIdent;
    }

    /**
     * 访问来源项目标识
     */
    public void setRequestProjectIdent(String requestProjectIdent) {
        this.requestProjectIdent = requestProjectIdent == null ? null : requestProjectIdent.trim();
    }

    /**
     * 访问来源授权码
     */
    public String getRequestProjectCode() {
        return requestProjectCode;
    }

    /**
     * 访问来源授权码
     */
    public void setRequestProjectCode(String requestProjectCode) {
        this.requestProjectCode = requestProjectCode == null ? null : requestProjectCode.trim();
    }

    /**
     * 访问来源IP
     */
    public String getRequestIp() {
        return requestIp;
    }

    /**
     * 访问来源IP
     */
    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp == null ? null : requestIp.trim();
    }

    /**
     * 访问时间
     */
    public Timestamp getRequestDate() {
        return requestDate;
    }

    /**
     * 访问时间
     */
    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建时间
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}