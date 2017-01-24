package com.alcatelsbell.nms.valueobject.physical.config;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

@Entity
@Table(name="T_CARDCONFIG")
@NamedQuery(
		name="CardConfig.getCardTypeEntity",
		query="SELECT rt FROM CardType rt WHERE rt.dn=:cardTypeDn"	
)
public class CardConfig extends BObject{
	@BField(description = "名称",searchType = BField.SearchType.REQUIRED)
    private String name;
	
	 @BField(description = "板卡类型",
			 	createType=BField.CreateType.REQUIRED,
	    		editType = BField.EditType.REQUIRED,
	            searchType = BField.SearchType.REQUIRED,
	            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.physical.config.CardType",
	            dnReferenceTransietField = "cardTypeName",
	            dnReferenceEntityField = "name")
    private String cardTypeDn;

	@Transient
	private String cardTypeName;
	 
	@Transient
	private CardType cardTypeEntity;
	 
	@Transient
	private Double selfX;
	    
	@Transient
	private Double selfY;
	    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardTypeDn() {
		return cardTypeDn;
	}

	public void setCardTypeDn(String cardTypeDn) {
		this.cardTypeDn = cardTypeDn;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public CardType getCardTypeEntity() {
		return cardTypeEntity;
	}

	public void setCardTypeEntity(CardType cardTypeEntity) {
		this.cardTypeEntity = cardTypeEntity;
	}

	public Double getSelfX() {
		return selfX;
	}

	public void setSelfX(Double selfX) {
		this.selfX = selfX;
	}

	public Double getSelfY() {
		return selfY;
	}

	public void setSelfY(Double selfY) {
		this.selfY = selfY;
	}
}
