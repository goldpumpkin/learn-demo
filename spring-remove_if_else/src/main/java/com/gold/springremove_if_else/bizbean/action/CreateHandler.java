package com.gold.springremove_if_else.bizbean.action;

import org.springframework.stereotype.Service;

@Service
public class CreateHandler extends IActionHandler {

    @Override
    public void handle(ActionReq req) {
        System.out.println("CreateHandler");
    }

    @Override
    public Integer getType() {
        return EnumAction.CREATE.getType();
    }
}
