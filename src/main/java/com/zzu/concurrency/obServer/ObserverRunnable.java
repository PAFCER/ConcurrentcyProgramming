package com.zzu.concurrency.obServer;

/**
 * Created by hotwater on 2019/4/18.
 *
 * 观察者模式线程
 */
public abstract class ObserverRunnable implements Runnable {

    //监听器接口
    final protected ObServerListener obServerListener;

    //构造函数注入监听器
    public ObserverRunnable(final ObServerListener obServerListener) {
        this.obServerListener = obServerListener;
    }

    /**
     * 通知改变
     */

    public void notifyChange(final RunnableEvent runnableEvent) {

        obServerListener.handlerEvent(runnableEvent);
    }

    /**
     * 线程状态
     */
    protected enum ThreadState{
        RUNNING,DONE,ERROR;
    }

    /***
     * 运行时事件类
     */
   public class  RunnableEvent{
       private final ThreadState threadState;
       private final Thread thread;
       private final Throwable cause;

        public ThreadState getThreadState() {
            return threadState;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }

        public RunnableEvent(ThreadState threadState, Thread thread, Throwable cause) {
           this.threadState = threadState;
           this.thread = thread;
           this.cause = cause;
       }
   }

}
