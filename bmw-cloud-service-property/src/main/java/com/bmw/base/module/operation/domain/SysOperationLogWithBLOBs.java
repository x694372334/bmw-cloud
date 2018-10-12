package com.bmw.base.module.operation.domain;


public class SysOperationLogWithBLOBs extends SysOperationLog {


    /**
     * 方法名称
     */
    private String method;


    /**
     * 备注
     */
    private String message;



    /**
     * 方法名称
     */
    public String getMethod() {
        return method;
    }

    /**
     * 方法名称
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * 备注
     */
    public String getMessage() {
        return message;
    }

    /**
     * 备注
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}