package com.gold.springremove_if_else.bizbean.action;

public enum EnumAction {

    CREATE(1),
    CANCEL(2),
    ;

    private Integer type;

    EnumAction(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
