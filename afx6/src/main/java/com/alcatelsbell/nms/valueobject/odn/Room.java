package com.alcatelsbell.nms.valueobject.odn;

import javax.persistence.*;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.ExtBObject;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

@Entity
@Table(name = "R_ROOM")
public class Room extends ExtBObject {
	/*拼装名字*/
	public String name;
	
	@BField(description = "机房名称",searchType = BField.SearchType.NULLABLE)
	public String roomname;

	@BField(description = "所属区域",editType = BField.EditType.REQUIRED,searchType = BField.SearchType.NULLABLE,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.RRegion",
            dnReferenceTransietField = "name",
            dnReferenceEntityField = "regionname")
	public String region_dn;
	
//	@DicGroupMapping(groupName = "ROOMCATEGORY", definitionClass = OdnDictionary.class)
//	@BField(description = "机房类型",searchType = BField.SearchType.NULLABLE)
	public int roomcategory;
	
	@BField(description = "责任人")
	public String responsibleperson;
	
	@BField(description = "联系电话")
	public String phone;
	
	@DicGroupMapping(groupName = "ROOMTYPE", definitionClass = OdnDictionary.class)
	@BField(description = "机房性质",searchType = BField.SearchType.NULLABLE)
	public int roomType;
	
	@BField(description = "父资源点",editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Room",
            dnReferenceTransietField = "parentRoom_dn",
            dnReferenceEntityField = "roomname")
	public String parentRoom_dn;

    @BField(description = "GPS经度")
    private Double gpsX;

    @BField(description = "GPS纬度")
    private Double gpsY;

    public Double getGpsX() {
        return gpsX;
    }

    public void setGpsX(Double gpsX) {
        this.gpsX = gpsX;
    }

    public Double getGpsY() {
        return gpsY;
    }

    public void setGpsY(Double gpsY) {
        this.gpsY = gpsY;
    }

    /*编码*/
	public String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion_dn() {
		return region_dn;
	}

	public void setRegion_dn(String region_dn) {
		this.region_dn = region_dn;
	}

	public int getRoomcategory() {
		return roomcategory;
	}

	public void setRoomcategory(int roomcategory) {
		this.roomcategory = roomcategory;
	}

	public String getResponsibleperson() {
		return responsibleperson;
	}

	public void setResponsibleperson(String responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	public String getParentRoom_dn() {
		return parentRoom_dn;
	}

	public void setParentRoom_dn(String parentRoom_dn) {
		this.parentRoom_dn = parentRoom_dn;
	}
	
}
