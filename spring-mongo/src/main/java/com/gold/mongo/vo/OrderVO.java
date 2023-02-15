package com.gold.mongo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {

    private String id;

    private String accountId;

    private String userName;

    private Integer userAge;

    private BigDecimal price;

    private String createTime;

    private String updateTime;

}
