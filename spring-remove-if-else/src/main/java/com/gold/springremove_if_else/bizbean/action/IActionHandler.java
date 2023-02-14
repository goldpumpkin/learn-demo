package com.gold.springremove_if_else.bizbean.action;

import com.gold.springremove_if_else.handler.IHandler;

public abstract class IActionHandler implements IHandler {
    @Override
    public Class getKey() {
        return IActionHandler.class;
    }

    public abstract void handle(ActionReq req);
}
