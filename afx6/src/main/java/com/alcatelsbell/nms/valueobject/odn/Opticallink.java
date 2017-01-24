package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import javax.persistence.Entity;

/**
 * Author: Ronnie.Chen
 * Date: 12-9-28
 * Time: 下午2:08
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Entity
public class Opticallink extends AbstractOdn{
    @BField(description = "光路编号")
    private String no;




    private String startPortDn;
    private String endPortDn;

    private String startDeviceDn;
    private String endDeviceDn;

    @BField(description = "起始端口名称")
    private String startPortName;
    @BField(description = "起始端口名称")
    private String endPortName;

    @BField(description = "起始设备名称")
    private String startDeviceName;
    @BField(description = "终止设备名称")
    private String endDeviceName;


    @BField(description = "施工状态" ,searchType = BField.SearchType.NULLABLE)
    @DicGroupMapping(groupName = "OPTICALLINK_CONSTRUCTSTATUS", definitionClass = OdnDictionary.class)
    private Integer consturctStatus;


    @BField(description = "数据校验状态",searchType = BField.SearchType.NULLABLE)
    @DicGroupMapping(groupName = "OPTICALLINK_VALIDATESTATUS", definitionClass = OdnDictionary.class)
    private Integer validateStatus;

    private Integer inputType;

    @BField(description = "路由数")
    private Integer routeNumber;

    public Integer getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(Integer routeNumber) {
        this.routeNumber = routeNumber;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public Integer getValidateStatus() {
        return validateStatus;
    }

    public void setValidateStatus(Integer validateStatus) {
        this.validateStatus = validateStatus;
    }

    public Integer getConsturctStatus() {
        return consturctStatus;
    }

    public void setConsturctStatus(Integer consturctStatus) {
        this.consturctStatus = consturctStatus;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }



    public String getStartPortDn() {
        return startPortDn;
    }

    public void setStartPortDn(String startPortDn) {
        this.startPortDn = startPortDn;
    }

    public String getEndPortDn() {
        return endPortDn;
    }

    public void setEndPortDn(String endPortDn) {
        this.endPortDn = endPortDn;
    }

    public String getStartDeviceDn() {
        return startDeviceDn;
    }

    public void setStartDeviceDn(String startDeviceDn) {
        this.startDeviceDn = startDeviceDn;
    }

    public String getEndDeviceDn() {
        return endDeviceDn;
    }

    public void setEndDeviceDn(String endDeviceDn) {
        this.endDeviceDn = endDeviceDn;
    }

    public String getStartPortName() {
        return startPortName;
    }

    public void setStartPortName(String startPortName) {
        this.startPortName = startPortName;
    }

    public String getEndPortName() {
        return endPortName;
    }

    public void setEndPortName(String endPortName) {
        this.endPortName = endPortName;
    }

    public String getStartDeviceName() {
        return startDeviceName;
    }

    public void setStartDeviceName(String startDeviceName) {
        this.startDeviceName = startDeviceName;
    }

    public String getEndDeviceName() {
        return endDeviceName;
    }

    public void setEndDeviceName(String endDeviceName) {
        this.endDeviceName = endDeviceName;
    }
}
