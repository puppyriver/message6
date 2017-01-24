package com.alcatelsbell.nms.valueobject.sys;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

/**
 * User: wcb
 * Date: 2013-10-31
 * Time: 下午14:37
 */

@Entity
@Table(name = "S_VENDORTYPE")
public class VendorType extends BObject{
	
	@BField(description = "型号名称",searchType = BField.SearchType.NULLABLE)
	private String name;
	
	@BField(description = "类别",searchType = BField.SearchType.NULLABLE)
	private String vendorType;
	
	@BField(description = "产商名称", createType = BField.CreateType.REQUIRED, editType = BField.EditType.REQUIRED, mergeType = BField.MergeType.RESERVED, dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.sys.Vendor", dnReferenceTransietField = "vendorTypeName", dnReferenceEntityField = "name")
	private String vendorDN;
	
	@Transient
	private String vendorTypeName;

	public String getVendorTypeName() {
		return vendorTypeName;
	}

	public void setVendorTypeName(String vendorTypeName) {
		this.vendorTypeName = vendorTypeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getVendorDN() {
		return vendorDN;
	}

	public void setVendorDN(String vendorDN) {
		this.vendorDN = vendorDN;
	}

	

}
