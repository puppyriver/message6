package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;
import com.alcatelsbell.nms.valueobject.physical.config.RackType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Aaron
 * Date 12.05.11
 * */
@XmlRootElement(name = "Rack")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rack")
@Entity
@Table(name = "O_RACK")
public class Rack extends AbstractOdn{

	private static final long serialVersionUID = 2353809133267244832L;
	
	@BField(description = "经度",sequence = 998)
	private double gis_x;
	
	@BField(description = "纬度",sequence = 999)
	private double gis_y;
	
	@BField(mergeType = BField.MergeType.RESERVED,viewType = BField.ViewType.HIDE)
	private String mgElementDn;

	//@BField(description = "机架名称",searchType = BField.SearchType.NULLABLE)
	private String rackname;
	
	@BField(description = "所在位置",editType = BField.EditType.REQUIRED,searchType=BField.SearchType.NULLABLE,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Room",
            dnReferenceTransietField = "room_name",
            dnReferenceEntityField = "roomname", mergeType = BField.MergeType.RESERVED)
	private String room_dn;

    @Transient
    private String room_name;
	private String rowindex;
	private String columnindex;

	
	@DicGroupMapping(groupName = "SIDEDCATEGORY", definitionClass = OdnDictionary.class)
	@BField(description = "单双面")
	private short sidedcategory;
	
	/*设备类型：ODF 1，光交接箱 2，光分纤盒 3*/
	@DicGroupMapping(groupName = "RACKTYPE", definitionClass = OdnDictionary.class)
	@BField(description = "设备类型",searchType = BField.SearchType.NULLABLE)
	private short racktype;


    @DicGroupMapping(groupName = "RACKSTATUS", definitionClass = OdnDictionary.class)
    @BField(description = "设备状态",searchType = BField.SearchType.NULLABLE)
    private short status = (short)OdnDictionary.RACKSTATUS.NO_POWER.value;
	
	/*机架类别：智能机架 1，传统机架2*/
	@DicGroupMapping(groupName = "RACKCATEGORY", definitionClass = OdnDictionary.class)
	@BField(description = "机架类别",searchType = BField.SearchType.NULLABLE)
	private short rackCategory;
	
	@DicGroupMapping(groupName = "ORDEROFARRANGE", definitionClass = OdnDictionary.class)
	@BField(description = "机框排列顺序")
	private short orderofchassis;
	
//	@DicGroupMapping(groupName = "RACKINSTALLATIONSIDE", definitionClass = OdnDictionary.class)
//	@BField(description = "机架属性",searchType = BField.SearchType.NULLABLE)
	private short rackinstallationside;

    @BField(description = "机框数",editType = BField.EditType.HIDE,createType = BField.CreateType.HIDE)
	private Integer shelfnumber;

    @BField(description = "机架模板类型",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
    		mergeType = BField.MergeType.RESERVED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.physical.config.RackType",
            dnReferenceTransietField = "rackTypeName",
            dnReferenceEntityField = "name")
    private String rackTypeDn;

    @Transient
    private String rackTypeName;
    
    @XmlTransient
    @Transient
    private RackType rackTypeEntity;

    private String elementId;


    @BField(description = "IP地址",searchType = BField.SearchType.NULLABLE, sequence = 1)
    private String ipAddress;

     @BField(description = "SNMP读团体",sequence = 2)
    private String readCommunity;

     @BField(description = "SNMP写团体",sequence = 3)
    private String writeCommunity;

    @BField(description = "厂商名称",
            createType = BField.CreateType.REQUIRED,
            editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.NULLABLE,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.sys.Vendor",
            dnReferenceTransietField = "vendorName",
            dnReferenceEntityField = "name")
    private String vendorDn;

    @Transient
    private String vendorName;

    private String instanceDn;

    public String getInstanceDn() {
        return instanceDn;
    }

    public void setInstanceDn(String instanceDn) {
        this.instanceDn = instanceDn;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorDn() {
        return vendorDn;
    }

    public void setVendorDn(String vendorDn) {
        this.vendorDn = vendorDn;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getReadCommunity() {
        return readCommunity;
    }

    public void setReadCommunity(String readCommunity) {
        this.readCommunity = readCommunity;
    }

    public String getWriteCommunity() {
        return writeCommunity;
    }

    public void setWriteCommunity(String writeCommunity) {
        this.writeCommunity = writeCommunity;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getRackTypeDn() {
        return rackTypeDn;
    }

    public void setRackTypeDn(String rackTypeDn) {
        this.rackTypeDn = rackTypeDn;
    }

    public String getRackname() {
		return rackname;
	}
	public void setRackname(String rackname) {
		this.rackname = rackname;
	}
	public String getRoom_dn() {
		return room_dn;
	}
	public void setRoom_dn(String room_dn) {
		this.room_dn = room_dn;
	}
	public String getRowindex() {
		return rowindex;
	}
	public void setRowindex(String rowindex) {
		this.rowindex = rowindex;
	}
	public String getColumnindex() {
		return columnindex;
	}
	public void setColumnindex(String columnindex) {
		this.columnindex = columnindex;
	}
	public short getSidedcategory() {
		return sidedcategory;
	}
	public void setSidedcategory(short sidedcategory) {
		this.sidedcategory = sidedcategory;
	}
	public short getRacktype() {
		return racktype;
	}
	public void setRacktype(short racktype) {
		this.racktype = racktype;
	}
	public short getOrderofchassis() {
		return orderofchassis;
	}
	public void setOrderofchassis(short orderofchassis) {
		this.orderofchassis = orderofchassis;
	}
	public short getRackinstallationside() {
		return rackinstallationside;
	}
	public void setRackinstallationside(short rackinstallationside) {
		this.rackinstallationside = rackinstallationside;
	}
	public Integer getShelfnumber() {
		return shelfnumber;
	}
	public void setShelfnumber(Integer shelfnumber) {
		this.shelfnumber = shelfnumber;
	}

    public String getRackTypeName() {
        return rackTypeName;
    }

    public void setRackTypeName(String rackTypeName) {
        this.rackTypeName = rackTypeName;
    }

	public RackType getRackTypeEntity() {
		return rackTypeEntity;
	}

	public void setRackTypeEntity(RackType rackTypeEntity) {
		this.rackTypeEntity = rackTypeEntity;
	}

	public String getMgElementDn() {
		return mgElementDn;
	}

	public void setMgElementDn(String mgElementDn) {
		this.mgElementDn = mgElementDn;
	}

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

	public short getRackCategory() {
		return rackCategory;
	}

	public void setRackCategory(short rackCategory) {
		this.rackCategory = rackCategory;
	}

	public double getGis_x() {
		return gis_x;
	}

	public void setGis_x(double gis_x) {
		this.gis_x = gis_x;
	}

	public double getGis_y() {
		return gis_y;
	}

	public void setGis_y(double gis_y) {
		this.gis_y = gis_y;
	}

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
