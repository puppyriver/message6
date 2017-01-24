package com.alcatelsbell.nms.valueobject.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * User: Ronnie.Chen
 * Date: 11-5-12
 * Time: 下午6:06
 * 短彩信专线
 */


@Entity
@Table(name = "R_PRODUCTMSGLINE")
@Inheritance(strategy = InheritanceType.JOINED)
public class RProductMSGLine extends RProduct implements java.io.Serializable {
	
    
  //new
    private String acircuitCode;//A端电路编号
    private String sdhCircuitCode2;//传输二干电路编号
    private String zcircuitCode;//Z端电路编号
    
    public String getAcircuitCode() {
        return acircuitCode;
    }

    public void setAcircuitCode(String acircuitCode) {
        this.acircuitCode = acircuitCode;
    }

    public String getSdhCircuitCode2() {
        return sdhCircuitCode2;
    }

    public void setSdhCircuitCode2(String sdhCircuitCode2) {
        this.sdhCircuitCode2 = sdhCircuitCode2;
    }

    public String getZcircuitCode() {
        return zcircuitCode;
    }

    public void setZcircuitCode(String zcircuitCode) {
        this.zcircuitCode = zcircuitCode;
    }

    private String basestation1info;  //基站侧设备一信息
    private String basestation2info;  //基站侧设备二信息
    private String basestation3info;   //基站侧设备三信息
    
    private String basestationname;   //基站名称
    private String basestationno;    //基站编号
    private String odfddf;    //业务DDF/ODF位置
    private String circuitno;   //传输电路编号
    private String zcustomername;  //Z端客户名称
    private String zcustomeraddress;  //Z端客户地址
    private String zcontactor;   //Z端联系人
    private String zcontacttype;  //Z端联系人联系方式
    
    private String customer1info;  //客户侧设备一信息
    private String customer2info;  //客户侧设备一信息
    private String customer3info;  //客户侧设备一信息
    
    private String speed ;   //速率
    private String loadtype;  //承载类型
    
	public String getBasestation1info() {
        return basestation1info;
    }

    public void setBasestation1info(String basestation1info) {
        this.basestation1info = basestation1info;
    }

    public String getBasestation2info() {
        return basestation2info;
    }

    public void setBasestation2info(String basestation2info) {
        this.basestation2info = basestation2info;
    }

    public String getBasestation3info() {
        return basestation3info;
    }

    public void setBasestation3info(String basestation3info) {
        this.basestation3info = basestation3info;
    }

    public String getBasestationname() {
        return basestationname;
    }

    public void setBasestationname(String basestationname) {
        this.basestationname = basestationname;
    }

    public String getBasestationno() {
        return basestationno;
    }

    public void setBasestationno(String basestationno) {
        this.basestationno = basestationno;
    }

    public String getOdfddf() {
        return odfddf;
    }

    public void setOdfddf(String odfddf) {
        this.odfddf = odfddf;
    }

    public String getCircuitno() {
        return circuitno;
    }

    public void setCircuitno(String circuitno) {
        this.circuitno = circuitno;
    }

    public String getZcustomername() {
        return zcustomername;
    }

    public void setZcustomername(String zcustomername) {
        this.zcustomername = zcustomername;
    }

    public String getZcustomeraddress() {
        return zcustomeraddress;
    }

    public void setZcustomeraddress(String zcustomeraddress) {
        this.zcustomeraddress = zcustomeraddress;
    }

    public String getZcontactor() {
        return zcontactor;
    }

    public void setZcontactor(String zcontactor) {
        this.zcontactor = zcontactor;
    }

    public String getZcontacttype() {
        return zcontacttype;
    }

    public void setZcontacttype(String zcontacttype) {
        this.zcontacttype = zcontacttype;
    }

    public String getCustomer1info() {
        return customer1info;
    }

    public void setCustomer1info(String customer1info) {
        this.customer1info = customer1info;
    }

    public String getCustomer2info() {
        return customer2info;
    }

    public void setCustomer2info(String customer2info) {
        this.customer2info = customer2info;
    }

    public String getCustomer3info() {
        return customer3info;
    }

    public void setCustomer3info(String customer3info) {
        this.customer3info = customer3info;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLoadtype() {
        return loadtype;
    }

    public void setLoadtype(String loadtype) {
        this.loadtype = loadtype;
    }

	// Constructors

	/** default constructor */
	public RProductMSGLine() {
	    
	}

}