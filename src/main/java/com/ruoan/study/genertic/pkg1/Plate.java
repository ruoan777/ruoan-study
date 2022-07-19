package com.ruoan.study.genertic.pkg1;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年09月05日 15:39:00
 */
public class Plate<T>{
    private T item;

    public Plate(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
