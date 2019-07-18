package com.zzu.concurrency.WorkerDesignPattern;

import java.util.Random;

/**
 * Created by hotwater on 2019/4/22.
 */
public class ChanleWorker extends Thread {

    private final Chanle chanle;
    private boolean running = true;
    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public ChanleWorker(String workerName, Chanle chanle) {
        super(workerName);
        this.chanle = chanle;
    }

    @Override
    public void run() {
        //一直工作状态下进行不同的执行流水线任务
        while (running) {
            //商品
            Product product = this.chanle.take();
            try {
                System.err.println("The worker ["+getName()+"]"+"   take  the product +"+product);
                //模拟工作延迟
                Thread.sleep(RANDOM.nextInt(1000));

                System.err.println("The worker ["+getName()+"]"+"   done  the product +"+product);

            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

    }
}
