package com.gold.spring_requested_scope_data.advice;

import com.alibaba.fastjson.JSON;
import com.gold.spring_requested_scope_data.controller.RequestScopeDataController;
import com.gold.spring_requested_scope_data.dto.CommonReq;
import com.gold.spring_requested_scope_data.util.RequestScopeBeanUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@ControllerAdvice(assignableTypes = {RequestScopeDataController.class})
public class RequestBodyAdvice extends RequestBodyAdviceAdapter {

    public static final String BIZ_ID_PARAM_NAME = "BIZ_ID";

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (body instanceof CommonReq) {
            CommonReq baseForm = JSON.parseObject(JSON.toJSONString(body), CommonReq.class);
            // can verify bizId & set it to Requested Scope
            RequestScopeBeanUtil.setAttribute(BIZ_ID_PARAM_NAME, baseForm.getBizId());
            System.out.println("[RequestBodyAdvice.afterBodyRead] SET request data ：" + "{BIZ_ID :" + baseForm.getBizId() + "}");
        }
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
