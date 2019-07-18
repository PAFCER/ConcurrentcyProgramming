package com.zzu.concurrency.obServer;

/**
 * Created by hotwater on 2019/4/18.
 *
 * 观察者模式监听接口
 */
public interface ObServerListener {

    /**
     * 处理事件
     * @param runnableEvent
     */
    void handlerEvent(ObserverRunnable.RunnableEvent runnableEvent);
}
