package com.gold.mongo.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.gold.mongo.decorate.OrderDecorator;
import com.gold.mongo.entity.Order;
import com.gold.mongo.repo.mongotemplate.OrderRepository;
import com.gold.mongo.vo.OrderVO;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@RequestMapping("order")
@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderDecorator orderDecorator;

    @GetMapping("{id}")
    public String getById(@PathVariable("id") String id) {
        final Order order = orderRepository.getById(id);
        final OrderVO decorate = orderDecorator.decorate(order);
        return JSON.toJSONString(decorate);
    }

    @PostMapping("ids")
    public String listByIds(@RequestBody List<String> ids) {
        final List<Order> orders = orderRepository.listByIds(ids);
        final List<OrderVO> decorate = orderDecorator.decorate(orders);
        return JSON.toJSONString(decorate);

    }

    @GetMapping("page")
    public String listByPage(@RequestParam("accountId") String accountId,
                             @RequestParam("minCreateTime") Date minCreateTime,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        final List<Order> orders = orderRepository.listCondition(accountId, null, minCreateTime, pageSize);
        final List<OrderVO> decorate = orderDecorator.decorate(orders);
        return JSON.toJSONString(decorate);
    }

    @PostMapping("initial")
    public String initial() {
        final List<Order> list = Lists.newArrayList();
        int count = 0;
        while (true) {
            if (count >= 200) {
                break;
            }

            final String id = String.valueOf(UUID.fastUUID());
            final String accountId = RandomUtil.randomString(10);
            final String name = RandomUtil.randomString(5);
            final Integer age = RandomUtil.randomInt(10, 120);
            final BigDecimal price = RandomUtil.randomBigDecimal(BigDecimal.valueOf(10000.12));
            final DateTime now = DateTime.now();
            list.add(Order.builder()
                    .id(id)
                    .userName(name)
                    .userAge(age)
                    .accountId(accountId)
                    .price(price)
                    .createTime(now)
                    .updateTime(now)
                    .build());
            count++;
        }

        orderRepository.batchInsert(list);

        return JSON.toJSONString("succ" + count);
    }

}
