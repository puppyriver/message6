package com.alcatelsbell.nms.valueobject.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * User: Ronnie.Chen
 * Date: 11-5-12
 * Time: 下午6:09
 */

@Entity
@Table(name = "R_PRODUCTRENTLINE")
@Inheritance(strategy = InheritanceType.JOINED)
public class RProductRentline extends RProduct implements java.io.Serializable {



	private String acustomername;
	private String acustomeraddress;
	private String aroomname;
	private String anename;
	private String aofficelogicport;
	private String aofficelogicddf;
	private String zcustomername;
	private String zcustomeraddress;
	private String zroomname;
	private String znename;
	private String zofficelogicport;
	private String zofficelogicddf;
	private String commnets;
	private String circuitcode;		//A端本地电路
	private String accessbandwidth;
	private String accessmethod;
	private String circuitlevel;
	private String circuitrequest;
	private String circuitprotect;
	private String office;
	private String accessmethodName;


    private String aend1info;	// A端设备一厂商<>A端设备一名称<>A端设备一端口<>A端设备一时隙
    private String aend2info;	
    private String aend3info;
    private String zend1info;
    private String zend2info;
    private String zend3info;


    private String aendcontactinfo;//A端客户名称<>A端客户地址
    private String zendcontactinfo;


    private String circuitno2;	//	A端二干电路	一干电路	Z端二干电路	Z端本地电路
    private String circuitno3;
    private String circuitno4;
    private String circuitno5;
    
    private String speed;  //速率
    private String cusAddressDis;  //客户终端地址分配
    private String carryType;	//承载类型
    private String kuaQuType;	//跨区类型
    
    private String aproductdeviceaddress1;   //A端业务设备一位置属性
	private String aproductdeviceaddress2;   //A端业务设备二位置属性
	private String aproductdeviceaddress3;   //A端业务设备三位置属性
	private String abasename;   //A端基站名称
	private String abaseno;     //A端基站编号
	
	private String zproductdeviceaddress1;   //z端业务设备一位置属性
	private String zproductdeviceaddress2;   //z端业务设备二位置属性
	private String zproductdeviceaddress3;   //z端业务设备三位置属性
	private String zbasename;   //z端基站名称
	private String zbaseno;     //z端基站编号
    


    public String getAproductdeviceaddress1() {
		return aproductdeviceaddress1;
	}

	public void setAproductdeviceaddress1(String aproductdeviceaddress1) {
		this.aproductdeviceaddress1 = aproductdeviceaddress1;
	}

	public String getAproductdeviceaddress2() {
		return aproductdeviceaddress2;
	}

	public void setAproductdeviceaddress2(String aproductdeviceaddress2) {
		this.aproductdeviceaddress2 = aproductdeviceaddress2;
	}

	public String getAproductdeviceaddress3() {
		return aproductdeviceaddress3;
	}

	public void setAproductdeviceaddress3(String aproductdeviceaddress3) {
		this.aproductdeviceaddress3 = aproductdeviceaddress3;
	}

	public String getAbasename() {
		return abasename;
	}

	public void setAbasename(String abasename) {
		this.abasename = abasename;
	}

	public String getAbaseno() {
		return abaseno;
	}

	public void setAbaseno(String abaseno) {
		this.abaseno = abaseno;
	}

	public String getZproductdeviceaddress1() {
		return zproductdeviceaddress1;
	}

	public void setZproductdeviceaddress1(String zproductdeviceaddress1) {
		this.zproductdeviceaddress1 = zproductdeviceaddress1;
	}

	public String getZproductdeviceaddress2() {
		return zproductdeviceaddress2;
	}

	public void setZproductdeviceaddress2(String zproductdeviceaddress2) {
		this.zproductdeviceaddress2 = zproductdeviceaddress2;
	}

	public String getZproductdeviceaddress3() {
		return zproductdeviceaddress3;
	}

	public void setZproductdeviceaddress3(String zproductdeviceaddress3) {
		this.zproductdeviceaddress3 = zproductdeviceaddress3;
	}

	public String getZbasename() {
		return zbasename;
	}

	public void setZbasename(String zbasename) {
		this.zbasename = zbasename;
	}

	public String getZbaseno() {
		return zbaseno;
	}

