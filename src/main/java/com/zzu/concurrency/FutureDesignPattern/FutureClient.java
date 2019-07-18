package com.zzu.concurrency.FutureDesignPattern;

import java.util.function.Consumer;

/**
 * Created by hotwater on 2019/4/21.
 */
public class FutureClient {

    public static void main(String[] args) {

        //1.利用主动问询方式获取计算结果集
        FutureService futureService=new FutureService();
        Future future = futureService.submit(() -> {

            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "FINISHED";
        });


        System.err.println("Do  something  for you .");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("===="+future.get());




        //2.利用被动回调函数进行
        FutureService futureService1=new FutureService();
        Consumer consumer=new Consumer() {
            @Override
            public void accept(Object o) {

            }
        };
        Future future1 = futureService.submit(() -> {

            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "FINISHED2";
        },System.out::print);


        System.err.println("Do  something  for you .");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("===="+future1.get());
    }
}
