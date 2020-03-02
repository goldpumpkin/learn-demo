package com.gold.springremove_if_else.handler;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class HandlerManager implements InitializingBean {

    private Map<Class, Map<Integer, IHandler>> handlerMap = new HashMap<>();

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, IHandler> handlers = ctx.getBeansOfType(IHandler.class);
        handlers.forEach((k, v) -> {
            Class key = v.getKey();
            Map<Integer, IHandler> internalMap = this.handlerMap.get(v.getKey());
            if (internalMap == null) {
                internalMap = new HashMap<>();
                this.handlerMap.put(key, internalMap);
            }
            internalMap.put(v.getType(), v);
        });
    }

    public <T extends IHandler> T getHandler(Class<T> clz, Integer type) {
        return Optional.ofNullable(handlerMap.get(clz)).map(e -> (T) e.get(type)).orElse(null);
    }

}
