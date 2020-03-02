package com.gold.springremove_if_else.controller;

import com.gold.springremove_if_else.bizbean.action.ActionReq;
import com.gold.springremove_if_else.bizbean.action.IActionHandler;
import com.gold.springremove_if_else.bizbean.orderstatus.CommonOrderStatusReq;
import com.gold.springremove_if_else.bizbean.orderstatus.IOrderHandler;
import com.gold.springremove_if_else.handler.HandlerManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    @Autowired
    private final HandlerManager handlerManager;

    @RequestMapping(value = "/order-status", method = RequestMethod.POST)
    public String orderStatus(@RequestBody CommonOrderStatusReq req) {
        System.out.println(req);
        IOrderHandler iOrderHandler = (IOrderHandler) handlerManager.getHandler(IOrderHandler.class, req.getStatus());
        iOrderHandler.handle(req);
        return String.valueOf(req);
    }

    @RequestMapping(value = "/order-create", method = RequestMethod.POST)
    public void orderCreate(@RequestBody ActionReq req) {
        IActionHandler iActionHandler = (IActionHandler) handlerManager.getHandler(IActionHandler.class, req.getActonType());
        iActionHandler.handle(req);
    }
}
