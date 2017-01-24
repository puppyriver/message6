package com.alcatelsbell.nms.util.workline;

import java.util.HashMap;

/**
 * User: Ronnie.Chen
 * Date: 11-5-29
 * Time:
 */
public class WorkUnit {
    public WorkUnit(Object obj) {
        setWorkObject(obj);
    }

    private HashMap<Object,Object> values = new HashMap<Object, Object>();
    public Object getWorkObject() {
        return workObject;
    }

    public void setWorkObject(Object workObject) {
        this.workObject = workObject;
    }

    private Object workObject;

    public void setValue(Object key,Object value) {
        values.put(key,value);
    }

    public Object getValue(Object key){
        return values.get(key);
    }
}
