package com.cjx.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class TestDemo {
    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(2);
          Thread t1=  new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 1; i <=100; i++) {
                        System.out.println("线程" + Thread.currentThread().getName() + "执行" + i );
                        if (i == 10) {
                            try {
                                countDownLatch.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            });
        Thread t2=  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=100; i++) {
                    System.out.println("线程" + Thread.currentThread().getName() + "执行" + i);
                    if (i == 80) {
                        countDownLatch.countDown();
                    }
                }
            }
        });
        Thread t3=  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=100; i++) {
                    System.out.println("线程" + Thread.currentThread().getName() + "执行" +( i));
                    if (i == 80) {
                        countDownLatch.countDown();
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();

    }
}
