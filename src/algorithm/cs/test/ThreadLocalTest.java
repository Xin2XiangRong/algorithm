package algorithm.cs.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Copyright: www.hikvision.com Inc. All rights reserved.
 * @ClassName: ThreadLocalTest
 * @Description: TODO
 * @author: chaishuai
 * @date: 2020/4/7 11:15
 * @version: V1.0.0
 */
public class ThreadLocalTest {

    //(1)创建ThreadLocal变量
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        //在main线程中添加main线程的本地变量
        //threadLocal.set("mainVal");
        //新创建一个子线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("subVal");
                System.out.println("sub:"+threadLocal.get());
            }
        });
        thread.start();
        //输出main线程中的本地变量值
        System.out.println("mainx:"+threadLocal.get());
    }
}
