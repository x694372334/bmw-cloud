package com.common.utils;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.NoSuchAlgorithmException;  
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;  
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * RSA工具类
 * @author tangdelong
 * 2016年1月26日
 */
public class RSAUtils {  
	
	static Logger log = LoggerFactory.getLogger(RSAUtils.class);
	
	/** 
    * RSA最大加密明文大小 
    */
    private static final int MAX_ENCRYPT_BLOCK = 117;
      
    /** 
     * RSA最大解密密文大小 
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    
    
    
    
    /**
     * 安全访问加密
     * @param srcBytes 要加密字节
     * 
     * @return 返回加密后字节
     * @author kyk
     * 2018年5月30日
     */
    public static byte[] accessEncrypt(byte[] srcBytes) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
    	return encrypt(getPublicKey("bmwWAPIPub.key"),srcBytes);
    }
    
    /**
     * 安全访问解密
     * @param srcBytes 要解密字节
     * 
     * @return 解密后字节
     * @author lyl
     * 2018年5月30日
     */
    public static byte[] accessDecrypt(byte[] srcBytes) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
    	return decrypt(getPrivateKey("TSWAPIQPri.key"),srcBytes);
    }
    
    

 
    
    
    /**
     * 获取公钥，在resources/rsa下的钥匙
     * @param allName 公钥全称带后缀；只能解析.key后缀文件
     * 
     *  @return 返回公钥类
     * 
     * @return
     */
    public static RSAPublicKey getPublicKey(String allName){
    	RSAPublicKey publicKey = null;
    	ObjectInputStream in = null;
    	try {
   		 	//返回读取指定资源的输入流  
   	        InputStream is=RSAUtils.class.getResourceAsStream("/rsa/"+ allName);   
             in = new ObjectInputStream(is);
             publicKey = (RSAPublicKey) in.readObject();
         } catch (Exception e) {
 	         e.printStackTrace();
         }finally{
        	 try {
        		if(in != null){
        			in.close();
        		}
			} catch (IOException e) {
				e.printStackTrace();
			}
         }
    	return publicKey;
    }
    
    
    
    
    
    /**
     * 获取私钥，在resources/rsa下的钥匙
     * @param allName 私钥全称带后缀；只能解析.key后缀文件
     * 
     *  @return 返回私钥类
     * 
     * @return
     */
    public static RSAPrivateKey getPrivateKey(String allName){
    	RSAPrivateKey privateKey = null;
    	ObjectInputStream in = null;
    	try {
    		 //返回读取指定资源的输入流  
   	        InputStream is=RSAUtils.class.getResourceAsStream("/rsa/"+ allName);   
             in = new ObjectInputStream(is);
             privateKey = (RSAPrivateKey) in.readObject();
         } catch (Exception e) {
 	         e.printStackTrace();
         }finally{
        	 try {
        		if(in != null){
        			in.close();
        		}
			} catch (IOException e) {
				e.printStackTrace();
			}
         }
    	return privateKey;
    }
    
    
    
    /** 
     * 加密 
     * @param publicKey 
     * @param srcBytes 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws NoSuchPaddingException 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     * @throws IOException 
     */  
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException{  
    	Cipher cipher = Cipher.getInstance("RSA");
    	cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    	int inputLen = srcBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密  
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(srcBytes, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(srcBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
    
      
    /** 
     * 解密   
     * @param privateKey 
     * @param srcBytes 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws NoSuchPaddingException 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     * @throws IOException 
     */  
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException{  
    	Cipher cipher = Cipher.getInstance("RSA");
    	cipher.init(Cipher.DECRYPT_MODE, privateKey);
    	int inputLen = srcBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密  
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(srcBytes, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(srcBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }  
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
    
    
    
    
    
    /**
     * 生成密钥文件（包括公钥和私钥）; XXX.key后缀
     * @param path 位置 如： d:\\
     * @param name 文件名称
     * 
     * @author tangdelong
     * 2016年11月25日
     */
    public static void generatorRSAKey(String path,String name){
	  
    	
    	FileOutputStream fos1 = null;
    	FileOutputStream fos2 = null;
    	ObjectOutputStream oos1 = null;
    	ObjectOutputStream oos2 = null;
        try {
        	KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.generateKeyPair();
            PublicKey puk = kp.getPublic();
            PrivateKey prk = kp.getPrivate();
        	
        	String allPathPub = path + "/" + name + "Pub.key";
        	String allPathPri = path + "/" + name + "Pri.key";
        	
			fos1 = new FileOutputStream(allPathPub);
			fos2 = new FileOutputStream(allPathPri);
			oos1 = new ObjectOutputStream(fos1);
			oos2 = new ObjectOutputStream(fos2);
			
			oos1.writeObject(puk);
			oos2.writeObject(prk);
			
			log.info("公匙生成成功! 公匙文件位置："+allPathPub);
			log.info("私匙生成成功! 私匙文件位置："+allPathPri);
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}finally{
			try {
				oos1.close();
				oos2.close();
				fos1.close();
				fos2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    
    
    
    
    
    
    
    /**
     * RSA文件转换，后缀key转换为pem
     * @param type true:公钥，false:私钥
     * @param orinPath 后缀key文件的位置
     * 
     * @author tangdelong
     * 2016年11月28日
     */
    public static void convertRSAToPEM(boolean type,String orinPath){
    	if(orinPath == null && orinPath.isEmpty()){
    		log.info("非法参数！");
    		return;
    	}
    	FileInputStream fin = null;
		ObjectInputStream in = null;
		try{
			fin = new FileInputStream(orinPath);
			in = new ObjectInputStream(fin);
			if(type){
				//读取
				PublicKey puk = null;
				puk = (PublicKey) in.readObject();
				String pkStr = linefeed(Base64.encodeBase64String(puk.getEncoded()),76);
				
				//保存
				File file = new File("d:\\", "public_key.pem");
				file.createNewFile();
				FileWriter fileWritter = new FileWriter(file,true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write("-----BEGIN PUBLIC KEY-----");
				bufferWritter.newLine();
				bufferWritter.write(pkStr);
				bufferWritter.newLine();
				bufferWritter.write("-----END PUBLIC KEY-----");
				bufferWritter.close();
				log.info("转换成功，文件已保存到 D:\\public_key.pem");
				return;
			}else{
				//读取
				PrivateKey prk = null;
				prk = (PrivateKey) in.readObject();
				String pkStr = linefeed(Base64.encodeBase64String(prk.getEncoded()),76);
				
				//保存
				File file = new File("d:\\", "private_key.pem");
				file.createNewFile();
				FileWriter fileWritter = new FileWriter(file,true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write("-----BEGIN RSA PRIVATE KEY-----");
				bufferWritter.newLine();
				bufferWritter.write(pkStr);
				bufferWritter.newLine();
				bufferWritter.write("-----END RSA PRIVATE KEY-----");
				bufferWritter.close();
				log.info("转换成功，文件已保存到 D:\\private_key.pem");
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("转换文件失败");
		}finally{
			try {
				fin.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    
    
    /**
     * 换行
     * @param content 内容
     * @param place 换行位置
     * 
     * @return 换行后的结果
     * @author tangdelong
     * 2016年11月28日
     */
    private static String linefeed(String content,int place){
    	StringBuffer sb = new StringBuffer();
    	
    	while(content.length() >= place){
    		sb.append(content.substring(0, place));
    		sb.append("\r\n");
    		content = content.substring(place);
    	}
    	sb.append(content);
    	return sb.toString();
    	
    } 
    
    
//    public static void main(String[] args){
//    	generatorRSAKey("d://","TLCWAPI");
//    	generatorRSAPem("d://","tdl");
//    	convertRSAToPEM(true,"d://TLCWAPIPub.key");
//    	convertRSAToPEM(false,"d://TLCWAPIPri.key");
    	
//	} 
  
    
}  