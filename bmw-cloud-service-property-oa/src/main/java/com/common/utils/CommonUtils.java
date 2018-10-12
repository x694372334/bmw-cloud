package com.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共工具类
 * @author lyl
 * 2017年1月22日
 */
public class CommonUtils {
	static Logger log =LoggerFactory.getLogger(CommonUtils.class);
	/**
	 * InputStream转String
	 * 
	 * @author lyl
	 * 2017年1月22日
	 */
	public static String convertStreamToString(InputStream inputStream) {
		if(inputStream == null){
			return null;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	
	
	/**
	 * 获取访问者IP
	 * @author lyl
	 * 2015年10月27日
	 */
	public static String getOriginIP(HttpServletRequest request) {		
		String ip = request.getHeader("x-forwarded-for");
		if(StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
            	ip = ip.substring(0,index);
            }
		}
	    if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("X-Real-IP");
	    }
	    if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	        if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
		          InetAddress inet = null;
		          try {
		            inet = InetAddress.getLocalHost();
		          } catch (UnknownHostException e) {
		            e.printStackTrace();
		          }
		          ip = inet.getHostAddress();
		        }
	    }	      
	    return ip;
	}  

	
	/**
	 * 安全访问参数加密，ras+base64
	 * @author lyl
	 * 2018年5月30日
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String HttpAccessEncode(String msg) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException{
		byte[] rsa = RSAUtils.accessEncrypt(msg.getBytes("UTF-8"));
		return Base64.encodeBase64String(rsa);
	}
	
	
	
	/**
	 * 安全访问参数解密，base64+ras
	 * @author lyl
	 * 2018年5月30日
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String HttpAccessDecode(String msg) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		String message = null;
		String result = null;
		message = msg.replace(" ", "+");
		byte[] Base64Mgs = Base64.decodeBase64(message.getBytes());
		byte[] RSAMgs = RSAUtils.accessDecrypt(Base64Mgs);
		result = new String(RSAMgs,"UTF-8"); 
		log.info(result);
		return result;
	}
}
