package com.alcatelsbell.nms.valueobject.physical.config;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * User: Ronnie
 * Date: 12-5-22
 * Time: 上午10:27
 */
@MappedSuperclass
public class EntityType extends BObject {

    @BField(description = "编码",editType = BField.EditType.REQUIRED)
    private String code;

    @BField(description = "备注")
    private String memo;

    private String creator;

    private String updator;

    @BField(description = "名称",editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.REQUIRED)
    private String name;

    @BField(description = "厂商名称",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.sys.Vendor",
            dnReferenceTransietField = "vendorName",
            dnReferenceEntityField = "name")
    private String vendorDn;

    @Transient
    private String vendorName;

    private String background;

    @BField(description = "长度")
    private Integer length;

    @BField(description = "宽度")
    private Integer width;

    @BField(description = "深度")
    private Integer depth;

    @DicGroupMapping(groupName = "ENTITYTYPE_DEFAULT_TYPE",definitionClass = OdnDictionary.class)
    @BField(description = "默认类型", viewType = BField.ViewType.SHOW,sequence = 1)
    private Integer defaultType;


    public Integer getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(Integer defaultType) {
        this.defaultType = defaultType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendorDn() {
        return vendorDn;
    }

    public void setVendorDn(String vendorDn) {
        this.vendorDn = vendorDn;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
