package com.zzu.concurrency.WorkerDesignPattern;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hotwater on 2019/4/22.
 * //传送带上的工人
 */
public class TransaforWorker extends Thread {

    private final Chanle chanle;
    private final static  AtomicInteger atomicInteger = new AtomicInteger();
    private volatile boolean running = true;
    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public TransaforWorker(String workerName, Chanle chanle) {
        super(workerName);
        this.chanle = chanle;
    }

    @Override
    public void run() {

        while (running) {
            Product product = new Product(getName(), atomicInteger.getAndIncrement());
            this.chanle.push(product);
            System.err.println("The TransaforWorker ["+getName()+"]"+"   push  the product +"+product);
        }
    }

    public  void stopWorker(){
        this.running=false;
    }

}
