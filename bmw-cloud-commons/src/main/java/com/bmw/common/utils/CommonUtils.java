//package com.bmw.common.utils;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Field;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//
//import org.apache.commons.codec.binary.Base64;
////import org.apache.commons.lang3.ArrayUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
///**
// * 公共工具类
// * @author lyl
// * 2016年11月10日
// */
//public class CommonUtils {
//
//	static Logger log=LoggerFactory.getLogger(CommonUtils.class);
//
//	/**
//	 * 手机短信Http发送参数编码
//	 * @author lyl
//	 * 2016年6月7日
//	 * @throws IOException
//	 * @throws UnsupportedEncodingException
//	 * @throws BadPaddingException
//	 * @throws IllegalBlockSizeException
//	 * @throws NoSuchPaddingException
//	 * @throws NoSuchAlgorithmException
//	 * @throws InvalidKeyException
//	 */
//	public static String HttpPhoneMsgEncode(String msg) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException{
//		Base64 base64 = new Base64();
//		return base64.encodeToString(msg.getBytes());
//	}
//
//	/**
//	 * Bean对象进行差异填充</br>
//	 * 把源Bean中的非空属性填充到目标Bean中；
//	 *
//	 * @param dest 目标Bean
//	 * @param orig 源Bean
//	 *
//	 * @author lyl
//	 * 2016年11月10日
//	 */
//	public static void populateConflict(Object dest,Object orig)throws IllegalArgumentException,IllegalAccessException{
//		Field[] beans = dest.getClass().getDeclaredFields();
//		Object value = null;
//		String temp = null;
//		for(Field f : beans){
//			f.setAccessible(true);
//			value = f.get(orig); // 获取属性值
//			if(value != null){
//				if(value instanceof String){ // 对字符串进行处理
//					temp = (String)value;
//					if(temp != ""){
//						f.set(dest, temp);
//					}
//				}else{ // 其他情况
//					f.set(dest, value);
//				}
//			}
//		}
//	}
//
//
//	/**
//	 * 检查Bean对象中字段是否为空（Null或String串的''）
//	 * @param cls Bean对象
//	 * @param excludeField 排除检查的字段
//	 *
//	 * @return true:Bean对象为空，false:Bean对象非空
//	 * @author lyl
//	 * 2016年11月16日
//	 * @throws IllegalAccessException
//	 * @throws IllegalArgumentException
//	 */
//	public static boolean checkBeanIsNull(Object bean,String... excludeField) throws IllegalArgumentException, IllegalAccessException{
//		Boolean result = true;
//		Field[] fiedls = bean.getClass().getDeclaredFields();
//
//		Object value = null;
//		String fieldName = null;
//		String temp = null;
//
//		for(Field f : fiedls){
//			f.setAccessible(true);
//			value = f.get(bean); // 获取属性值
//			if(value != null){
//				if(ArrayUtils.isNotEmpty(excludeField)){
//					boolean isExclude = false;
//					fieldName = f.getName(); // 属性名称
//					for(String str:excludeField){
//						if(str.trim().equals(fieldName)){
//							isExclude = true;
//							break;
//						}
//					}
//					if(!isExclude){
//						if(value instanceof String){
//							temp = (String)value;
//							if(temp != ""){
//								result = false;
//							}
//						}else{
//							result = false;
//						}
//					}
//				}else{
//					if(value instanceof String){
//						temp = (String)value;
//						if(temp != ""){
//							result = false;
//						}
//					}else{
//						result = false;
//					}
//				}
//			}
//		}
//
//		return result;
//	}
//	/**
//	 * 附件 文件 由临时转正
//	 * @param fileNameStr  字符串格式的临时文件名称 如  "a.png,b.jpg"
//	 * @param path  附件转正后的路径
//	 * @param fileServiceUrl  文件服务器
//	 *
//	 *
//	 * @return  附件转正后的地址 如 "/upload/formal/solution/a.png,/upload/formal/solution/b.jpg"
//	 * */
//	public static String fileFormal(String fileNameStr,String path,String fileServiceUrl){
//		List<String> formalfiles = new ArrayList<String>();
//		String[] fileNames = fileNameStr.split(",");
//		for(int j=0;j<fileNames.length;j++){
//			String fileName = fileNames[j];
//			String r = HttpUtils.fileForamlPath(fileName,path, fileServiceUrl);
//			formalfiles.add(r);
//		}
//		// 转账后图片路径保存
//		StringBuilder temp = new StringBuilder();
//		for(int j=0;j<formalfiles.size();j++){
//			if(j == 0){
//				temp.append(formalfiles.get(j));
//			}else{
//				temp.append(",");
//				temp.append(formalfiles.get(j));
//			}
//		}
//		return temp.toString();
//	}
//
//
//
//
////	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
////		PhoneContent r = new PhoneContent();
////		r.setToken("d");
////		r.setOriginIP("asdf");
////		r.setPhoneContent("sadf");
////
////		log.info(CommonUtils.checkBeanIsNull(r, "token","originIP"));
////
////	}
//
//
//}
