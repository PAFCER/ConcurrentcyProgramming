package com.zzu.concurrency.obServer;

import java.util.List;

/**
 * Created by hotwater on 2019/4/18.
 * 监听器
 */
public class DefaultObServerListener implements ObServerListener {



    public   void  concurrentQuery(List<String>queryIdList){

        if(queryIdList==null||queryIdList.isEmpty()) {
            return;//空的话直接返回
        }
        queryIdList.stream().forEach(t->new Thread(new ObserverRunnable(this) {
            @Override
            public void run() {
                try {
                    //线程执行
                    //线程开始运行
                    notifyChange(new RunnableEvent(ThreadState.RUNNING,Thread.currentThread(),null));
                    Thread.sleep(100L);
                    int error=1/0;
                    //线程完成
                    notifyChange(new RunnableEvent(ThreadState.DONE,Thread.currentThread(),null));
                }catch (Exception e){
                    //线程遇到异常
                    notifyChange(new RunnableEvent(ThreadState.ERROR,Thread.currentThread(),e));
                }

            }
        }, t).start());

    }


    //锁对象
    public final Object LOCK=new Object();
    /**
     * 处理事件
     *
     * @param runnableEvent
     */
    @Override
    public void handlerEvent(ObserverRunnable.RunnableEvent runnableEvent) {

        synchronized (LOCK) {
            System.err.println("线程" + runnableEvent.getThread() + "进入" + runnableEvent.getThreadState() + "状态，目前：" + runnableEvent.getCause());
        }
        }
}
