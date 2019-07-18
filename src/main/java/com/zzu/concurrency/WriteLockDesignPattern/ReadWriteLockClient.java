package com.zzu.concurrency.WriteLockDesignPattern;

/**
 * Created by hotwater on 2019/4/21.
 */
public class ReadWriteLockClient {

    public static void main(String[] args) {

        ShareData shareData= new ShareData(10);
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new WriterWorker(shareData,"ABCDEFG").start();
        new WriterWorker(shareData,"abcdefg").start();

    }
}
