package com.alcatelsbell.nms.util.cache;

import java.util.List;

/**
 * User: Ronnie
 * Date: 12-6-7
 * Time: 下午9:33
 */
public interface Cache {
    public void addObject(String id, Object object);
    public void updateObject(String id, Object object);
    public Object findObject(String id);
    public void removeObject(String id);
    public void removeAll();
    public int getSize();
    public List getKeys();
}
