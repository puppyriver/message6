package com.alcatelsbell.nms.valueobject.alarm;

import com.alcatelsbell.nms.valueobject.BObject;
import org.hibernate.annotations.Index;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Ronnie.Chen
 * Date: 11-5-23
 * Time:
 */
@Table(name = "F_Alarminformation")
@Entity
public class Alarminformation extends BObject implements Serializable{
	private String eventname;    //告警名称


    @Temporal(TemporalType.TIMESTAMP)
	private Date netime;         // 收到告警时间

    @Temporal(TemporalType.TIMESTAMP)
	private Date emstime;        // 告警信息中的产生时间
	private int severity;        // 告警级别 参见 com.alcatelsbell.nms.common.SysConst.ALARM_SEVERITY_

	private String sourceobjectname; // 告警定位信息，使用SysUtil.createSourceObjectName 方法获取，若参数不满足则自行添加，满足格式即可
	private String description;      //告警描述信息
	private String alarmtype;        //告警类型，如果告警信息中无告警类型则为空

	private int objecttype;           // 告警对象类别 参见 com.alcatelsbell.nms.common.SysConst.ALARM_OBJECTTYPE_
	private String objectname;         //针对告警对象类别所获取的对象名称，比如告警对象类别为网元，则填写网元名称

	private String addintionalinfo;      //附加信息

	private String menativeemsname;              //告警网元的唯一标示 ，和网元的natvieemsname对应

    @Index(name = "AI_INDEX_CORRELATIONID")
	private String correlationId;       //重要！自己拼的字符串，唯一确定一个告警信息，告警server会根据该字段来归并重复告警.

	private int fieldType;                 //专业类型  参见  com.alcatelsbell.nms.common.SysConst.EVENT_FIELDTYPE  若没有请自行添加

    private String emsDn;             //EMS  唯一的接口名称，如"ZHIZHENG-ALARM"

    @Temporal(TemporalType.TIMESTAMP)
    private Date clear_time;         //清除时间

/* ************************************************************************** */
    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public Date getNetime() {
        return netime;
    }

    public void setNetime(Date netime) {
        this.netime = netime;
    }

    public Date getEmstime() {
        return emstime;
    }

    public void setEmstime(Date emstime) {
        this.emstime = emstime;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getSourceobjectname() {
        return sourceobjectname;
    }

    public void setSourceobjectname(String sourceobjectname) {
        this.sourceobjectname = sourceobjectname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlarmtype() {
        return alarmtype;
    }

    public void setAlarmtype(String alarmtype) {
        this.alarmtype = alarmtype;
    }

    public int getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(int objecttype) {
        this.objecttype = objecttype;
    }

    public String getObjectname() {
        return objectname;
    }

    public void setObjectname(String objectname) {
        this.objectname = objectname;
    }

    public String getAddintionalinfo() {
        return addintionalinfo;
    }

    public void setAddintionalinfo(String addintionalinfo) {
        this.addintionalinfo = addintionalinfo;
    }

    public String getMenativeemsname() {
        return menativeemsname;
    }

    public void setMenativeemsname(String menativeemsname) {
        this.menativeemsname = menativeemsname;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public String getEmsDn() {
        return emsDn;
    }

    public void setEmsDn(String emsDn) {
        this.emsDn = emsDn;
    }

    public Date getClear_time() {
        return clear_time;
    }

    public void setClear_time(Date clear_time) {
        this.clear_time = clear_time;
    }

//    返回标识确定处理该告警信息的方法，目前确定为EMS+专业，比如传输综合网管的SDH告警为一种处理方式
    public String getProcessKey() {
        return emsDn+"_"+fieldType;
    }
}
