package com.alcatelsbell.nms.valueobject.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * User: Ronnie.Chen
 * Date: 11-5-12
 * Time: 下午6:06
 * 语音专线
 */


@Entity
@Table(name = "R_PRODUCTPBXLINE")
@Inheritance(strategy = InheritanceType.JOINED)
public class RProductPBXLine extends RProduct implements java.io.Serializable {

    private String zcustomername ;  //Z端客户名称
    private String circuitCode ;//电路编号
    
    
    public String getCircuitCode() {
		return circuitCode;
	}

	public void setCircuitCode(String circuitCode) {
		this.circuitCode = circuitCode;
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

    public String getCustomerDevice1info() {
        return customerDevice1info;
    }

    public void setCustomerDevice1info(String customerDevice1info) {
        this.customerDevice1info = customerDevice1info;
    }

    public String getCustomerDevice2info() {
        return customerDevice2info;
    }

    public void setCustomerDevice2info(String customerDevice2info) {
        this.customerDevice2info = customerDevice2info;
    }

    public String getCustomerDevice3info() {
        return customerDevice3info;
    }

    public void setCustomerDevice3info(String customerDevice3info) {
        this.customerDevice3info = customerDevice3info;
    }

    private String ngnDeviceVendor;  //ngn设备厂商
    private String ngnDeviceNeName;     //ngn设备网元名称
    private String ngnDevicePort;  //ngn设备端口
    
    private String zcustomeraddress ;  //Z端客户地址
    private String zcontactor ;  //Z端联系人
    private String zcontacttype;  //Z端联系方式
    private String customerDevice1info;  // 客户侧设备一信息  
    private String customerDevice2info;  //客户侧设备二信息  
    private String customerDevice3info;   //客户侧设备三信息  
    private String num;  //号码

    public String getNum() {
        return num;
    }

    public String getNgnDeviceVendor() {
        return ngnDeviceVendor;
    }

    public void setNgnDeviceVendor(String ngnDeviceVendor) {
        this.ngnDeviceVendor = ngnDeviceVendor;
    }

    public String getNgnDeviceNeName() {
        return ngnDeviceNeName;
    }

    public void setNgnDeviceNeName(String ngnDeviceNeName) {
        this.ngnDeviceNeName = ngnDeviceNeName;
    }

    public String getNgnDevicePort() {
        return ngnDevicePort;
    }

    public void setNgnDevicePort(String ngnDevicePort) {
        this.ngnDevicePort = ngnDevicePort;
    }

    public void setNum(String num) {
        this.num = num;
    }

    /** default constructor */
        public RProductPBXLine() {
            
        }
        
}