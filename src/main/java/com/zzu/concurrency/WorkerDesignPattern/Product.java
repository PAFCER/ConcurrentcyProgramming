package com.zzu.concurrency.WorkerDesignPattern;

/**
 * Created by hotwater on 2019/4/22.
 */
public class Product {

    //名字
    private String productName;
    //序号
    private int req;

    public Product(String productName, int req) {
        this.productName = productName;
        this.req = req;
    }

    public String getProductName() {
        return productName;
    }

    public int getReq() {
        return req;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", req=" + req +
                '}';
    }
}
