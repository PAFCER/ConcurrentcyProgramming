package com.zzu.concurrency.obServer;

import java.util.Arrays;

/**
 * Created by hotwater on 2019/4/18.
 */
public class ObserverTest {

    public static void main(String[] args) {


        new DefaultObServerListener().concurrentQuery(Arrays.asList("111","222","333","444","555"));

    }
}
