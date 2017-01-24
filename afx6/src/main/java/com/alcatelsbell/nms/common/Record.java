package com.alcatelsbell.nms.common;

import com.alcatelsbell.nms.valueobject.BObject;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;

/**
 * Author: Ronnie.Chen
 * Date: 12-11-28
 * Time: 下午1:18
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class Record extends HashMap {
    public static final String KEY_TIMEPOINT = "RECORD_TIMEPOINT";
    public static final String KEY_TYPE = "RECORD_TYPE";
    public static final String KEY_COMMENTS = "RECORD_COMMENTS";
    public static final String KEY_TABLE_NAME = "RECORD_TABLE";
    public static final String KEY_DB_NAME = "RECORD_DB";
    public static final String KEY_OBJECT = "RECORD_OBJECT";
    public static final String KEY_ID = "RECORD_ID";
    private String dbName;
    private String tableName;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        put(KEY_DB_NAME,dbName);
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        put(KEY_TABLE_NAME,tableName);
        this.tableName = tableName;
    }

    public Date getTimePoint() {
        return (Date) get(KEY_TIMEPOINT);
    }

    public void setTimePoint(Date timePoint) {
        this.put(KEY_TIMEPOINT,timePoint);
    }

    public void setType(String type) {
        put(KEY_TYPE,type);
    }

    public String getType() {
        return (String) get(KEY_TYPE);
    }

    public void setID(String id) {
        put(KEY_ID,id);
    }

    public String getID() {
        return (String) get(KEY_ID);
    }

    public void setObject(Object object) {
        put(KEY_OBJECT,object);
    }

    public Object getObject() {
        return (Object) get(KEY_OBJECT);
    }

    public void setComments(String comments) {
        put(KEY_COMMENTS,comments);
    }
    public String getComments() {
        return (String) get(KEY_COMMENTS);
    }
    public static Record wrap(BObject object) {
        Record record = new Record();
        record.setTimePoint(new Date());
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                Object value = CommonUtil.getObjectFieldValue(field,object);
                if (value instanceof Number
                        || value instanceof String
                        || value instanceof Date)
                    record.put(field.getName(),value);
                else
                    record.put(field.getName(),value == null ? "":value.toString());
            }
        }
        return record;
    }
}
