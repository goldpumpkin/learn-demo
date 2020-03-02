package com.gold.springremove_if_else.handler;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class HandlerManager<T extends IHandler> implements InitializingBean {

    private Map<Class, Map<Integer, IHandler>> handlerMap = new HashMap<>();

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, IHandler> handlers = ctx.getBeansOfType(IHandler.class);
        handlers.forEach((k, v) -> {
            IHandler iHandler = v;
            Class key = iHandler.getKey();
            Map<Integer, IHandler> integerIHandlerMap = handlerMap.get(iHandler.getKey());
            if (integerIHandlerMap == null) {
                integerIHandlerMap = new HashMap<>();
                handlerMap.put(key, integerIHandlerMap);
            }
            integerIHandlerMap.put(iHandler.getType(), iHandler);
        });
    }

    public IHandler getHandler(Class<T> clz, Integer type) {
        return Optional.ofNullable(handlerMap.get(clz)).map(e -> e.get(type)).orElse(null);
    }

}
