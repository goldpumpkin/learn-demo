package com.gold.mongo.decorate;

import cn.hutool.core.date.DateUtil;
import com.gold.mongo.entity.Order;
import com.gold.mongo.vo.OrderVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_MS_PATTERN;

@Service
public class OrderDecorator {

    public List<OrderVO> decorate(List<Order> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return Collections.emptyList();
        }

        return orders.stream().map(this::decorate).collect(Collectors.toList());
    }

    public OrderVO decorate(Order order) {
        final Date createTime = order.getCreateTime();
        final String createStr = DateUtil.format(createTime, NORM_DATETIME_MS_PATTERN);

        final Date updateTime = order.getUpdateTime();
        final String updateStr = DateUtil.format(updateTime, NORM_DATETIME_MS_PATTERN);

        return OrderVO.builder()
                .id(order.getId())
                .accountId(order.getAccountId())
                .price(order.getPrice())
                .userAge(order.getUserAge())
                .userName(order.getUserName())
                .createTime(createStr)
                .updateTime(updateStr)
                .build();
    }

}
