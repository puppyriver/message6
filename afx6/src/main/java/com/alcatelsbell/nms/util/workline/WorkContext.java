package com.alcatelsbell.nms.util.workline;

import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Ronnie.Chen
 * Date: 11-5-29
 * Time:
 */
public class WorkContext {
    public Hashtable<String,Object> valueMap = new Hashtable<String, Object>();
    private ConcurrentLinkedQueue<WorkUnit> workUnitQueue = new ConcurrentLinkedQueue<WorkUnit>();
    private ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    private WorkBus bus = null;
    public WorkContext(WorkBus bus) {
        this.bus = bus;
    }

    public ReentrantLock getLock() {
        return lock;
    }
    public int getQueueSize() {
        return workUnitQueue.size();
    }
    public void waitForWork() throws InterruptedException {
         notEmpty.await();
    }

    public void signalNotEmpty() {
        lock.lock();

        try {
            // use singal() instead of signalAll() can make the workers do less doNoMoreWorkUnit()
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object getObject(String name) {
        return valueMap.get(name);
    }

    /**
     *  Get a workunit
     *  @param block  if  queue is empty
     * @return
     */
    public WorkUnit getWorkUnit() throws InterruptedException {
        try {
            WorkUnit workUnit = workUnitQueue.remove();
            return workUnit;
        } catch (NoSuchElementException exp) {
            return null;
        }
    }

    public void addWorkUnit(WorkUnit wu) {
        workUnitQueue.offer(wu);
        signalNotEmpty();
    }

    public void finishWorkUnit(WorkUnit wu) {
        if (bus != null)
            bus.fireWorkUnitFinish(wu);
    }


}
