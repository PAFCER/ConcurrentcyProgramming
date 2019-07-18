package com.zzu.concurrency.GuardedSupensionDesignPattern;

/**
 * Created by hotwater on 2019/4/21.
 */
public class GuardedSuspensionDesignPattern {

    public static void main(String[] args) throws InterruptedException {
        Queue  queue= new Queue();
        RequestClient requestClient = new RequestClient(queue,"GUARDED  SUPENSION");
        requestClient.start();
        RequestServer requestServer=new RequestServer(queue);
        requestServer.start();
        Thread.sleep(10000L);
//        requestServer.join();//此处的join会让服务器线程一直处于等待状态waiting
        requestServer.close();
    }
}
