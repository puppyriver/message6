package com.alcatelsbell.nms.util.workline;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * User: Ronnie.Chen
 * Date: 11-5-29
 * Time:
 */
public class WorkBus<T>  {
    private List<Worker> workers = new ArrayList();
    private WorkBus nextBus = null;
    private Log logger = LogFactory.getLog(getClass());
    private WorkContext workContext = new WorkContext(this);
    private Class workerCls;
    private int workUnitLimit = -1;

    public WorkBus() {
    }
    public WorkBus(int size,Class workerCls,int workUnitLimit,WorkBus nextBus){
        this.workerCls = workerCls;
        this.workUnitLimit = workUnitLimit;
        for (int i = 0; i < size; i++) {
            try {
                this.addWorker((Worker)workerCls.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (nextBus != null)
            addNextBus(nextBus);
    }
    public WorkBus(int size,Class workerCls,int workUnitLimit){
        this(size,workerCls,workUnitLimit,null);
    }
    public WorkBus(int size,Class workerCls){
          this(size,workerCls,-1);
    }

    public void fireWorkUnitFinish(WorkUnit wu) {
        if (nextBus != null)
          nextBus.addWorkUnit(wu);
    }


    public WorkBus addNextBus(WorkBus bus) {
        nextBus = bus;
        return bus;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void addWorker(Worker worker) {
        worker.setWorkContext(workContext);
        this.workers.add(worker);
        worker.start();
    }

    public void stopWorker(Worker worker){
        worker.stopWorking();
    }


    public void removeAllWorkers() {
        if (workers != null) {
            for (Worker worker : workers)
                worker.stopWorking();
            this.workers.clear();
        }

    }

    public void addToken(T obj) {
        workContext.addWorkUnit(new WorkUnit(obj));
    }
    public void addWorkUnit(WorkUnit wu) {
        workContext.addWorkUnit(wu);
        if (workUnitLimit > 0 && workContext.getQueueSize() > workUnitLimit) {
            try {
                throw new TooManyWorkUnitException("Too many work units, size = "+workContext.getQueueSize()+" need more "+workerCls);
            } catch (TooManyWorkUnitException e) {
                logger.error(e,e);
            }
        }
    }

    public void start() {

    }




    public void doWork() {

    }


    public WorkContext getWorkContext() {
        return workContext;
    }

    public void setWorkContext(WorkContext workContext) {
        this.workContext = workContext;
    }

    public int getWorkUnitLimit() {
        return workUnitLimit;
    }

    public void setWorkUnitLimit(int workUnitLimit) {
        this.workUnitLimit = workUnitLimit;
    }
}
