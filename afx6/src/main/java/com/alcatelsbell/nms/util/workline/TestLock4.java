package com.alcatelsbell.nms.util.workline;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Ronnie
 * Date: 12-5-19
 * Time: 下午10:19
 */
public class TestLock4 {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private volatile boolean b_globalLock = false;
    public void check() {
        if (b_globalLock) {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            lock.unlock();
        }
    }

    public void lock() {
        b_globalLock = true;
    }

    public void unlock() {
        b_globalLock = false;
        lock.lock();
        condition.signalAll();
        lock.unlock();
    }


    public static void main(String[] args) throws InterruptedException {
        final TestLock4 tl = new TestLock4();
        tl.lock();

        for (int i = 0; i < 10; i++) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    tl.check();
                    System.out.println(Thread.currentThread().getName());
                }
            };
            new Thread(r).start();
        }

        Thread.sleep(5000l);
        tl.unlock();

        Thread.sleep(1000000000l);
    }
}
