package com.alcatelsbell.nms.util.protocol.tl1;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * User: Ronnie
 * Date: 12-6-7
 * Time: 下午3:09
 */
public class TL1Event implements Serializable {
    private Date time;
    private String source;
    private String serial;
    private String type;
    private HashMap dataMap;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(HashMap dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public String toString() {
        return "TL1Event{" +
                "time=" + time +
                ", source='" + source + '\'' +
                ", serial='" + serial + '\'' +
                ", type='" + type + '\'' +
                ", dataMap=" + dataMap +
                '}';
    }
}
