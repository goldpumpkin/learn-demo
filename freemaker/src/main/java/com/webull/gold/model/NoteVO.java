package com.webull.gold.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class NoteVO implements Serializable {

    /**
     * 结算 info
     */
    private List<NoteEntity> list;

    /**
     * 账户姓名
     */
    private String accountName;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * principalTransaction 为No 时显示， Y
     */
    private String displayCrossTrade;

    /**
     * 状态
     */
    private String status;

    /**
     * 生成账单时间
     */
    private String dateOfIssuance;

    /**
     * 地址
     */
    private String address1;
    private String address2;
    private String address3;
    private String address4;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 城市 邮编
     */
    private String city;
    private String state;
    private String country;
    private String postalCode;

    /**
     * 联名账户信息
     */
    private String jointEmail;
    private String jointAccountName;
    private String jointAddress1;
    private String jointAddress2;
    private String jointAddress3;
    private String jointAddress4;
    private String jointCity;
    private String jointState;
    private String jointCountry;
    private String jointPostalCode;

}
