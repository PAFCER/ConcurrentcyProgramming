package com.zzu.concurrency.GuardedSupensionDesignPattern;

import java.util.Random;

/**
 * Created by hotwater on 2019/4/21.
 * 请求服务端
 */
public class RequestServer extends Thread {

    private final Queue queque;
    private final Random random=new Random(System.currentTimeMillis());
    //服务是否开启
    private boolean opened=true;

    public RequestServer(Queue queque) {
        this.queque = queque;
    }

    @Override
    public void run() {
        while(opened){
            synchronized (queque){
                Request request = queque.getRequest();
                if(request==null){
                    continue;
                }
                System.err.println("The server  comsumer "+request+"    and "+random.nextInt(1000));
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    opened=false;
                    break;//中断的话进行
                }
            }
        }
    }

    /**
     * 关闭服务器
     */
    public  void close(){
        //关闭服务器标记
       opened=false;
       //中断信号
       this.interrupt();
        System.err.println("The Server closed");

    }
}
