package com.alcatelsbell.nms.util.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: Ronnie
 * Date: 12-6-7
 * Time: 下午9:35
 */
public class ConcurrentHashMapCache implements Cache {
    private ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();

    @Override
    public void addObject(String id, Object element) {
        map.put(id,element);
    }

    @Override
    public void updateObject(String id, Object element) {
        map.put(id,element);
    }

    @Override
    public Object findObject(String id) {
        return map.get(id);
    }

    @Override
    public void removeObject(String id) {
        map.remove(id);
    }

    @Override
    public void removeAll() {
        map.clear();
    }

    @Override
    public int getSize() {
        return map.size();
    }

    @Override
    public List getKeys() {
        return new ArrayList(map.keySet());
    }
}
