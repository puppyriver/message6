package com.alcatelsbell.nms.valueobject.physical.config;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie
 * Date: 12-5-22
 * Time: 上午10:12
 */
@Entity
@Table(name = "T_CARDTYPE")
public class CardType extends EntityType {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4662015297449444560L;

	@BField(description = "端口数",editType = BField.EditType.REQUIRED)
    private Integer portNumber;

    private Integer portType;
    
    @BField(description="是否分光器",editType = BField.EditType.REQUIRED)
    private int SpliceTray=OdnDictionary.CARDTYPE.BUSINESS_CARD.value;
    //存一个字符串 ，例如 "1:(10,100);2:(20,100);3:(20,100)"
    @Column(length = 2048)
    private String portCoordinate;
    
    //端口位规格,存一个字符串 ，例如 "(10,100)" (如果所有端口位规格都一样的话)
    @Column(length = 2048)
    private String portSpec;

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public Integer getPortType() {
        return portType;
    }

    public void setPortType(Integer portType) {
        this.portType = portType;
    }

    public String getPortSpec() {
        return portSpec;
    }

    public void setPortSpec(String portSpec) {
        this.portSpec = portSpec;
    }

	public String getPortCoordinate() {
		return portCoordinate;
	}

	public void setPortCoordinate(String portCoordinate) {
		this.portCoordinate = portCoordinate;
	}

	public int getSpliceTray() {
		return SpliceTray;
	}

	public void setSpliceTray(int spliceTray) {
		SpliceTray = spliceTray;
	}
}
