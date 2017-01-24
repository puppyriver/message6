/**
 * 
 */
package com.alcatelsbell.nms.valueobject.odn;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

/**
 * @author: Aaron
 * Date: 2012-9-11
 * Time: 下午02:11:55
 */
@Entity
@Table(name = "O_OPTICALCABLE")
public class OpticalCable extends AbstractOdn {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@BField(description = "光缆名称",searchType = BField.SearchType.NULLABLE)
	private String opticalCableName;

	@BField(description = "所属区域",editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.RRegion",
            searchType = BField.SearchType.NULLABLE,
            dnReferenceTransietField = "regionname",
            dnReferenceEntityField = "name")
	private String region_dn;
	
	@BField(description = "光缆级别",searchType = BField.SearchType.NULLABLE)
	@DicGroupMapping(groupName = "OPTICALCABLELEVEL", definitionClass = OdnDictionary.class)
	private short cableLevel;
	
	@BField(description ="光缆类型",searchType = BField.SearchType.NULLABLE)
	@DicGroupMapping(groupName= "OPTICALCABLETYPE",definitionClass = OdnDictionary.class)
	private short cableType;
	
	@BField(description ="光缆长度(m)")
	private int length;
	
	@BField(description ="竣工日期")
	@Temporal (TemporalType.TIMESTAMP)
	private Date finishDate;
	
	@Transient
	private String regionname;

	public String getRegion_dn() {
		return region_dn;
	}

	public void setRegion_dn(String region_dn) {
		this.region_dn = region_dn;
	}

	public short getCableLevel() {
		return cableLevel;
	}

	public void setCableLevel(short cableLevel) {
		this.cableLevel = cableLevel;
	}

	public short getCableType() {
		return cableType;
	}

	public void setCableType(short cableType) {
		this.cableType = cableType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getOpticalCableName() {
		return opticalCableName;
	}

	public void setOpticalCableName(String opticalCableName) {
		this.opticalCableName = opticalCableName;
	}
	
	
}
