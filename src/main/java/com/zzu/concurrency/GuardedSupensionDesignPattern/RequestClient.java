package com.zzu.concurrency.GuardedSupensionDesignPattern;

import java.util.Random;

/**
 * Created by hotwater on 2019/4/21.
 * 请求客户端
 */
public class RequestClient extends  Thread {

    /**
     * 队列
     */
    private final Queue queque;
    private final String clientName;

    private final Random random=new Random(System.currentTimeMillis());
    public RequestClient(Queue queque, String clientName) {
        this.queque = queque;
        this.clientName = clientName;
    }

    @Override
    public void run() {

        for (int x=0;x<100;x++) {
            queque.pullRequest(new Request("client request "+random.nextInt(1000)));
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
