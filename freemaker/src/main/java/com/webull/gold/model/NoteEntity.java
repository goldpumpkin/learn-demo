package com.webull.gold.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class NoteEntity implements Serializable {

    private String symbol;

    private BigDecimal brokerageGst;

    private String principalTransaction;

    private String expiryDate;

    private BigDecimal strikePrice;
}
