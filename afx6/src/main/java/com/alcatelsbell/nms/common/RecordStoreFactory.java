package com.alcatelsbell.nms.common;

/**
 * Author: Ronnie.Chen
 * Date: 12-11-28
 * Time: 下午1:52
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class RecordStoreFactory {
    public static RecordStore  createCurralarmRecordStore() {
//        String b = SysProperty.getString("mongodb","on");
//        if (!b.equals("on")) return null;
//        String host = SysProperty.getString("mongodb_host","10.181.130.10");
//        int port = SysProperty.getInt("mongodb_port",27017);
//        if (host != null && host.length() > 0 && port > 0) {
//            LogUtil.info(RecordStoreFactory.class,"init RecordStoreMongoDBImpl with : "+host+":"+port);
//            try {
//                return new RecordStoreMongoDBImpl(host,port);
//            } catch (UnknownHostException e) {
//                LogUtil.error(RecordStoreFactory.class, e, e);
//            }
//        }
//        return null;
        //return new RecordStoreSqliteImpl();
        return null;
    }

    public static void main(String[] args) {
        RecordStore curralarmRecordStore = RecordStoreFactory.createCurralarmRecordStore();

    }

}
