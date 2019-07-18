package com.zzu.concurrency.GuardedSupensionDesignPattern;

import java.util.LinkedList;

/**
 * Created by hotwater on 2019/4/21.
 * 请求队列
 */
public class Queue {

    private LinkedList<Request> queque = new LinkedList<>();

    /**
     * 从队列中获取请求
     * @return
     */
    public Request getRequest() {
        synchronized (queque) {
            while (queque.isEmpty()) {
                try {
                    queque.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Request request = queque.removeFirst();
            System.err.println("The Thread "+Thread.currentThread().getName()+"拿到了请求    request="+request);
            return  request;
        }
    }

    /**
     * 添加请求到队列
     * @param request
     */
    public void  pullRequest(Request request){
        synchronized (queque){
          queque.addLast(request);
            System.err.println("The Thread "+Thread.currentThread().getName()+"加入了请求    request="+request);
            queque.notifyAll();
        }
    }

}
