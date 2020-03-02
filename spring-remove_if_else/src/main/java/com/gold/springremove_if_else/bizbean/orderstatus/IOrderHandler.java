package com.gold.springremove_if_else.bizbean.orderstatus;

import com.gold.springremove_if_else.handler.IHandler;

public abstract class IOrderHandler implements IHandler {
    @Override
    public Class getKey() {
        return IOrderHandler.class;
    }

    public abstract void handle(CommonOrderStatusReq req);
}
