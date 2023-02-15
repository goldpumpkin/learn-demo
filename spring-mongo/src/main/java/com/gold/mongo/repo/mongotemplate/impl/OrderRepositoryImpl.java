package com.gold.mongo.repo.mongotemplate.impl;

import com.gold.mongo.entity.Order;
import com.gold.mongo.repo.mongotemplate.OrderRepository;
import com.gold.mongo.util.MongoCriteriaHelper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {


    private static final String COLLECTION = "order";

    /**
     * UTC 时间 format
     */
    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private final MongoTemplate mongoTemplate;

    @Override
    public Order getById(String id) {
        final Criteria idCriteria = Criteria.where("id").is(id);
        final Query query = Query.query(idCriteria);
        return mongoTemplate.findOne(query, Order.class, COLLECTION);
    }

    @Override
    public List<Order> listByIds(List<String> ids) {
        final Criteria idCriteria = Criteria.where("id").in(ids);
        final Query query = Query.query(idCriteria);
        return mongoTemplate.find(query, Order.class, COLLECTION);
    }

    @Override
    public List<Order> listCondition(String accountId, String lastId, Date minCreateTime, Integer pageSize) {
        final List<Criteria> andCriteriaList = Lists.newArrayList();
        final Criteria accountIdCriteria = Criteria.where("accountId").is(accountId);
        andCriteriaList.add(accountIdCriteria);

        if (Objects.nonNull(minCreateTime)) {
            final Criteria sTime = Criteria.where("createTime").gte(minCreateTime);
            andCriteriaList.add(sTime);
        }

        final Criteria addCriteria = new Criteria();
        addCriteria.andOperator(andCriteriaList.toArray(new Criteria[0]));

        pageSize = Objects.nonNull(pageSize) ? pageSize : 50;
        final Pageable pageable = MongoCriteriaHelper.limitCountAndIdDesc(pageSize);
        final Query q = Query.query(addCriteria).with(pageable);
        return mongoTemplate.find(q, Order.class, COLLECTION);
    }

    @Override
    public void batchInsert(List<Order> orders) {
        mongoTemplate.insert(orders, COLLECTION);
    }
}
