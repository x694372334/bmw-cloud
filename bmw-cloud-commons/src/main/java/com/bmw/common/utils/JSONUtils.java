package com.bmw.common.utils;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON格式化工具
 * @author lyl
 * 2016年11月3日
 */
@SuppressWarnings("deprecation")
public class JSONUtils {
	
	
    /**
     * JSON格式化输出
     * @param jsonStr json串
     * 
     * @return 格式化结果
     * @author lyl
     * 2016年11月3日
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }
    
    /**
     * 添加space
     * @author lyl
     * 2016年11月3日
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
    
    
    /**
     * JSON格式拼装，对传入的参数进行循环的处理，如需顺序需传入有序Map
     * @author lyl
     * 2017年6月30日
     */
    public static String jsonWrapper(Map<String, String> map){
    	if(map == null){
    		return null;
    	}
    	int i = 0;
    	StringBuilder sb = new StringBuilder();
    	sb.append("{");
    	for(Entry<String, String> m : map.entrySet()){
    		
    		if(i > 0){
    			sb.append(",\"");
    		}else{
    			sb.append("\"");
    		}
    		sb.append(m.getKey());
    		sb.append("\":\"");
    		sb.append(m.getValue());
			sb.append("\"");
    		
    		i++;
    	}
    	sb.append("}");
    	
    	
    	return sb.toString();
    }
    
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 将Map<String, Object>转成Json串
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @return Json串
	 */
	public static String convert2Json(Map<?, ?> map) {
		// StringWriter不需要关闭IO流（可见Java源码）
		String result = null;
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, map);
			return sw.toString();
		} catch (Exception e) {
		}

		return result;
	}

	/**
	 * 将List转换成JSON字符串
	 * 
	 * @param datalistList
	 *            list集合
	 * 
	 *            list中可以是Map或者自定义的JavaBean
	 * 
	 * @return
	 */
	public static String convertList2Json(List<?> datalistList) {
		String result = null;
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, datalistList);
			return sw.toString();
		} catch (Exception e) {
		}

		return result;
	}

	/**
	 * 将java对象转换成JSON字符串
	 * 
	 * @param obj
	 *            java对象
	 * 
	 *            obj可以是java标准对象或者自定义的Javabean
	 * 
	 * @return
	 */
	public static String convertObject2Json(Object obj) {
		String result = null;
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, obj);
			result = sw.toString();
		} catch (Exception e) {
		}

		return result;
	}

	/**
	 * 将Json串转成Map 1
	 * 
	 * @param jsonText
	 *            Json串
	 * @return Map
	 */
	public static Map<?, ?> json2Map(String jsonText) {
		try {
			Map<?, ?> map = mapper.readValue(jsonText, HashMap.class);
			return map;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将Json串转成Map 2
	 * 
	 * @param jsonText
	 *            Json串
	 * @return Map
	 */	
	public static Map<?, ?> jsonToMap(String jsonText) {
		try {
			JsonFactory jsonFactory = new MappingJsonFactory();
			// Json解析器
			JsonParser jsonParser = jsonFactory.createJsonParser(jsonText);
			// 跳到结果集的开始
			jsonParser.nextToken();
			// 接受结果的HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			// while循环遍历Json结果
			while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
				// 跳转到Value
				jsonParser.nextToken();
				// 将Json中的值装入Map中
				map.put(jsonParser.getCurrentName(), jsonParser.getText());

			}
			return map;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 将json对象转换为java对象
	 * 
	 * @param jsonText
	 *            json字符串
	 * @param clazz
	 *            java对象class
	 * @return
	 */
	public static Object json2Object(String jsonText, Class<?> clazz) {
		Object obj = null;
		try {
			obj = mapper.readValue(jsonText, clazz);
		} catch (Exception e) {
		}

		return obj;
	}

	/**
	 * 将json字符串转换成List
	 * 
	 * 其中list可以包含自定义的java对象
	 * 
	 * @param jsonText
	 * @param clazz
	 * @return
	 */
	public static List<?> json2List(String jsonText, Class<?> clazz) {
		List<?> list = null;
		try {
			JavaType javaType = mapper.getTypeFactory()
					.constructParametricType(ArrayList.class, clazz);
			;
			list = mapper.readValue(jsonText, javaType);
		} catch (Exception e) {
		}

		return list;
	}

    

}
