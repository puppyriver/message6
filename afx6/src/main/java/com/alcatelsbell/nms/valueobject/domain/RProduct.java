package com.alcatelsbell.nms.valueobject.domain;




import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.Model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "R_PRODUCT")
@Inheritance(strategy = InheritanceType.JOINED)
public class RProduct extends BObject implements java.io.Serializable {

	// Fields

	private String name;           //业务名称
	private String code;           //业务编码
	private String comments;         //备注
	private String creator;        //
	private String updator;				//角色名----------------
	private String productspecid;
	private String productspecname;     //产品描述
	private String producttype;			//业务类型
	private String city;				//业务城市
	private String region;				//区县
	private String customerid;			//所属客户id
	private String customername;		//客户名称
	private String workticketid;
	private String workticketcode;		//角色id----------------
	private String servicelinecode;
	private String aaddress;
	private String acity;
	private String aregion;
	private String axcoordinate;
	private String aycoordinate;
	private String zaddress;
	private String zcity;
	private String zregion;
	private String zxcoordinate;
	private String zycoordinate;
	private String businesscontactor;      
	private String businesscontactorphone;     //联系方式
	private String serviceinfo;
	private String serviceopendate;				//业务开通时间
	private String servicestopdate;				//业务结束时间
	private String slalevel;					//sal等级
	private String networkoperlevel;
	private String productTypeName;
	private Long slaid;

	
    private String contactorname;   //省外客户联系人
    private String contactorinfo;	//省外客户联系方式
    private Integer maintaintype;	//维护方式
    private String maintaincompany;	//代维公司（部门）
    private String maintainowner;	//负责人
    private String maintaincontact; //联系方式
    
    private Date beginTime;
    private Date endTime;
    
    private Integer areatype;
    public String getMaintainer() {
		return maintainer;
	}

	public void setMaintainer(String maintainer) {
		this.maintainer = maintainer;
	}

	public String getMaintainercode() {
		return maintainercode;
	}

	public void setMaintainercode(String maintainercode) {
		this.maintainercode = maintainercode;
	}

	private String maintainer; 			//处理人
    private String maintainercode;		//处理id
	

	

    public Integer getAreatype() {
		return areatype;
	}

	public void setAreatype(Integer areatype) {
		this.areatype = areatype;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContactorname() {
        return contactorname;
    }

    public void setContactorname(String contactorname) {
        this.contactorname = contactorname;
    }

    public String getContactorinfo() {
        return contactorinfo;
    }

    public void setContactorinfo(String contactorinfo) {
        this.contactorinfo = contactorinfo;
    }

    public Integer getMaintaintype() {
        return maintaintype;
    }

    public void setMaintaintype(Integer maintaintype) {
        this.maintaintype = maintaintype;
    }

    public String getMaintaincompany() {
        return maintaincompany;
    }

    public void setMaintaincompany(String maintaincompany) {
        this.maintaincompany = maintaincompany;
    }

    public String getMaintainowner() {
        return maintainowner;
    }

    public void setMaintainowner(String maintainowner) {
        this.maintainowner = maintainowner;
    }

    public String getMaintaincontact() {
        return maintaincontact;
    }

    public void setMaintaincontact(String maintaincontact) {
        this.maintaincontact = maintaincontact;
    }


	public Long getSlaid() {
		return slaid;
	}

	public void setSlaid(Long slaid) {
		this.slaid = slaid;
	}

	@Model(type="new")
    private int alarmSeverity;



    @Model(type="model")
    private String customerdn;

	// Constructors

	/** default constructor */
	public RProduct() {
	}

	/** minimal constructor */
	public RProduct(String producttype) {
		this.producttype = producttype;
	}

	/** full constructor */
	public RProduct(String name, String code, String comments,
			String createdate, String creator, String updatedate,
			String updator, String productspecid, String productspecname,
			String producttype, String city, String region, String customerid,
			String customername, String workticketid, String workticketcode,
			String servicelinecode, String aaddress, String acity,
			String aregion, String axcoordinate, String aycoordinate,
			String zaddress, String zcity, String zregion, String zxcoordinate,
			String zycoordinate, String businesscontactor,
			String businesscontactorphone, String serviceinfo,
			String serviceopendate, String servicestopdate, String slalevel,
			String networkoperlevel) {
		this.name = name;
		this.code = code;
		this.comments = comments;
		this.creator = creator;
		this.updator = updator;
		this.productspecid = productspecid;
		this.productspecname = productspecname;
		this.producttype = producttype;
		this.city = city;
		this.region = region;
		this.customerid = customerid;
		this.customername = customername;
		this.workticketid = workticketid;
		this.workticketcode = workticketcode;
		this.servicelinecode = servicelinecode;
		this.aaddress = aaddress;
		this.acity = acity;
		this.aregion = aregion;
		this.axcoordinate = axcoordinate;
		this.aycoordinate = aycoordinate;
		this.zaddress = zaddress;
		this.zcity = zcity;
		this.zregion = zregion;
		this.zxcoordinate = zxcoordinate;
		this.zycoordinate = zycoordinate;
		this.businesscontactor = businesscontactor;
		this.businesscontactorphone = businesscontactorphone;
		this.serviceinfo = serviceinfo;
		this.serviceopendate = serviceopendate;
		this.servicestopdate = servicestopdate;
		this.slalevel = slalevel;
		this.networkoperlevel = networkoperlevel;

	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}



	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}



	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getProductspecid() {
		return this.productspecid;
	}

