package com.zzu.concurrency.FutureDesignPattern;

/**
 * Created by hotwater on 2019/4/21.
 * 任务传输的接口
 */
public interface FutureTask<T> {
    /**
     * 任务调用接口
     * @return
     */
    T call();
}

