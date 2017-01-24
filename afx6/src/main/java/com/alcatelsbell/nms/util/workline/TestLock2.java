package com.alcatelsbell.nms.util.workline;

import java.util.concurrent.locks.Condition;

/**
 * User: Ronnie.Chen
 * Date: 11-5-31
 * Time:
 */
public class TestLock2 implements  Runnable{
    private final WorkLock lock = new WorkLock();
    private final Condition notEmpty = lock.newCondition();
    public static void main(String[] args) {
         TestLock2 lock2 = new TestLock2();
         new Thread(lock2).start();
         new Thread(lock2).start();
         new Thread(lock2).start();

    }

    @Override
    public void run() {
        lock.lock();
        System.out.println("Current runing thread:"+Thread.currentThread());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        lock.unlock();
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
