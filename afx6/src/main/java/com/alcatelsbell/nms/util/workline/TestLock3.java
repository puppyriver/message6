package com.alcatelsbell.nms.util.workline;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: Ronnie
 * Date: 11-11-25
 * Time: 下午1:58
 */
public class TestLock3 {
     private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void test() {
         lock.readLock().lock();
        System.out.println("getReadLock");
         lock.readLock().lock();
        System.out.println("getReadLock");
        System.out.println(lock.readLock().toString());
        lock.writeLock().lock();
        System.out.println("getWriteLock");
    }

    public static void main(String[] args) {
        new TestLock3().test();
    }
}
