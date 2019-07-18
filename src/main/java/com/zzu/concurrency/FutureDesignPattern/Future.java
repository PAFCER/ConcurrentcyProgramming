package com.zzu.concurrency.FutureDesignPattern;

/**
 * Created by hotwater on 2019/4/21.
 * 船票接口
 * 未来涉及模式：
 *          核心在于将任务执行指向异步线程操作，完成之后会将结果回写船票
 */
public interface Future<T> {
    //获取结果集
    T get();
}
