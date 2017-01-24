package com.alcatelsbell.nms.alarm.service.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: Ronnie
 * Date: 11-11-25
 * Time: 下午3:02
 */
public class CurralarmLockManager {
    private Log logger = LogFactory.getLog(getClass());
    public int getLockNumber() {
        return lockMap.size();
    }


    private volatile boolean b_lock = false;
    private ReentrantLock opLock = new ReentrantLock();
    private Condition condition = opLock.newCondition();
    public void checkOpLock() {
        if (b_lock) {
            logger.info("oplock is locked ,waiting....");
            try {
                opLock.lock();
                condition.await();
            } catch (InterruptedException e) {
                 logger.error(e,e);
            } finally {
                opLock.unlock();
            }
        }
    }

    public synchronized void lockOp() {
        b_lock = true;
    }
    public synchronized void unlockOp() {
        b_lock = false;
        opLock.lock();
        try {
            condition.signalAll();
        } catch (Exception e) {
            logger.error(e,e);
        } finally {
            opLock.unlock();
        }
    }





    private ConcurrentHashMap<String,ReentrantReadWriteLock> lockMap = new ConcurrentHashMap<String, ReentrantReadWriteLock>();

    public void removeLock(String alarmId) throws IllegalCurralarmStateException {
//        ReentrantReadWriteLock lock = getRWLock(alarmId);
//        if (lock == null) throw new IllegalCurralarmStateException(alarmId,"lock not existed!");
//     //   lock.opLock().lock();
         lockMap.remove(alarmId);
//      //  lock.opLock().unlock();

    }

    /**
     * 可能会有问题，当一个lock已经被取到且锁住，
     */
    public void removeAllLock() {
        lockMap.clear();
//        synchronized (lockMap) {
//            Enumeration<String> keys = lockMap.keys();
//            while (keys.hasMoreElements()) {
//                String key = keys.nextElement();
//                ReentrantReadWriteLock lock = lockMap.get(key);
//                if (lock.getReadLockCount() == 0 && !lock.isWriteLocked())
//            }
//        }
    }


    public ReentrantReadWriteLock createRWLock(String alarmId) throws IllegalCurralarmStateException {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        synchronized (lockMap) {
            if (lockMap.get(alarmId) != null)
                throw new IllegalCurralarmStateException(alarmId," lock existed !");
            lockMap.put(alarmId,readWriteLock);

        }
        return readWriteLock;
    }
    public ReentrantReadWriteLock getRWLock(String alarmId) throws IllegalCurralarmStateException {
         ReentrantReadWriteLock readWriteLock = lockMap.get(alarmId);
        if (readWriteLock == null) throw new IllegalCurralarmStateException(alarmId," lock not existed !");
        return readWriteLock;
    }


    public static void main(String[] args) {
        final CurralarmLockManager lockManager = new CurralarmLockManager();
        final int[] alarms = new int[30];


        for (int i = 1; i <= 15000 ; i++) {
            final int idx = i;
            Runnable r = new Runnable() {
                @Override
                public void run() {

                    long alarmId = (idx % 15);

                    if (idx <= 15000) {
              //           lockManager.lockAlarmWrite(alarmId);
                        if (alarms[(int)alarmId] < 0)
                            alarms[(int)alarmId] = 1;
                        else {
                            int old = alarms[(int)alarmId]+1;
                            try {
                                Thread.sleep(30);
                            } catch (InterruptedException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                            alarms[(int)alarmId] = old +1;
                        }
                 //       lockManager.unlockAlarmWrite(alarmId);

                        if (alarms[(int)alarmId] == 1000)
                            System.out.println(alarmId + "::" +alarms[(int)alarmId]);
                    } else {
//                        lockManager.lockAlarmRead(alarmId+"");
//                        System.out.println(alarmId + "::" + alarms[(int) alarmId]);
//                        lockManager.unlockAlarmRead(alarmId+"");
                    }

                  //   System.out.println("lockManager = " + lockManager.getLockNumber());
                }
            };
            new Thread(r).start();

        }
    }

}
