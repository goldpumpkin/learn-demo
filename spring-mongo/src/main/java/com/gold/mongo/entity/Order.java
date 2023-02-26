package com.gold.mongo.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Field("id")
    private String id;

    private String accountId;

    private String userName;

    private Integer userAge;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

}
