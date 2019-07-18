package com.zzu.concurrency.WriteLockDesignPattern;

/**
 * Created by hotwater on 2019/4/21.
 * 读线程
 */
public class ReadWorker  extends Thread {


    private final ShareData shareData;

    /**
     * 读线程
     * @param shareData
     */
    public ReadWorker(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        while (true){
            char[] readBuffer = shareData.read();
            System.err.println("The  Current Thread ["+Thread.currentThread().getName()+"]"+"   read    "+String.valueOf(readBuffer));
        }
    }
}
