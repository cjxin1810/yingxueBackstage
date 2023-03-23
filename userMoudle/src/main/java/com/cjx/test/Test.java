package com.cjx.test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Test  {
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("dddd" + Thread.currentThread().getName() + "来到停车场");
                        if (semaphore.availablePermits() == 0) {
                            System.out.println("车位不足，请耐心等待");
                        }
                        semaphore.acquire();//获取令牌尝试进入停车场
                        System.out.println(Thread.currentThread().getName() + "成功进入停车场");
                        Thread.sleep(new Random().nextInt(1000));
                        System.out.println(Thread.currentThread().getName() + "驶出停车场");
                        semaphore.release();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            },i+"号车");
            thread.start();
        }
    }
}
