package com.zzu.concurrency.GuardedSupensionDesignPattern;

/**
 * Created by hotwater on 2019/4/21.
 * 模拟客户端请求实体
 */
public class Request {
    //请求名称
    private String name;

    public Request(String name) {
        this.name = name;
    }
}
