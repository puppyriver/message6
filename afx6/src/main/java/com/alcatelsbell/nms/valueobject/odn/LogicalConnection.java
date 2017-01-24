package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Author: Ronnie.Chen
 * Date: 12-9-28
 * Time: 下午1:13
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Entity
public class LogicalConnection extends AbstractOdn{
	
	@BField(description = "A端端口",editType = BField.EditType.REQUIRED,
		    dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Port",
		    dnReferenceEntityField = "name",
		    dnReferenceTransietField = "aportName")
    private String aportDn;
	
	@BField(description = "Z端端口",editType = BField.EditType.REQUIRED,
		    dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Port",
		    dnReferenceEntityField = "name",
		    dnReferenceTransietField = "zportName")
    private String zportDn;
	
	@BField(description = "光路信息")
    private String textRoute;
    
	@BField(description = "业务状态")
    private Integer businessStatus;

    @BField(description = "创建信息")
    private String createInfo;

    @Transient
    private String aportName;
    
    @Transient
    private String zportName;
    

    public String getAportName() {
		return aportName;
	}

	public void setAportName(String aportName) {
		this.aportName = aportName;
	}

	public String getZportName() {
		return zportName;
	}

	public void setZportName(String zportName) {
		this.zportName = zportName;
	}

	public String getAportDn() {
		return aportDn;
	}

	public void setAportDn(String aportDn) {
		this.aportDn = aportDn;
	}

	public String getZportDn() {
		return zportDn;
	}

	public void setZportDn(String zportDn) {
		this.zportDn = zportDn;
	}

	public String getCreateInfo() {
        return createInfo;
    }

    public void setCreateInfo(String createInfo) {
        this.createInfo = createInfo;
    }

    public String getTextRoute() {
        return textRoute;
    }

    public void setTextRoute(String textRoute) {
        this.textRoute = textRoute;
    }

    public Integer getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Integer businessStatus) {
        this.businessStatus = businessStatus;
    }
}
