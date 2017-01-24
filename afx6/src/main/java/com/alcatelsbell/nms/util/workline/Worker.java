package com.alcatelsbell.nms.util.workline;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: Ronnie.Chen
 * Date: 11-5-30
 * Time:
 */
public abstract class Worker extends Thread{

    private Log logger = LogFactory.getLog(getClass());
    private  long number = 0;

    public long getNumber() {
        return number;
    }
    public void resetNumber() {
        number = 0;
    }
    public void setNumber(long number) {
        this.number = number;
    }

    public WorkContext getWorkContext() {
        return workContext;
    }

    public void setWorkContext(WorkContext workContext) {
        this.workContext = workContext;
    }

    WorkContext workContext = null;
    public Worker(WorkContext workContext) {
        this.workContext = workContext;
    }

    public Worker() {

    }

    public void stopWorking() {
        stop = true;
    }

    private volatile boolean stop = false;

    @Override
    public void run() {
        stop = false;
        boolean  wait = false;
        while(!stop) {
            workContext.getLock().lock();
            WorkUnit wu = null;
             try {
                while ((wu = workContext.getWorkUnit()) == null){
                    doNoMoreWorkUnit();
                    wait = true;
                    workContext.waitForWork();
                }

                 if (wait) {
                     wait = false;
                     logger.debug("waked!queue size="+workContext.getQueueSize());
                 }
            } catch (Exception e) {
                logger.error(e,e);
            } finally {
                workContext.getLock().unlock();

            }
            if (wu != null) {
                try {
                    work(wu);
                    workContext.finishWorkUnit(wu);
                    number++;
                } catch (Throwable e) {
                    logger.error(e,e);
                }
            }

        }
        try {
            doBeforeDestory();
        } catch (Exception e) {
            logger.error(e,e);
        }

    }

    /**
     *  work with workunit
     * @param workUnit
     * @throws Exception
     */
    abstract public void work(WorkUnit workUnit) throws Exception;

    /**
     *  invoked when no more workunit taked.
     * @throws Exception
     */
    abstract public void doNoMoreWorkUnit() throws Exception;

    abstract public void doBeforeDestory() throws Exception;




    public static void main(String[] args) throws Exception {
        WorkContext ctx = new WorkContext(null);
        final Worker worker = new Worker(ctx){
            @Override
            public void work(WorkUnit workUnit) throws Exception {
                System.out.println("working with:"+workUnit.getWorkObject());
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void doNoMoreWorkUnit() throws Exception {
                System.out.println("doNoMOreWorkUnit");
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void doBeforeDestory() throws Exception {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        worker.start();

        int i = 0;
        while (true) {
            Thread.sleep(5000l);
            ctx.addWorkUnit(new WorkUnit("obj:"+(i++)));


        }
    }
}
