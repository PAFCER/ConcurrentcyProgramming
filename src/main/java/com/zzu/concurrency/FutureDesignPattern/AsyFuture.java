package com.zzu.concurrency.FutureDesignPattern;

/**
 * Created by hotwater on 2019/4/21.
 */
public class AsyFuture<T> implements Future<T> {

    //任务是否完成的标记
    private volatile boolean done=false;
    //线程处理后的结果集
    private T result;

    /**
     * 完成通知方法
     * @param result
     * @return
     */
    public synchronized  boolean haveDone(T result){
        //接收结果
        this.result=result;
        this.done=true;
        this.notifyAll();//唤醒其他线程
        return done;
    }


    @Override
    public synchronized T get() {
        while(!done){
            try {
                //如果没有完成就阻塞等待释放锁对象
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
