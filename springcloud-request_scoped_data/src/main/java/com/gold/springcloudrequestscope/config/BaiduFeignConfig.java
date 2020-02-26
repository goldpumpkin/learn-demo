package com.gold.springcloudrequestscope.config;


import com.gold.springcloudrequestscope.advice.RequestBodyAdvice;
import com.gold.springcloudrequestscope.util.RequestScopeBeanUtil;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class BaiduFeignConfig {


    @Bean
    public RequestInterceptor languageInterceptor() {
        return requestTemplate -> {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar calendar = Calendar.getInstance();
            requestTemplate.query("time", df.format(calendar.getTime()));

            // 获取request scope data - bizId
            Integer type = (Integer) RequestScopeBeanUtil.getAttribute(RequestBodyAdvice.BIZ_ID_PARAM_NAME);
            // 可以根据业务内容做一些事情，比如获取不同bizId请求第三方Api，需要生成不同的token
            requestTemplate.header((HttpHeaders.AUTHORIZATION), "Bear " + type);
        };
    }

}
