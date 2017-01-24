package com.alcatelsbell.nms.valueobject.physical.config;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alcatelsbell.nms.common.crud.annotation.BField;


/**
 * User: Zhe Chen
 * Date: 12-5-22
 * Time: 上午10:12
 */
@Entity
@Table(name = "T_TYPECONFIG")
public class TypeConfig extends EntityType{
	@BField(description = "模板名称",searchType = BField.SearchType.NULLABLE)
	private String typeConfigName;
	@BField(description = "所属配置",searchType = BField.SearchType.NULLABLE,
			dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.meta.MetaConfig",
            dnReferenceTransietField = "metaConfigName",
            dnReferenceEntityField = "name")
	private String metaConfig_dn;
	@BField(description = "机架类型",searchType = BField.SearchType.NULLABLE,
			dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.physical.config.RackType",
            dnReferenceTransietField = "rackTypeName",
            dnReferenceEntityField = "name")
	private String rackType_dn;
	@BField(description = "机框类型",searchType = BField.SearchType.NULLABLE,
			dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.physical.config.ShelfType",
            dnReferenceTransietField = "shelfTypeName",
            dnReferenceEntityField = "name")
	private String shelfType_dn;
	@BField(description = "板卡类型",searchType = BField.SearchType.NULLABLE,
			dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.physical.config.CardType",
            dnReferenceTransietField = "cardTypeName",
            dnReferenceEntityField = "name")
	private String cardType_dn;
	
	@Transient
	private String metaConfigName;
	
	@Transient
	private String rackTypeName;
	
	@Transient
	private String shelfTypeName;
	
	@Transient
	private String cardTypeName;
	
	
	public String getTypeConfigName() {
		return typeConfigName;
	}

	public void setTypeConfigName(String typeConfigName) {
		this.typeConfigName = typeConfigName;
	}

	public String getMetaConfigName() {
		return metaConfigName;
	}

	public void setMetaConfigName(String metaConfigName) {
		this.metaConfigName = metaConfigName;
	}

	public String getRackTypeName() {
		return rackTypeName;
	}

	public void setRackTypeName(String rackTypeName) {
		this.rackTypeName = rackTypeName;
	}

	public String getShelfTypeName() {
		return shelfTypeName;
	}

	public void setShelfTypeName(String shelfTypeName) {
		this.shelfTypeName = shelfTypeName;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public String getMetaConfig_dn() {
		return metaConfig_dn;
	}

	public void setMetaConfig_dn(String metaConfigDn) {
		metaConfig_dn = metaConfigDn;
	}

	public String getRackType_dn() {
		return rackType_dn;
	}

	public void setRackType_dn(String rackTypeDn) {
		rackType_dn = rackTypeDn;
	}

	public String getShelfType_dn() {
		return shelfType_dn;
	}

	public void setShelfType_dn(String shelfTypeDn) {
		shelfType_dn = shelfTypeDn;
	}

	public String getCardType_dn() {
		return cardType_dn;
	}

	public void setCardType_dn(String cardTypeDn) {
		cardType_dn = cardTypeDn;
	}

}
