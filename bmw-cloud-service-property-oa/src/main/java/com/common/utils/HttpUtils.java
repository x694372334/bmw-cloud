package com.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.support.HttpKit;

/**
 * Http工具类
 * 
 * @author lyl 2015年12月17日
 */
public class HttpUtils {

	private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

	private static CloseableHttpClient httpClient;

	static {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", plainsf).register("https", sslsf).build();

		poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
		// 最大连接数
		poolingHttpClientConnectionManager.setMaxTotal(1000);
		// 将每个路由基础的连接增加到20
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(50);

		// 请求重试处理
		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 5) {// 如果已经重试了5次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// ssl握手异常
					return false;
				}

				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 如果请求是幂等的，就再次尝试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};

		httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).setRetryHandler(httpRequestRetryHandler).build();
	}
	
	/**
	 * 配置Http请求参数
	 * @author lyl
	 * 2015年12月17日
	 */
	private static void config(HttpRequestBase httpRequestBase) {
        try {
			httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
			httpRequestBase.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpRequestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");//"en-US,en;q=0.5");
			httpRequestBase.setHeader("Accept-Charset", "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");
			httpRequestBase.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE); 
			//zuul鉴权配置信息
			httpRequestBase.setHeader("bmw-RequestId",CommonUtils.HttpAccessEncode("bmw-oa"));
			// 配置请求的超时设置
			RequestConfig requestConfig = RequestConfig.custom()
			        .setConnectionRequestTimeout(5000)
			        .setConnectTimeout(5000)
			        .setSocketTimeout(30000)
			        .build();
			httpRequestBase.setConfig(requestConfig);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	/**
	 * 执行Get请求
	 * @author lyl
	 * 2015年12月17日
	 */
	public static String doGet(String url) throws ParseException, IOException{
		HttpGet get = new HttpGet(url);
		config(get);
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(get);
		try{
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		}finally{
			if(response != null){
				response.close();
			}
		}
	}
	
	/**
	 * 执行Get请求
	 * @author lyl
	 * 2015年12月17日
	 */
	public static String doGet(String url,Map<String,String> headers) throws ParseException, IOException{
		HttpGet get = new HttpGet(url);
		config(get);
		
		// 设置头信息
		if(headers != null && !headers.isEmpty()){
			for(Entry<String, String> mp : headers.entrySet()){
				get.setHeader(mp.getKey(), mp.getValue());
			}
		}
		
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(get);
		try{
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		}finally{
			if(response != null){
				response.close();
			}
		}
	}
	
	
	/**
	 * 执行Get请求,获取图片文件
	 * @param url 下载地址
	 * @param dir 存放图片的相对位置文件夹名称
	 * @param fileName 文件名称
	 * 
	 * @author lyl
	 * 2015年12月17日
	 */
	public static File doGetFileImage(String url,String dir,String fileName) throws ParseException, IOException{
		File file = null;
		InputStream is = null;
		FileOutputStream output = null;
		HttpGet get = new HttpGet(url);
		config(get);
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(get);
		try{
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			
			File dirFile = new File(dir);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			
			is = entity.getContent();
			file = new File(dir+"//"+fileName);
			output = new FileOutputStream(file);
			IOUtils.copy(is, output);
			
		}finally{
			if(response != null){
				response.close();
			}
			if(is != null){
				is.close();
			}
			if(output != null){
				output.close();
			}
		}
		
		return file;
	}
	
	/**
	 * 执行Get请求,获取图片文件
	 * @param url 下载地址
	 * @param dir 存放图片的相对位置文件夹名称
	 * @param fileName 文件名称
	 * 
	 * @author yuanbin
	 * 2018年07月27日
	 */
	public static File doGetFileImage(String url) throws ParseException, IOException{
		File file = null;
		InputStream in = null;
		OutputStream out = null;
		HttpGet get = new HttpGet(url);
		config(get);
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(get);
		try{
			// 获取响应实体
			HttpEntity entity = response.getEntity();  
			in = entity.getContent();
	        byte[] buf = new byte[1024];
	        int legth = 0;
	        
	        out = HttpKit.getResponse().getOutputStream();
	        while ((legth = in.read(buf)) != -1) {
	        	out.write(buf, 0, legth);
	        }
	       
		}finally{
			if(response != null){
				response.close();
			}
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		}
		
		return file;
	}
	
	
	/**
	 * 执行Post请求
	 * @author lyl
	 * 2015年12月17日
	 */
	public static String doPost(String url,List<NameValuePair> nameValuePair) throws ParseException, IOException{
		HttpPost post = new HttpPost(url);
		config(post);
		
		if(nameValuePair != null){
			post.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
		}
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(post);
		try{
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		}finally{
			if(response != null){
				response.close();
			}
		}
	}
	
	
	/**
	 * 执行Post请求
	 * @author lyl
	 * 2015年12月17日
	 */
	public static String doPost(String url,List<NameValuePair> nameValuePair,Map<String,String> headers) throws ParseException, IOException{
		HttpPost post = new HttpPost(url);
		config(post);
		
		// 设置头信息
		if(headers != null && !headers.isEmpty()){
			for(Entry<String, String> mp : headers.entrySet()){
				post.setHeader(mp.getKey(), mp.getValue());
			}
		}
		
		if(nameValuePair != null){
			post.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
		}
		
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(post);
		try{
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		}finally{
			if(response != null){
				response.close();
			}
		}
	}
	
	
	
	/**
	 * 执行Post请求
	 * @author lyl
	 * 2015年12月17日
	 */
	public static String doPost(String url,String bodys,Map<String,String> headers) throws ParseException, IOException{
		HttpPost post = new HttpPost(url);
		config(post);
		
		// 设置头信息
		if(headers != null && !headers.isEmpty()){
			for(Entry<String, String> mp : headers.entrySet()){
				post.setHeader(mp.getKey(), mp.getValue());
			}
		}
		
		if(bodys != null){
			post.setEntity(new StringEntity(bodys,"UTF-8"));
		}
		
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(post);
		try{
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		}finally{
			if(response != null){
				response.close();
			}
		}
	}
	
	
	
	/**
	 * 执行Post请求
	 * @param url
	 * @param nameValuePair 请求参数
	 * @param MultipartName 文件接收绑定名称
	 * @param file 上传的文件
	 * 
	 * @author lyl
	 * 2015年12月17日
	 */
	public static String doPost(String url,List<NameValuePair> nameValuePair,String MultipartName,File file) throws ParseException, IOException{
		HttpPost post = new HttpPost(url);
		config(post);
		
		if(nameValuePair != null){
			post.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
		}
		
		if(file != null){
			FileBody bin = new FileBody(file);

            HttpEntity reqEntity = MultipartEntityBuilder.create()
	                    .addPart(MultipartName, bin)
	                    .build();


	        post.setEntity(reqEntity);
		}
		
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(post);
		try{
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		}finally{
			if(response != null){
				response.close();
			}
		}
	}
	
	
	/**
	 * 执行delete请求
	 * @author lyl
	 * 2015年12月17日
	 */
	public static String doDelete(String url) throws ParseException, IOException{
		HttpDelete delete = new HttpDelete(url);
		config(delete);
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(delete);
		try{
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		}finally{
			if(response != null){
				response.close();
			}
		}
	}
	
	/**
	 * 执行delete请求
	 * @author lyl
	 * 2015年12月17日
	 */
	public static String doDelete(String url,Map<String,String> headers) throws ParseException, IOException{
		HttpDelete delete = new HttpDelete(url);
		config(delete);
		
		// 设置头信息
		if(headers != null && !headers.isEmpty()){
			for(Entry<String, String> mp : headers.entrySet()){
				delete.setHeader(mp.getKey(), mp.getValue());
			}
		}
		
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(delete);
		try{
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		}finally{
			if(response != null){
				response.close();
			}
		}
	}
	
	/**
	 * put请求执行
	 * @author lyl
	 * 2016年7月8日
	 */
	public static String doPut(String url,List<NameValuePair> nameValuePair){
		HttpPut put = new HttpPut(url);
		config(put);
		// 执行get请求.
		CloseableHttpResponse response = null;
		try {
			if(nameValuePair != null){
				put.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
			}
			response = httpClient.execute(put);
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(response != null){
					response.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	
	
	/**
	 * put请求执行
	 * @author lyl
	 * 2016年7月8日
	 */
	public static String doPut(String url,List<NameValuePair> nameValuePair,Map<String,String> headers){
		HttpPut put = new HttpPut(url);
		config(put);
		
		// 设置头信息
		if(headers != null && !headers.isEmpty()){
			for(Entry<String, String> mp : headers.entrySet()){
				put.setHeader(mp.getKey(), mp.getValue());
			}
		}
		
		// 执行get请求.
		CloseableHttpResponse response = null;
		try {
			if(nameValuePair != null){
				put.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
			}
			response = httpClient.execute(put);
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(response != null){
					response.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	/**
	 * 执行Patch请求
	 * @author lyl
	 * 2015年12月17日
	 */
	public static String doPatch(String url,List<NameValuePair> nameValuePair) throws ParseException, IOException{
		HttpPatch patch = new HttpPatch(url);
		config(patch);
		
		if(nameValuePair != null){
			patch.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
		}
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(patch);
		try{
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		}finally{
			if(response != null){
				response.close();
			}
		}
	}
	
	/**
	 * 执行Patch请求
	 * @author lyl
	 * 2015年12月17日
	 */
	public static String doPatch(String url,List<NameValuePair> nameValuePair,Map<String,String> headers) throws ParseException, IOException{
		HttpPatch patch = new HttpPatch(url);
		config(patch);
		
		// 设置头信息
		if(headers != null && !headers.isEmpty()){
			for(Entry<String, String> mp : headers.entrySet()){
				patch.setHeader(mp.getKey(), mp.getValue());
			}
		}
		
		if(nameValuePair != null){
			patch.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
		}
		// 执行get请求.
		CloseableHttpResponse response = httpClient.execute(patch);
		try{
			// 获取响应实体
			return HttpUtils.getHttpEntity(response);
		}finally{
			if(response != null){
				response.close();
			}
		}
	}
	
	
	/**
	 * 获取HTTP响应实体
	 * @author lyl
	 * 2016年7月15日
	 */
	public static String getHttpEntity(CloseableHttpResponse response) throws ParseException, IOException{
		// 获取响应实体
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "utf-8");
	}
	
	
	
	/**
	 * 参数转换为键值对对象
	 * @author lyl
	 * 2016年7月8日
	 */
	public static List<NameValuePair> convertParam(Object obj){
		List<NameValuePair> ls = new ArrayList<NameValuePair>();
		JSONObject json = (JSONObject) JSONObject.toJSON(obj);
		if(json != null){
			for(Entry<String, Object> j : json.entrySet()){
				String key = j.getKey();
				Object value = j.getValue();
				if(value != null){
					String valueStr = null;
					if(value instanceof JSONArray){
						/*byte[] temp = CommonUtils.toByteArray(obj);
						try {
							valueStr = new String(temp,"utf-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						valueStr = String.valueOf(value).replace("[","").replace("]", "").replaceAll("\"", "");
					}else{
						valueStr = String.valueOf(value);
					}
					ls.add(new BasicNameValuePair(key, valueStr));
				}else{
					ls.add(new BasicNameValuePair(key, null));
				}
			}
		}
		return ls;
	}
	
	
	
	
	/**
	 * 上传临时文件到文件服务器中
	 * @param fileServerUrl 文件服务器地址   如：http://117.78.52.134:8091
	 * @param fileByte 文件字节
	 * @param suffix 后缀  如：.jpg/.doc
	 * 
	 * @return 临时文件所有参数
	 * 
	 * @author lyl
	 * 2017年8月14日
	 * @throws IOException 
	 */
//	public static JSONObject fileUploadTemp(String fileServerUrl,byte[] fileByte,String suffix) throws IOException{
//		String dir = "tempFiles";
//		File dirFile = new File(dir);
//		if(!dirFile.exists()){
//			dirFile.mkdirs();
//		}
//		File uploadfile = new File(dir+"//"+IDUtils.getUUID32()+suffix);
//		
//		FileOutputStream fos = new FileOutputStream(uploadfile);
//		IOUtils.write(fileByte, fos);
//		fos.close();
//		return fileUploadTemp(fileServerUrl,uploadfile,true);
//	}
	
	
	
	/**
	 * 上传临时文件到文件服务器中
	 * @param fileServerUrl 文件服务器地址   如：http://117.78.52.134:8091
	 * @param file 文件
	 * @param isDeleteFile 是否删除file文件，true:删除
	 * 
	 * @return 临时文件所有参数
	 * 
	 * @author lyl
	 * 2017年8月14日
	 * @throws IOException 
	 */
	public static JSONObject fileUploadTemp(String fileServerUrl,File file,boolean isDeleteFile) throws IOException{
		// 上传临时文件地址
		String uploadUrl = fileServerUrl + "/FileUploadTemp"; 
		// 向文件服务器中上传临时文件
		String result = HttpUtils.doPost(uploadUrl, null, "Filedata", file);
		if(isDeleteFile) {
			file.delete(); //  清除临时文件
		}
		return JSONObject.parseObject(result);
	}
	
	
	
	/**
	 * 上传文件到正式文件服务器中
	 * 
	 * @param fileServerUrl 文件服务器地址   如：http://117.78.52.134:8091
	 * @param filePath 存储到服务器上的相对位置 ，如：order(服务器上路径为/upload/formal/order/)
	 * @param file 文件
	 * @param suffix 后缀  如：.jpg/.doc
	 * 
	 * @return 正式文件的相对路径
	 * 
	 * @author lyl
	 * 2017年8月14日
	 * @throws IOException 
	 */
//	public static String flieUploadFormal(String fileServerUrl,String filePath,byte[] fileByte,String suffix) throws IOException{
//		// 上传临时文件
//		JSONObject jsonTemp = fileUploadTemp(fileServerUrl,fileByte,suffix);
//		// 临时文件名称
//		String fileName = jsonTemp.getString("fileName");
//		// 临时文件转正
//		String result = fileForamlPath(fileName,filePath,fileServerUrl);
//		return result;
//	}
	
	
	
	/**
	 * 上传文件到正式文件服务器中
	 * 
	 * @param fileServerUrl 文件服务器地址   如：http://117.78.52.134:8091
	 * @param filePath 存储到服务器上的相对位置 ，如：order(服务器上路径为/upload/formal/order/)
	 * @param file 文件
	 * 
	 * @return 正式文件的相对路径
	 * 
	 * @author lyl
	 * 2017年8月14日
	 * @throws IOException 
	 */
	public static String flieUploadFormal(String fileServerUrl,String filePath,File file) throws IOException{
		// 上传临时文件
		JSONObject jsonTemp = fileUploadTemp(fileServerUrl,file,false);
		// 临时文件名称
		String fileName = jsonTemp.getString("fileName");
		// 临时文件转正
		String result = fileForamlPath(fileName,filePath,fileServerUrl);
		return result;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 临时文件转正
	 * @param fileName 文件名称
	 * @param filePath 存储到服务器上的相对位置 ，如：order(服务器上路径为/upload/formal/order/)
	 * @param fileServerAddress 文件服务器地址
	 * 
	 * @return 返回正式文件所有参数
	 * @author lyl
	 */
	public static String fileFormal(String fileName, String filePath,String fileServerAddress){
		String result = "";
		StringBuffer url = new StringBuffer();
		url.append(fileServerAddress);
		url.append("/FileFormal");
		url.append("?filePath=");
		url.append(filePath);
		url.append("&tempPath[]=");
		url.append(fileName);
        try {
        	result = HttpUtils.doGet(url.toString());
        	result = result.replace("null(","").replace(")",""); // 处理数据结构
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
	}
	
	
	/**
	 * 临时文件转正，返回正式文件的相对路径
	 * @param fileName 文件名称
	 * @param filePath 存储到服务器上的相对位置 ，如：order(服务器上路径为/upload/formal/order/)
	 * @param fileServerAddress 文件服务器地址
	 * 
	 * @return 返回正式文件的相对路径
	 * @author lyl
	 * 2017年7月6日
	 */
	public static String fileForamlPath(String fileName, String filePath,String fileServerAddress){
		String result = "";
		String r = HttpUtils.fileFormal(fileName, filePath, fileServerAddress);
		if(StringUtils.isNotBlank(r)){
			JSONObject rJson = JSONObject.parseObject(r);
			JSONArray array = rJson.getJSONArray("fileList");
			JSONObject fileJson = (JSONObject) array.get(0);
			result = fileJson.getString("path");
		}
		return result;
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
	 * 
	* <p>Description: 解决base64编码出现/等特殊字符影响访问结果问题</p>  
	* @author lyl  
	* @param object 需要编码的对象
	* @date 2018年8月14日
	 */
	public static String encodeParams(Object object) {
		String jsonStr=JSONObject.toJSONString(object);
		String base64Str="";
		String urlStr="";
		try {
			base64Str=Base64Utils.encodeToString(jsonStr.getBytes("UTF-8"));
			urlStr=URLEncoder.encode(base64Str);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return urlStr;
	}
}
