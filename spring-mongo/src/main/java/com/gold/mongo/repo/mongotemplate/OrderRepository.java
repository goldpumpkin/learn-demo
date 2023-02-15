package com.gold.mongo.repo.mongotemplate;

import com.gold.mongo.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderRepository {

    Order getById(String id);

    List<Order> listByIds(List<String> ids);

    /**
     * 分页条件查询
     */
    List<Order> listCondition(String accountId, String lastId, Date minCreateTime, Integer pageSize);

    void batchInsert(List<Order> orders);
}
