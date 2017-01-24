package com.alcatelsbell.nms.valueobject.alarm;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author: Ronnie.Chen
 * Date: 12-11-30
 * Time: 下午3:18
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Table(name = "status_association")
@Entity
public class CurralarmAssociation extends BObject {
    private Long childserial;
    private Long parentserial;
    private Integer ruleid;
    private String rulename;
    private String resourcekey;
    private Date createtime;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getChildserial() {
        return childserial;
    }

    public void setChildserial(Long childserial) {
        this.childserial = childserial;
    }

    public Long getParentserial() {
        return parentserial;
    }

    public void setParentserial(Long parentserial) {
        this.parentserial = parentserial;
    }

    public Integer getRuleid() {
        return ruleid;
    }

    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }

    public String getRulename() {
        return rulename;
    }

    public void setRulename(String rulename) {
        this.rulename = rulename;
    }

    public String getResourcekey() {
        return resourcekey;
    }

    public void setResourcekey(String resourcekey) {
        this.resourcekey = resourcekey;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
