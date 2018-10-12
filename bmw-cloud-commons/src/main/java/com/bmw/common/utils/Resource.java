package com.bmw.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件资源获取类
 * @author lyl
 * 2017年6月29日
 */
public class Resource {
	
	Logger log = LoggerFactory.getLogger(Resource.class);
	
	private static Resource resource;
	
	private Resource(){}
	
	
	/**
	 * 实例化
	 * @author lyl
	 * 2016年1月21日
	 */
	public static Resource getInstance(){
		if(resource == null){
			resource =  new Resource();
		}
		return resource;
	}
	 
	
	
	/**
     * 根据路径获取文件流
     * @param path 获取文件名称 ，如：[/rsa/pay-callback-function/FWPri.key]
     * @return 返回流文件，没找到返回null。
     * @author lyl
     * 2016年1月21日
     */
    public InputStream getResoureToStream(String path) {
    	InputStream is = null;
    	try{
    		is = this.getClass().getResourceAsStream(path);
    	}catch(NullPointerException e){
    		log.error("获取文件没有找到 ：文件名称[{}]", path);
    	}
		return is;
	}
    
    /**
     * 根据路径获取文件字节
     * @param path 获取文件名称 ，如：[/rsa/pay-callback-function/FWPri.key]
     * @return 返回byte[]，没找到返回null。
     * @author lyl
     * 2016年1月21日
     * @throws IOException 
     */
    public byte[] getResoureToByte(String path) throws IOException {
    	InputStream is = getResoureToStream(path);
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
 	    byte[] buffer = new byte[4096];
 	    int n = 0;
 	    while (-1 != (n = is.read(buffer))) {
 	        output.write(buffer, 0, n);
 	    }
 	    return output.toByteArray();
    }
    
}
