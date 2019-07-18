package com.zzu.concurrency.WorkerDesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hotwater on 2019/4/22.
 * worker模式流水线工人模式
 * 此处是流水线
 */
public class Chanle {

    private final int MAX_DEFAULT = 100;
    private final int workerSize;//工人数量
    private volatile int head;//首
    private volatile int tail;//尾部
    private volatile int count;//当前流水线上的商品数量
    private Product[] productQueue;//生产线上的队列
    private List<ChanleWorker> chanleWorkerPool;

    /**
     * 初始化生产线
     *
     * @param workerSize
     */
    public Chanle(int workerSize) {
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.productQueue = new Product[MAX_DEFAULT];//初始化生产线队列大小
        //初始化工人池
        this.chanleWorkerPool = new ArrayList<>(workerSize);
        this.workerSize=workerSize;
        initChanleWorkerPool();
    }

    /**
     * 初始化工人池
     */
    private void initChanleWorkerPool() {
        for (int x = 0; x < workerSize; x++) {
            this.chanleWorkerPool.add(new ChanleWorker("Thread -["+x+"]",this));
        }
    }


    /**
     * 让所有的工人进行启动
     */
    public void startWorker(){
        //利用推导的方式进行开启所有的工人线程
        this.chanleWorkerPool.stream().forEach(t->t.start());
    }

    /**
     * 往流水线上放商品
     */
    public synchronized void push(Product product) {
        //生产线上的商品达到负载
        while (count >= this.productQueue.length) {
            try {
                //进行等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //可以推送的时候进行
        this.productQueue[tail] = product;
        //存放至队尾并对其进行追加尾部索引变更
        this.tail = (this.tail + 1) % this.productQueue.length;
        //流水线上的商品数量进行更新
        this.count++;
        //唤醒生产上等待流水线的
        this.notifyAll();
    }


    /**
     * 在流水线上进行处理商品
     *
     * @return
     */
    public synchronized Product take() {

        //流水线上没有商品进行等待
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //有的话就从队首去拿
        Product product = this.productQueue[this.head];
        //重置队首位置
        this.head = (this.head + 1) % this.productQueue.length;
        //进行更新流水线上的商品数量
        this.count--;
        //唤醒其他小伙伴
        this.notifyAll();
        return product;
    }
}
