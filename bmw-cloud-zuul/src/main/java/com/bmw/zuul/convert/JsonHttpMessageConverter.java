package com.bmw.zuul.convert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * JSON输出处理
 * @author lyl
 * 2016年11月7日
 */
public class JsonHttpMessageConverter extends FastJsonHttpMessageConverter{

	private FastJsonConfig fastJsonConfig = new FastJsonConfig();


	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = outputMessage.getHeaders();
        ByteArrayOutputStream outnew = new ByteArrayOutputStream();

        // 格式化日期数据
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        int len = JSON.writeJSONString(outnew, //
                                       fastJsonConfig.getCharset(), //
                                       obj, //
                                       fastJsonConfig.getSerializeConfig(), //
                                       fastJsonConfig.getSerializeFilters(), //
                                       fastJsonConfig.getDateFormat(), //
                                       JSON.DEFAULT_GENERATE_FEATURE, //
                                       fastJsonConfig.getSerializerFeatures());
        headers.setContentLength(len);
        OutputStream out = outputMessage.getBody();
        outnew.writeTo(out);
        outnew.close();
    }

}
