package com.alcatelsbell.nms.util.workline;

import java.util.Date;

/**
 * User: Ronnie.Chen
 * Date: 11-5-31
 * Time:
 */
public class TestWorker extends Worker {
    public static boolean workerenought = true;
    private String name = null;
    public TestWorker(String name) {
        this.name = name;
    }
    public TestWorker(String name,WorkContext ctx) {
        this.name = name;
        this.setWorkContext(ctx);

    }
    public String toString() {
        return "TestWorker:"+name;
    }
    @Override
    public void work(WorkUnit workUnit) throws Exception {
        System.out.println(toString()+" working with "+workUnit.getWorkObject());
        if (!workerenought)
            Thread.sleep(10000);
    }

    @Override
    public void doNoMoreWorkUnit() throws Exception {
        System.out.println(toString()+" make a rest and wait for more works");
    }

    @Override
    public void doBeforeDestory() throws Exception {
        System.out.println(toString()+" existed");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Date(1293724800875L));
        WorkContext ctx = new WorkContext(null);

        for (int i = 0; i < 10; i++)
            ctx.addWorkUnit(new WorkUnit("workunit"+i));

        TestWorker worker = new TestWorker("worker"+"16",ctx);
        worker.start();
        for (int j = 0; j < 15; j++)
            new TestWorker("worker"+j,ctx).start();

        for (int i = 10; i < 100; i++) {
            ctx.addWorkUnit(new WorkUnit("workunit"+i));
            ctx.signalNotEmpty();
            Thread.sleep(100);
            if (i == 20) worker.stopWorking();
        }
    }


}
