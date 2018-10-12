package com.bmw.common.utils;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DES算法加解密，以Base64加密最终呈现
 * 
 * @author lyl 
 * 2016年11月8日
 */
public class DESUtils {

	static Logger log=LoggerFactory.getLogger(DESUtils.class);
	
	 private final static String DES = "DES";
     
	  public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
	    // DES算法要求有一个可信任的随机数源
	    SecureRandom sr = new SecureRandom();
	    // 从原始密匙数据创建DESKeySpec对象
	    DESKeySpec dks = new DESKeySpec(key);
	    // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
	    SecretKey securekey = keyFactory.generateSecret(dks);
	    // Cipher对象实际完成加密操作
	    Cipher cipher = Cipher.getInstance(DES);
	    // 用密匙初始化Cipher对象
	    cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
	    // 正式执行加密操作
	    return cipher.doFinal(src);
	  }
	            
	  /**
	   *
	   * @param password 密码
	   * @param key 加密字符串
	   * @return
	 * @throws Exception 
	   */
	  public final static String encrypt(String password, String key) throws Exception {
	      return Base64.encodeBase64String(encrypt(password.getBytes(), key.getBytes()));
	  }
	  
	  
	  /**
	   *
	   * @param src 数据源
	   * @param key 密钥，长度必须是8的倍数
	   * @return
	   * @throws Exception
	   */
	  public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
	    // DES算法要求有一个可信任的随机数源
	    SecureRandom sr = new SecureRandom();
	    // 从原始密匙数据创建一个DESKeySpec对象
	    DESKeySpec dks = new DESKeySpec(key);
	    // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
	    SecretKey securekey = keyFactory.generateSecret(dks);
	    // Cipher对象实际完成解密操作
	    Cipher cipher = Cipher.getInstance(DES);
	    // 用密匙初始化Cipher对象
	    cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
	        
	    // 正式执行解密操作
	    return cipher.doFinal(src);
	  }
	      
	  public final static String decrypt(String data, String key) throws Exception {
	      return new String(decrypt(Base64.decodeBase64(data.getBytes()), key.getBytes()));
	  }
	            
	  public static void main(String[] args) throws Exception{
	    String encryptString = encrypt("bmw-lyl","lyl");
	    log.info(encryptString);
	    
	    String decryptString = decrypt(encryptString, "lyl");
	    log.info(decryptString);
	  }          
}