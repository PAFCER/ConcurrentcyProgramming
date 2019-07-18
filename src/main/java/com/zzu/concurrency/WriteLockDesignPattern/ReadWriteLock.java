package com.zzu.concurrency.WriteLockDesignPattern;

/**
 * Created by hotwater on 2019/4/21.
 *      读写锁自实现
 */
public class ReadWriteLock {

    private int readingReaders=0;//读线程个数
    private int readingWaiters=0;//读等待线程个数
    private int writingReaders=0;//写线程个数
    private int writingWaiters=0;//写等待线程个数

    private boolean preperWriteThread=true;

  public   ReadWriteLock(){
        this(true);
    }
  public   ReadWriteLock(boolean preperWriteThread){
      this.preperWriteThread=preperWriteThread;
    }
    /**
     * 读锁
     * 获取读锁，如果写线程个数大于0则需要等待
     * 另外
     */
    public synchronized void readLock(){
        //读等待线程数++
        this.readingWaiters++;
            try {
                while(this.writingReaders>0||(preperWriteThread&&this.writingWaiters>0)){
                this.wait();
                }
                //获取到读锁则进行读线程数量++
                this.readingReaders++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //读线程等待数量--
                this.readingWaiters--;
            }
    }

    /**
     * 释放读锁
     */
    public synchronized void unLockReadLock(){
//        while(this.readingReaders>0){
            this.readingReaders--;
            //唤醒当前锁等待线程
            this.notifyAll();
//        }
    }

    /**
     * 申请写锁
     */
    public  synchronized void    writeLock(){
        //写等待线程++
        this.writingWaiters++;
        try {
            while(this.readingReaders>0||this.writingReaders>0){
                this.wait();
            }
            //获取到写锁就去进行线程写数量++
            this.writingReaders++;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //写等待线程--
            this.writingWaiters--;
        }
    }

    /**
     * 释放写锁
     */
    public synchronized void unlockWriteLock(){
//        while(this.writingReaders>0){
            this.writingReaders--;
            this.notifyAll();//唤醒其他线程进行
//        }
}

}
