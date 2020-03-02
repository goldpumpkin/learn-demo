package com.gold.springremove_if_else.bizbean.orderstatus;

import org.springframework.stereotype.Service;

@Service
public class OrderStatus1 extends IOrderHandler {

    @Override
    public Integer getType() {
        return 1;
    }

    @Override
    public void handle(CommonOrderStatusReq req) {
        System.out.println(req.getStatus());
    }
}
