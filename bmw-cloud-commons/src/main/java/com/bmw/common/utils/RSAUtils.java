package com.bmw.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

/**
 * RSA工具类
 * @author lyl
 * 2016年1月26日
 */

@SuppressWarnings("restriction")
public class RSAUtils {  
	
	static Logger log=LoggerFactory.getLogger(RSAUtils.class);
	
	 /** 
     * RSA最大加密明文大小 
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
      
    /** 
     * RSA最大解密密文大小 
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    
    
    
    /**
     * 获取公钥
     * @param path 公钥相对路径，如：[/rsa/pay-callback-function/FWPub.key]
     * @return
     */
    public static RSAPublicKey getRSAPublicKey(String path){
    	RSAPublicKey publicKey = null;
    	ObjectInputStream in = null;
    	try {
             in = new ObjectInputStream(Resource.getInstance().getResoureToStream(path));
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
     * 获取私钥
     * @param path 公钥相对路径，如：[/rsa/pay-callback-function/FWPub.key]
     * @return
     */
    public static RSAPrivateKey getRSAPrivateKey(String path){
    	RSAPrivateKey privateKey = null;
    	ObjectInputStream in = null;
    	try {
             in = new ObjectInputStream(Resource.getInstance().getResoureToStream(path));
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
     * 公钥加密 
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
    public static byte[] encryptByPublicKey(RSAPublicKey publicKey, byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException{  
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
     * 私钥加密 
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
    public static byte[] encryptByPrivateKey(RSAPrivateKey privateKey, byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException{  
    	Cipher cipher = Cipher.getInstance("RSA");
    	cipher.init(Cipher.ENCRYPT_MODE, privateKey);
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
     * 生成密钥文件
     * @throws Exception
     */
    public static void generator() throws Exception{
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey puk = kp.getPublic();
        PrivateKey prk = kp.getPrivate();
        /**查看公私钥串内容
        BASE64Encoder b64 = new BASE64Encoder();
		String pkStr = b64.encode(puk.getEncoded());
        log.info(pkStr);
        **/
        FileOutputStream fos1 = new FileOutputStream("e:/mypub.key");
        FileOutputStream fos2 = new FileOutputStream("e:/mypri.key");
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
        ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
        oos1.writeObject(puk);
        oos2.writeObject(prk);
        oos1.close();
        oos2.close();
        fos1.close();
        fos2.close();
        log.info("公匙生成成功!公匙文件为mypub.key");
        log.info("私匙生成成功!私匙文件为mypri.key");
    }
    
    
    
    
    
    public static void javaRSAConvertBase64(){
    	FileInputStream fin = null;
		ObjectInputStream in = null;
		try{
			BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));  
			System.out.print("请输入转换类型（0-公钥；1-私钥）：");  
			String type = strin.readLine();  
			  
			log.info("请输入原始待转换文件路径（如c:\\mypub.key）：");  
			String path = strin.readLine();  
			
			fin = new FileInputStream(path);
			in = new ObjectInputStream(fin);
			
			if("0".equals(type)){
				//读取
				PublicKey puk = null;
				puk = (PublicKey) in.readObject();
				BASE64Encoder b64 = new BASE64Encoder();
				String pkStr = b64.encode(puk.getEncoded());
				
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
			}else if("1".equals(type)){
				//读取
				PrivateKey prk = null;
				prk = (PrivateKey) in.readObject();
				BASE64Encoder b64 = new BASE64Encoder();
				String pkStr = b64.encode(prk.getEncoded());

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
}  