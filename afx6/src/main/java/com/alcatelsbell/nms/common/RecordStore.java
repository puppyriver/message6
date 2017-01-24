package com.alcatelsbell.nms.common;

import java.util.List;

/**
 * Author: Ronnie.Chen
 * Date: 12-11-28
 * Time: 下午1:52
 * rongrong.chen@alcatel-sbell.com.cn
 */
public interface RecordStore {
    public Object createRecord(Record record) throws Exception;
    public Object deleteRecord(String id) throws Exception;
    public List findRecord(String exp) throws Exception;
    public Object updateRecord(String id, Record record) throws Exception;
    public Object clearMemory() throws Exception;
    public boolean reconnect() throws Exception;
    public boolean isEnable();
}
