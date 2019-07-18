package com.zzu.concurrency.FutureDesignPattern;

import java.util.function.Consumer;

/**
 * Created by hotwater on 2019/4/21.
 */
public class FutureService<T> {

    /**
     * 利用task进行收集
     * @param futureTask
     * @return
     */
    Future<T> submit(FutureTask<T> futureTask){
        AsyFuture asyFuture=new AsyFuture();
        new Thread(){
            @Override
            public void run() {
                T call = futureTask.call();
                //通知任务完成
                asyFuture.haveDone(call);
            }
        }.start();

        return asyFuture;
    }

    /**
     * 利用回调函数进行被动接收结果集
     * @param futureTask
     * @param consumer
     * @return
     */
    Future<T> submit(FutureTask<T> futureTask, Consumer<T>consumer/**TODO 回调函数 */){
        AsyFuture asyFuture=new AsyFuture();
        new Thread(){
            @Override
            public void run() {
                T call = futureTask.call();
                //通知任务完成
                asyFuture.haveDone(call);
                //回调函数
                consumer.accept(call);
            }
        }.start();

        return asyFuture;
    }
}
