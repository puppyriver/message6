package com.alcatelsbell.nms.valueobject.pfms;

import com.alcatelsbell.nms.valueobject.BObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Author: Ronnie.Chen
 * Date: 2014/11/9
 * Time: 19:02
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Entity
@Table(name = "E_PFMENTITY")
public class PFMEntity extends BObject implements Serializable {
     private String deviceDn;
    private Long deviceId;
    private String category;
    private String userlabel;
    private String type;
    private String url;


    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceDn() {
        return deviceDn;
    }

    public void setDeviceDn(String deviceDn) {
        this.deviceDn = deviceDn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserlabel() {
        return userlabel;
    }

    public void setUserlabel(String userlabel) {
        this.userlabel = userlabel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
