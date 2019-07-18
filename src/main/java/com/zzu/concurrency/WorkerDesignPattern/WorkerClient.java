package com.zzu.concurrency.WorkerDesignPattern;

/**
 * Created by hotwater on 2019/4/22.
 * 生产线模式的测试验证
 */
public class WorkerClient {

    public static void main(String[] args) {
        //初始化生产线
        Chanle chanle= new Chanle(10);
        //生产线上全部开工
        chanle.startWorker();

        //生产线上的搬运工
        new TransaforWorker("apple",chanle).start();
        new TransaforWorker("banana",chanle).start();
        new TransaforWorker("orange",chanle).start();

    }
}