	public void setProductspecid(String productspecid) {
		this.productspecid = productspecid;
	}

	public String getProductspecname() {
		return this.productspecname;
	}

	public void setProductspecname(String productspecname) {
		this.productspecname = productspecname;
	}

	public String getProducttype() {
		return this.producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return this.customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getWorkticketid() {
		return this.workticketid;
	}

	public void setWorkticketid(String workticketid) {
		this.workticketid = workticketid;
	}

	public String getWorkticketcode() {
		return this.workticketcode;
	}

	public void setWorkticketcode(String workticketcode) {
		this.workticketcode = workticketcode;
	}

	public String getServicelinecode() {
		return this.servicelinecode;
	}

	public void setServicelinecode(String servicelinecode) {
		this.servicelinecode = servicelinecode;
	}

	public String getAaddress() {
		return this.aaddress;
	}

	public void setAaddress(String aaddress) {
		this.aaddress = aaddress;
	}

	public String getAcity() {
		return this.acity;
	}

	public void setAcity(String acity) {
		this.acity = acity;
	}

	public String getAregion() {
		return this.aregion;
	}

	public void setAregion(String aregion) {
		this.aregion = aregion;
	}

	public String getAxcoordinate() {
		return this.axcoordinate;
	}

	public void setAxcoordinate(String axcoordinate) {
		this.axcoordinate = axcoordinate;
	}

	public String getAycoordinate() {
		return this.aycoordinate;
	}

	public void setAycoordinate(String aycoordinate) {
		this.aycoordinate = aycoordinate;
	}

	public String getZaddress() {
		return this.zaddress;
	}

	public void setZaddress(String zaddress) {
		this.zaddress = zaddress;
	}

	public String getZcity() {
		return this.zcity;
	}

	public void setZcity(String zcity) {
		this.zcity = zcity;
	}

	public String getZregion() {
		return this.zregion;
	}

	public void setZregion(String zregion) {
		this.zregion = zregion;
	}

	public String getZxcoordinate() {
		return this.zxcoordinate;
	}

	public void setZxcoordinate(String zxcoordinate) {
		this.zxcoordinate = zxcoordinate;
	}

	public String getZycoordinate() {
		return this.zycoordinate;
	}

	public void setZycoordinate(String zycoordinate) {
		this.zycoordinate = zycoordinate;
	}

	public String getBusinesscontactor() {
		return this.businesscontactor;
	}

	public void setBusinesscontactor(String businesscontactor) {
		this.businesscontactor = businesscontactor;
	}

	public String getBusinesscontactorphone() {
		return this.businesscontactorphone;
	}

	public void setBusinesscontactorphone(String businesscontactorphone) {
		this.businesscontactorphone = businesscontactorphone;
	}

	public String getServiceinfo() {
		return this.serviceinfo;
	}

	public void setServiceinfo(String serviceinfo) {
		this.serviceinfo = serviceinfo;
	}

	public String getServiceopendate() {
		return this.serviceopendate;
	}

	public void setServiceopendate(String serviceopendate) {
		this.serviceopendate = serviceopendate;
	}

	public String getServicestopdate() {
		return this.servicestopdate;
	}

	public void setServicestopdate(String servicestopdate) {
		this.servicestopdate = servicestopdate;
	}

	public String getSlalevel() {
		return this.slalevel;
	}

	public void setSlalevel(String slalevel) {
		this.slalevel = slalevel;
	}

	public String getNetworkoperlevel() {
		return this.networkoperlevel;
	}

	public void setNetworkoperlevel(String networkoperlevel) {
		this.networkoperlevel = networkoperlevel;
	}



	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

    public int getAlarmSeverity() {
        return alarmSeverity;
    }

    public void setAlarmSeverity(int alarmSeverity) {
        this.alarmSeverity = alarmSeverity;
    }



    public String getCustomerdn() {
        return customerdn;
    }

    public void setCustomerdn(String customerdn) {
        this.customerdn = customerdn;
    }
}