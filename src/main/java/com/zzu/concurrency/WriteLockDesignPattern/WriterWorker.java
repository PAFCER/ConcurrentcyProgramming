package com.zzu.concurrency.WriteLockDesignPattern;

import java.util.Random;

/**
 * Created by hotwater on 2019/4/21.
 * 写操作
 */
public class WriterWorker extends Thread {
    private final ShareData shareData;
    private final String fillerData;

    private final Random random=new Random(System.currentTimeMillis());

    public WriterWorker(ShareData shareData, String fillerData) {
        this.shareData = shareData;
        this.fillerData = fillerData;
    }

    @Override
    public void run() {
        try {
            while (true){
                char nextChar = this.nextChar();
                shareData.write(nextChar);
                Thread.sleep(random.nextInt(1000));
                System.err.println("The current Thread ["+Thread.currentThread().getName()+"]"+ " write "+nextChar);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

    }

    /**
     * 下一个
     * @return
     */
    private  int index=0;
    private char nextChar() {
         char charAt = fillerData.charAt(index);
        if(++index>=fillerData.length()){
            index=0;//重置
        }
        return charAt;
    }
}
