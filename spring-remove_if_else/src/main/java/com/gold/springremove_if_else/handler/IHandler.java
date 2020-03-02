package com.gold.springremove_if_else.handler;

public interface IHandler<T extends Comparable<T>> {

    Class getKey();

    Integer getType();
}
