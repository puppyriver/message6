package com.alcatelsbell.nms.util.workline;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: Ronnie
 * Date: 11-11-30
 * Time: 下午10:59
 */
public class TestWorkerBus {
    static class MyWorker extends Worker {

        @Override
        public void work(WorkUnit workUnit) throws Exception {
            System.out.println("work:"+workUnit.getWorkObject());
        }

        @Override
        public void doNoMoreWorkUnit() throws Exception {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void doBeforeDestory() throws Exception {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
    public static void main(String[] args) throws InterruptedException {
        final WorkBus workBus = new WorkBus(1,MyWorker.class);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 100; i++) {
            final int idx = i;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    workBus.addWorkUnit(new WorkUnit("object:"+idx));
                }
            };
            executor.execute(r);

        }
        Thread.sleep(10000);
        for (int i = 101; i < 200; i++) {
            final int idx = i;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    workBus.addWorkUnit(new WorkUnit("object:"+idx));
                }
            };
            executor.execute(r);
        }
    }


}
