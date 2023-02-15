package com.gold.mongo.util;

import com.gold.mongo.constant.MongoCommonConsts;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.math.NumberUtils;
import org.bson.Document;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

@UtilityClass
public class MongoCriteriaHelper {

    private static final String ID = "id";

    /**
     * where 关键字后面可以跟 任何条件进行比较，包括两个字段进行比较
     */
    public Criteria where(String value) {
        return new Criteria() {
            @Override
            public Document getCriteriaObject() {
                final Document doc = new Document();
                doc.put(MongoCommonConsts.KEY_WHERE, value);
                return doc;
            }
        };
    }

    /**
     * 选取数据id小于输入id
     *
     * @param id 数据小于的id值
     */
    public Criteria ltId(String id) {
        return Criteria.where(ID).lt(id);
    }

    /**
     * 分页参数
     * <p>
     * id 倒排
     */
    public Pageable limitCountAndIdDesc(Integer pageSize) {
        final Sort idSort = Sort.by(Sort.Order.desc(ID));
        return PageRequest.of(NumberUtils.INTEGER_ZERO, pageSize, idSort);
    }

}
