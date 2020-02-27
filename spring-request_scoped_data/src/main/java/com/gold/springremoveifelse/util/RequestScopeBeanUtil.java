package com.gold.springremoveifelse.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class RequestScopeBeanUtil {

    private RequestScopeBeanUtil() {
    }

    public static void setAttribute(String name, Object data) {
        RequestContextHolder.currentRequestAttributes().setAttribute(name, data, RequestAttributes.SCOPE_REQUEST);
    }

    public static Object getAttribute(String name) {
        return RequestContextHolder.currentRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
