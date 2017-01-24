package com.alcatelsbell.nms.util.workline;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Ronnie.Chen
 * Date: 11-5-31
 * Time:
 */
public class WorkLock extends ReentrantLock {
    public void lock() {
        System.out.println(this.toString()+" "+Thread.currentThread()+" Try to get lock");
        super.lock();
        System.out.println(this.toString()+" "+Thread.currentThread()+" get lock success");
    }

    public void unlock(){

        super.unlock();
        System.out.println(this.toString()+" "+Thread.currentThread()+" unlock");
    }
}