	public void setZbaseno(String zbaseno) {
		this.zbaseno = zbaseno;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getCusAddressDis() {
		return cusAddressDis;
	}

	public void setCusAddressDis(String cusAddressDis) {
		this.cusAddressDis = cusAddressDis;
	}

	public String getCarryType() {
		return carryType;
	}

	public void setCarryType(String carryType) {
		this.carryType = carryType;
	}

	public String getKuaQuType() {
		return kuaQuType;
	}

	public void setKuaQuType(String kuaQuType) {
		this.kuaQuType = kuaQuType;
	}

	public String getCircuitno2() {
        return circuitno2;
    }

    public void setCircuitno2(String circuitno2) {
        this.circuitno2 = circuitno2;
    }

    public String getCircuitno5() {
        return circuitno5;
    }

    public void setCircuitno5(String circuitno5) {
        this.circuitno5 = circuitno5;
    }

    public String getCircuitno4() {
        return circuitno4;
    }

    public void setCircuitno4(String circuitno4) {
        this.circuitno4 = circuitno4;
    }

    public String getCircuitno3() {
        return circuitno3;
    }

    public void setCircuitno3(String circuitno3) {
        this.circuitno3 = circuitno3;
    }


    public String getAendcontactinfo() {
        return aendcontactinfo;
    }

    public void setAendcontactinfo(String aendcontactinfo) {
        this.aendcontactinfo = aendcontactinfo;
    }

    public String getZendcontactinfo() {
        return zendcontactinfo;
    }

    public void setZendcontactinfo(String zendcontactinfo) {
        this.zendcontactinfo = zendcontactinfo;
    }




    public String getAend1info() {
        return aend1info;
    }

    public void setAend1info(String aend1info) {
        this.aend1info = aend1info;
    }

    public String getZend3info() {
        return zend3info;
    }

    public void setZend3info(String zend3info) {
        this.zend3info = zend3info;
    }

    public String getZend2info() {
        return zend2info;
    }

    public void setZend2info(String zend2info) {
        this.zend2info = zend2info;
    }

    public String getZend1info() {
        return zend1info;
    }

    public void setZend1info(String zend1info) {
        this.zend1info = zend1info;
    }

    public String getAend3info() {
        return aend3info;
    }

    public void setAend3info(String aend3info) {
        this.aend3info = aend3info;
    }

    public String getAend2info() {
        return aend2info;
    }

    public void setAend2info(String aend2info) {
        this.aend2info = aend2info;
    }





	// Constructors

	public String getAccessmethodName() {
		return accessmethodName;

	}

	public void setAccessmethodName(String accessmethodName) {
		this.accessmethodName = accessmethodName;
	}

	/** default constructor */
	public RProductRentline() {
	}



	/** full constructor */
	public RProductRentline(  String acustomername,
			String acustomeraddress, String aroomname, String anename,
			String aofficelogicport, String aofficelogicddf,
			String zcustomername, String zcustomeraddress, String zroomname,
			String znename, String zofficelogicport, String zofficelogicddf,
			String commnets, String circuitcode, String accessbandwidth,
			String accessmethod, String circuitlevel, String circuitrequest,
			String circuitprotect, String office) {

		this.acustomername = acustomername;
		this.acustomeraddress = acustomeraddress;
		this.aroomname = aroomname;
		this.anename = anename;
		this.aofficelogicport = aofficelogicport;
		this.aofficelogicddf = aofficelogicddf;
		this.zcustomername = zcustomername;
		this.zcustomeraddress = zcustomeraddress;
		this.zroomname = zroomname;
		this.znename = znename;
		this.zofficelogicport = zofficelogicport;
		this.zofficelogicddf = zofficelogicddf;
		this.commnets = commnets;
		this.circuitcode = circuitcode;
		this.accessbandwidth = accessbandwidth;
		this.accessmethod = accessmethod;
		this.circuitlevel = circuitlevel;
		this.circuitrequest = circuitrequest;
		this.circuitprotect = circuitprotect;
		this.office = office;
	}



	public String getAcustomername() {
		return this.acustomername;
	}

	public void setAcustomername(String acustomername) {
		this.acustomername = acustomername;
	}

	public String getAcustomeraddress() {
		return this.acustomeraddress;
	}

	public void setAcustomeraddress(String acustomeraddress) {
		this.acustomeraddress = acustomeraddress;
	}

	public String getAroomname() {
		return this.aroomname;
	}

	public void setAroomname(String aroomname) {
		this.aroomname = aroomname;
	}

	public String getAnename() {
		return this.anename;
	}

	public void setAnename(String anename) {
		this.anename = anename;
	}

	public String getAofficelogicport() {
		return this.aofficelogicport;
	}

	public void setAofficelogicport(String aofficelogicport) {
		this.aofficelogicport = aofficelogicport;
	}

	public String getAofficelogicddf() {
		return this.aofficelogicddf;
	}

	public void setAofficelogicddf(String aofficelogicddf) {
		this.aofficelogicddf = aofficelogicddf;
	}

	public String getZcustomername() {
		return this.zcustomername;
	}

	public void setZcustomername(String zcustomername) {
		this.zcustomername = zcustomername;
	}

	public String getZcustomeraddress() {
		return this.zcustomeraddress;
	}

	public void setZcustomeraddress(String zcustomeraddress) {
		this.zcustomeraddress = zcustomeraddress;
	}

	public String getZroomname() {
		return this.zroomname;
	}

	public void setZroomname(String zroomname) {
		this.zroomname = zroomname;
	}

	public String getZnename() {
		return this.znename;
	}

	public void setZnename(String znename) {
		this.znename = znename;
	}

	public String getZofficelogicport() {
		return this.zofficelogicport;
	}

	public void setZofficelogicport(String zofficelogicport) {
		this.zofficelogicport = zofficelogicport;
	}

	public String getZofficelogicddf() {
		return this.zofficelogicddf;
	}

	public void setZofficelogicddf(String zofficelogicddf) {
		this.zofficelogicddf = zofficelogicddf;
	}

	public String getCommnets() {
		return this.commnets;
	}

	public void setCommnets(String commnets) {
		this.commnets = commnets;
	}

	public String getCircuitcode() {
		return this.circuitcode;
	}

	public void setCircuitcode(String circuitcode) {
		this.circuitcode = circuitcode;
	}

	public String getAccessbandwidth() {
		return this.accessbandwidth;
	}

	public void setAccessbandwidth(String accessbandwidth) {
		this.accessbandwidth = accessbandwidth;
	}

	public String getAccessmethod() {
		return this.accessmethod;
	}

	public void setAccessmethod(String accessmethod) {
		this.accessmethod = accessmethod;
	}

	public String getCircuitlevel() {
		return this.circuitlevel;
	}

	public void setCircuitlevel(String circuitlevel) {
		this.circuitlevel = circuitlevel;
	}

	public String getCircuitrequest() {
		return this.circuitrequest;
	}

	public void setCircuitrequest(String circuitrequest) {
		this.circuitrequest = circuitrequest;
	}

	public String getCircuitprotect() {
		return this.circuitprotect;
	}

	public void setCircuitprotect(String circuitprotect) {
		this.circuitprotect = circuitprotect;
	}

	public String getOffice() {
		return this.office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

}