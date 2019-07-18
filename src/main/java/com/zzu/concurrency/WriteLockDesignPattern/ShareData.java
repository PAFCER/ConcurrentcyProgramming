package com.zzu.concurrency.WriteLockDesignPattern;

/**
 * Created by hotwater on 2019/4/21.
 * 共享数据
 */
public class ShareData {
    //线程间共享数据
    private final char[] shareData;
    //读写锁
    private final ReadWriteLock lock = new ReadWriteLock();

    public ShareData(int size) {
        this.shareData = new char[size];
        for (int x = 0; x < size; x++) {
            shareData[x] = '*';//初始化数据
        }
    }

    /**
     * 读操作
     * @return
     */
    public synchronized char[] read() {
        try {
            lock.readLock();
            char[] result = new char[shareData.length];
            for (int x = 0; x < shareData.length; x++)
                result[x] = shareData[x];
            Thread.sleep(50);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unLockReadLock();
        }
        return null;
    }

    /**
     * 写操作
     */
    public synchronized void write(char c){
        lock.writeLock();
        try {
            for (int x=0;x<shareData.length;x++) {
                shareData[x]=c;
                Thread.sleep(10);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlockWriteLock();;
        }
    }

}
