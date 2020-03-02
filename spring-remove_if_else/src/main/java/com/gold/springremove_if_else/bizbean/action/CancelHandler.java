package com.gold.springremove_if_else.bizbean.action;

import org.springframework.stereotype.Service;

@Service
public class CancelHandler extends IActionHandler {

    @Override
    public void handle(ActionReq req) {
        System.out.println("CanelHandler");
    }

    @Override
    public Integer getType() {
        return EnumAction.CANCEL.getType();
    }

}
