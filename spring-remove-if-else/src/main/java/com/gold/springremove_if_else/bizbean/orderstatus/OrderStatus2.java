package com.gold.springremove_if_else.bizbean.orderstatus;

import org.springframework.stereotype.Service;

@Service
public class OrderStatus2 extends IOrderHandler {

    @Override
    public Integer getType() {
        return 2;
    }

    @Override
    public void handle(CommonOrderStatusReq req) {
        System.out.println(req.getStatus());
    }
}
