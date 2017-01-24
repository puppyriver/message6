package com.alcatelsbell.nms.valueobject.alarm;

import com.alcatelsbell.nms.common.CommonDictionary;
import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: gxlu</p>
 *
 * @author not attributable
 * @version 1.0
 */
@Table(name = "F_VENDORALARMLIB")
@Entity
public class VendorAlarmLib extends BObject
{

    public Integer oid;

    @BField(description = "厂商",editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.sys.Vendor",
            dnReferenceTransietField = "vendorName",
            dnReferenceEntityField = "name")
    private String vendorDn;

    @Transient
    private String vendorName;

    @BField(description = "映射方式")
    private String mappingType;
    
    @BField(description="原始厂商")
    private String originalVendor;
    
    @BField(description = "专业")
    private String domain;
    
    @BField(description = "设备类型", searchType = BField.SearchType.NULLABLE)
    private String deviceCategory;
    
    @BField(description = "层速率")
    private String layerRate;
    
    @BField(description = "厂家告警ID", searchType = BField.SearchType.NULLABLE)
    private String alarmCode;
    
    @BField(description = "告警标题",searchType = BField.SearchType.NULLABLE)
    private String alarmName;
    
    @BField(description = "告警解释辅助字段")
    private String additionalComment;
    
    @BField(description = "厂家告警级别", searchType = BField.SearchType.NULLABLE)
    private String originalSeverity;
    
    @BField(description = "适用的厂家版本号")
    private String emsVersion;
    
    @DicGroupMapping(groupName = "ALARM_SEVERITY_ALIAS", definitionClass = OdnDictionary.class)
    @BField(description = "网管告警级别", searchType = BField.SearchType.NULLABLE)
    private Integer severity;
    
    @BField(description = "网管告警ID")
    private String alarmId;
    
    @BField(description = "告警解释")
    private String alarmDescription;
    
    @BField(description = "告警类别", searchType = BField.SearchType.NULLABLE)
    private String alarmType;

    @BField(description = "告警逻辑分类")
    private String alarmCategory;
    
    @BField(description = "告警逻辑子类")
    private String alarmSubCategory;
    
    @BField(description = "该事件对设备的影响")
    private String deviceImpact;
    
    @BField(description = "该事件对业务的影响", searchType = BField.SearchType.NULLABLE)
    private String businessImpact;
    
    @BField(description = "关联标识")
    private String relationFlag;
    
    @BField(description = "业务类型")
    private String businessType;
    
    @BField(description = "告警标准名")
    private String standardName;

    private String sourceType;
    
//    @BField(description = "故障原因",searchType = BField.SearchType.NULLABLE)
    private String problemCause;

    private String alarmNameCN;
    private Integer isAffectedOperation;

    @DicGroupMapping(groupName = "ALARM_ISSHIELD", definitionClass = CommonDictionary.class)
    @BField(description = "是否屏蔽",values = {"否","是"})
    private Integer isShield;
    private Integer sourceTypeId;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public Integer getSourceTypeId() {
        return sourceTypeId;
    }

    public void setSourceTypeId(Integer sourceTypeId) {
        this.sourceTypeId = sourceTypeId;
    }

    public Integer getIsShield() {
        return isShield;
    }

    public void setIsShield(Integer isShield) {
        this.isShield = isShield;
    }

    public Integer getIsAffectedOperation() {
        return isAffectedOperation;
    }


    public void setIsAffectedOperation(Integer isAffectedOperation) {
        this.isAffectedOperation = isAffectedOperation;
    }


	public String getProblemCause()
    {
        return problemCause;
    }

    public void setProblemCause( String _problemCause )
    {
        this.problemCause = _problemCause;
    }

    public String getAlarmNameCN()
    {
        return alarmNameCN;
    }

    public void setAlarmNameCN( String _alarmNameCN )
    {
        this.alarmNameCN = _alarmNameCN;
    }

    public void setAlarmType( String _alarmType )
    {
        this.alarmType = _alarmType;
    }

    public String getAlarmType()
    {
        return alarmType;
    }

    public String getSourceType()
    {
        return sourceType;
    }

    public void setSourceType( String _sourceType )
    {
        this.sourceType = _sourceType;
    }

    public void setAlarmDescription( String _alarmDescription )
    {
        this.alarmDescription = _alarmDescription;
    }

    public String getAlarmDescription()
    {
        return alarmDescription;
    }

    public String getAlarmName()
    {
        return alarmName;
    }

    public void setAlarmName( String _alarmName )
    {
        this.alarmName = _alarmName;
    }

    public String getVendorDn()
    {
        return vendorDn;
    }

    public void setVendorDn( String _vendorDn )
    {
        this.vendorDn = _vendorDn;
    }

    /**
     * set the oid attribute value of this object.
     *
     * @param oid Integer
     * @todo Implement this gxlu.afx.system.common.Integererfaces.BObjectInterface method
     */
    public void setOid( Integer _oid )
    {
        oid = _oid;
    }

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	public String getLayerRate() {
		return layerRate;
	}

	public void setLayerRate(String layerRate) {
		this.layerRate = layerRate;
	}

	public String getAlarmCode() {
		return alarmCode;
	}

	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}

	public String getAdditionalComment() {
		return additionalComment;
	}

	public void setAdditionalComment(String additionalComment) {
		this.additionalComment = additionalComment;
	}

	public String getOriginalSeverity() {
		return originalSeverity;
	}

	public void setOriginalSeverity(String originalSeverity) {
		this.originalSeverity = originalSeverity;
	}

	public String getEmsVersion() {
		return emsVersion;
	}

	public void setEmsVersion(String emsVersion) {
		this.emsVersion = emsVersion;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getAlarmCategory() {
		return alarmCategory;
	}

	public void setAlarmCategory(String alarmCategory) {
		this.alarmCategory = alarmCategory;
	}

	public String getAlarmSubCategory() {
		return alarmSubCategory;
	}

	public void setAlarmSubCategory(String alarmSubCategory) {
		this.alarmSubCategory = alarmSubCategory;
	}

	public String getDeviceImpact() {
		return deviceImpact;
	}

	public void setDeviceImpact(String deviceImpact) {
		this.deviceImpact = deviceImpact;
	}

	public String getBusinessImpact() {
		return businessImpact;
	}

	public void setBusinessImpact(String businessImpact) {
		this.businessImpact = businessImpact;
	}

	public String getRelationFlag() {
		return relationFlag;
	}

	public void setRelationFlag(String relationFlag) {
		this.relationFlag = relationFlag;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getOriginalVendor() {
		return originalVendor;
	}

	public void setOriginalVendor(String originalVendor) {
		this.originalVendor = originalVendor;
	}
	
	
    
}
