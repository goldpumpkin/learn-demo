package com.gold.jpa.entity;

import com.gold.jpa.method.convert.CustomCryptoConverter;
import com.gold.jpa.method.cstombasictype.CustomCrypto;
import com.gold.jpa.model.CryptoParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author goldhuang
 * @Description:
 * @date 2021-08-20
 */
@Entity
@Table(name = "customer_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {

    /**
     * 注意这里：h2 的主键设置需要是 IDENTITY, not AUTO
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String customerName;

    private String password;

    @Convert(converter = CustomCryptoConverter.class)
    private CryptoParam customerNameCrypto;

    private CustomCrypto<String> basicTypeName;

    private String pwdCrypto;
}
