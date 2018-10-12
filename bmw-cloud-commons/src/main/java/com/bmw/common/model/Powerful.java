package com.bmw.common.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.util.TypeUtils;

/**
 * 全能实体超类
 * 
 * @author lyl 2016年12月29日
 */
public class Powerful {


	public static Object toPowerful(Object javaObject) {
		return toPowerful(javaObject, SerializeConfig.globalInstance);
	}

	/**
	 * 转换object对象为PowerfulModel
	 * 
	 * @author lyl 2016年12月30日
	 */
	@SuppressWarnings("unchecked")
	public static Object toPowerful(Object javaObject, SerializeConfig config) {
		if (javaObject == null) {
			return null;
		}

		if (javaObject instanceof Map) {
			Map<Object, Object> map = (Map<Object, Object>) javaObject;

			PowerfulModel model = new PowerfulModel(map.size());

			for (Map.Entry<Object, Object> entry : map.entrySet()) {
				Object key = entry.getKey();
				String modelKey = TypeUtils.castToString(key);
				Object modelValue = toPowerful(entry.getValue());
				model.put(modelKey, modelValue);
			}

			return model;
		}

		if (javaObject instanceof Collection) {
			Collection<Object> collection = (Collection<Object>) javaObject;

			List<Object> array = new ArrayList<Object>(collection.size());

			for (Object item : collection) {
				Object modelValue = toPowerful(item);
				array.add(modelValue);
			}

			return array;
		}

		Class<?> clazz = javaObject.getClass();

		if (clazz.isEnum()) {
			return ((Enum<?>) javaObject).name();
		}

		if (clazz.isArray()) {
			int len = Array.getLength(javaObject);

			JSONArray array = new JSONArray(len);

			for (int i = 0; i < len; ++i) {
				Object item = Array.get(javaObject, i);
				Object jsonValue = toPowerful(item);
				array.add(jsonValue);
			}

			return array;
		}
		//未解决问题之一
//		if (ParserConfig.isPrimitive(clazz)) {
//			return javaObject;
//		}

		ObjectSerializer serializer = config.getObjectWriter(clazz);
		if (serializer instanceof JavaBeanSerializer) {
			JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer) serializer;

			PowerfulModel model = new PowerfulModel();
			try {
				Map<String, Object> values = javaBeanSerializer.getFieldValuesMap(javaObject);
				for (Map.Entry<String, Object> entry : values.entrySet()) {
					model.put(entry.getKey(), toPowerful(entry.getValue()));
				}
			} catch (Exception e) {
				throw new JSONException("toJSON error", e);
			}
			return model;
		}

		return javaObject;

	}

	@Override
	public String toString() {
		return toJSONString();
	}

	public String toJSONString() {
		SerializeWriter out = new SerializeWriter();
		try {
			new JSONSerializer(out).write(this);
			return out.toString();
		} finally {
			out.close();
		}
	}
	
	
	
	/**
	 * Bean集合进行顺序拼接，以bean1集合为基础进行拼接。
	 * @param bean1	bean集合
	 * @param bean2 bean集合
	 * 
	 * @return 返回拼装后的PowerfulModel集合,如果失败返回null;
	 * 
	 * @author lyl
	 * 2016年12月30日
	 */
	@SuppressWarnings("unchecked")
	public static List<PowerfulModel> jointArrayPoerfulModel(Object bean1,Object bean2){
		if(bean1 == null && bean2 == null){
			return null;
		}
		
		List<PowerfulModel> arrayBean1 = null;
		if(bean1 instanceof Collection){
			arrayBean1 = (List<PowerfulModel>) toPowerful(bean1);
		}
		
		List<PowerfulModel> arrayBean2 = null;
		if(bean2 instanceof Collection){
			arrayBean2 = (List<PowerfulModel>) toPowerful(bean2);
		}
		
		if(arrayBean1 != null && arrayBean2 == null){
			return arrayBean1;
		}else if(arrayBean1 == null && arrayBean2 != null){
			return arrayBean2;
		}else if(arrayBean1 == null && arrayBean2 == null){
			return null;
		}
		
		
		List<PowerfulModel> resultArray = new ArrayList<PowerfulModel>();
		
		PowerfulModel powerful1 = null;
		PowerfulModel powerful2 = null;
		
		for(int i=0;i<arrayBean1.size();i++){
			powerful1 = arrayBean1.get(i);
			powerful2 = arrayBean2.get(i);
			powerful1.putAll(powerful2);
			
			resultArray.add(powerful1);
			
		}
		
		return resultArray;
	}
	
	
	
	/**
	 * Bean集合进行匹配拼接，以bean1集合为基础进行拼接。
	 * @param bean1	bean集合
	 * @param bean2 bean集合
	 * @param matchField 进行匹配的字段名称
	 * 
	 * @return 返回拼装后的PowerfulModel集合,如果失败返回null;
	 * 
	 * @author lyl
	 * 2016年12月30日
	 */
	public static List<PowerfulModel> jointArrayPoerfulModel(Object bean1,Object bean2,String matchField){
		return jointArrayPoerfulModel(bean1,bean2,matchField,matchField);
	}
	
	
	
	
	/**
	 * Bean集合进行匹配拼接，以bean1集合为基础进行拼接。
	 * @param bean1	bean集合
	 * @param bean2 bean集合
	 * @param matchField1 进行匹配的字段名称
	 * @param matchField2 进行匹配的字段名称
	 * 
	 * @return 返回拼装后的PowerfulModel集合,如果失败返回null;
	 * 
	 * @author lyl
	 * 2016年12月30日
	 */
	@SuppressWarnings("unchecked")
	public static List<PowerfulModel> jointArrayPoerfulModel(Object bean1,Object bean2,String matchField1,String matchField2){
		if(StringUtils.isEmpty(matchField1) || StringUtils.isEmpty(matchField2)){
			return null;
		}else if(bean1 == null && bean2 == null){
			return null;
		}
		
		List<PowerfulModel> arrayBean1 = null;
		if(bean1 instanceof Collection){
			arrayBean1 = (List<PowerfulModel>) toPowerful(bean1);
		}
		
		List<PowerfulModel> arrayBean2 = null;
		if(bean2 instanceof Collection){
			arrayBean2 = (List<PowerfulModel>) toPowerful(bean2);
		}
		
		if(arrayBean1 != null && arrayBean2 == null){
			return arrayBean1;
		}else if(arrayBean1 == null && arrayBean2 != null){
			return arrayBean2;
		}else if(arrayBean1 == null && arrayBean2 == null){
			return null;
		}
		
		
		List<PowerfulModel> resultArray = new ArrayList<PowerfulModel>();
		
		PowerfulModel powerful1 = null;
		PowerfulModel powerful2 = null;
		String powerful1Field = "";
		String powerful2Field = "";
		for(int i=0;i<arrayBean1.size();i++){
			powerful1 = arrayBean1.get(i);
			powerful1Field = (String) powerful1.get(matchField1);
			if(powerful1Field == null){
				break;
			}
			for(int j=0;j<arrayBean2.size();j++){
				powerful2 = arrayBean2.get(j);
				powerful2Field = (String) powerful2.get(matchField2);
				if(powerful2Field == null){
					break;
				}
				
				if(powerful1Field.equals(powerful2Field)){
					powerful1.putAll(powerful2);
					break;
				}
				
			}
			
			resultArray.add(powerful1);
		}
		
		return resultArray;
		
	}

}
