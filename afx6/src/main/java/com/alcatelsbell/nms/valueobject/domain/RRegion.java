package com.alcatelsbell.nms.valueobject.domain;

/**
 * User: Ronnie.Chen
 * Date: 11-5-16
 * Time: ����10:09
 */


import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.ExtBObject;
import com.alcatelsbell.nms.valueobject.sys.SysDictionarys;

import java.util.List;
import java.util.Vector;
import javax.persistence.*;


/**
 *
 * @author g
 */
@Entity
@Table(name = "R_REGION")
public class RRegion extends ExtBObject {
	/*拼装名字*/
	private String name;
	
	@BField(description = "区域名称",searchType = BField.SearchType.NULLABLE)
    private String regionname;
	
	@BField(description = "区域编号")
	private Integer no;
	
	@BField(description = "上级区域",editType = BField.EditType.REQUIRED,
	    dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.RRegion",
	    dnReferenceEntityField = "regionname",
	    dnReferenceTransietField = "parentRegionName")
	protected String parentRegionDn;
	
	@DicGroupMapping(groupName = "REGIONTYPE", definitionClass = SysDictionarys.class)
	@BField(description = "区域类型",searchType = BField.SearchType.NULLABLE)
    private Integer type;
	
	@DicGroupMapping(groupName = "ROOTFLAG", definitionClass = SysDictionarys.class)
	@BField(description = "设置根区域")
    private Integer rootFlag;

	@BField(description = "区域简称")
	private String abbrevcn;
	
    private String namepy;
   
//    @BField(description = "地址",searchType = BField.SearchType.NULLABLE)
    private String address;

//    @BField(description = "办公地点")
      private String officername;

//    @BField(description = "办公电话")
      private String officerphone;

//    @BField(description = "x坐标")
    private Integer xposition;

//    @BField(description = "y坐标")
    private Integer yposition;

//    @BField(description = "x偏移量")
    private Double offsetx=0.0;

//    @BField(description = "y偏移量")
    private Double offsety=0.0;

//    @BField(description = "规模")
    private Double scale=0.0;

//    @BField(description = "标志位置")
    private Integer labelposition;

    private String background;
    
    /*编码*/
    private String code;

//    @BField(description = "服务等级")
    private Integer serviceorder;

    private String comments;

    private String ticketprefix;

//    @BField(description = "管理地区标识")
    private Long administrativeareaid;

    private String alias;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn (name = "parentRegionDn")
    @Transient
    protected RRegion parentRegion = null;

 //   @OneToMany (mappedBy = "parentRegion")
    @Transient
    protected List<RRegion> regions = null;

    @Transient
    protected String parentRegionName=null;

    public RRegion() {
        this.name = "";
        this.namepy = "";
        this.abbrevcn = "";
        this.officername = "";
        this.officerphone = "";
    }

    public RRegion getParentRegion() {
        return parentRegion;
    }

    public void setParentRegion(RRegion parentRegion) {
        this.parentRegion = parentRegion;
    }

    public String getParentRegionDn() {
        return parentRegionDn;
    }

    public void setParentRegionDn(String parentRegionDn) {
        this.parentRegionDn = parentRegionDn;
    }

    public List<RRegion> getRegions() {
        return regions;
    }

    public void setRegions(List<RRegion> _regions) {
        regions = _regions;
        if (regions != null) {
            for (RRegion region : regions) {
                region.setParentRegion(this);
            }
        }
    }

    public void addRegion(RRegion _region) {
        if (regions == null) {
            regions = new Vector();
        }
        _region.setParentRegion(this);
        regions.add(_region);
    }

    public String getAbbrevcn() {
        return abbrevcn;
    }

    public void setAbbrevcn(String abbrevcn) {
        this.abbrevcn = abbrevcn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamepy() {
        return namepy;
    }

    public void setNamepy(String namepy) {
        this.namepy = namepy;
    }

    public String getOfficername() {
        return officername;
    }

    public void setOfficername(String officername) {
        this.officername = officername;
    }

    public String getOfficerphone() {
        return officerphone;
    }

    public void setOfficerphone(String officerphone) {
        this.officerphone = officerphone;
    }

    public Long getAdministrativeareaid() {
        return administrativeareaid;
    }

    public void setAdministrativeareaid(Long administrativeareaid) {
        this.administrativeareaid = administrativeareaid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getLabelposition() {
        return labelposition;
    }

    public void setLabelposition(Integer labelposition) {
        this.labelposition = labelposition;
    }

    public Double getOffsetx() {
        return offsetx;
    }

    public void setOffsetx(Double offsetx) {
        this.offsetx = offsetx;
    }

    public Double getOffsety() {
        return offsety;
    }

    public void setOffsety(Double offsety) {
        this.offsety = offsety;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Integer getServiceorder() {
        return serviceorder;
    }

    public void setServiceorder(Integer serviceorder) {
        this.serviceorder = serviceorder;
    }

    public String getTicketprefix() {
        return ticketprefix;
    }

    public void setTicketprefix(String ticketprefix) {
        this.ticketprefix = ticketprefix;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }



    public Integer getXposition() {
        return xposition;
    }

    public void setXposition(Integer xposition) {
        this.xposition = xposition;
    }

    public Integer getYposition() {
        return yposition;
    }

    public void setYposition(Integer yposition) {
        this.yposition = yposition;
    }

    @Override
    public String toString() {
        return this.name;
    }

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getParentRegionName() {
		return parentRegionName;
	}

	public void setParentRegionName(String parentRegionName) {
		this.parentRegionName = parentRegionName;
	}

	public Integer getRootFlag() {
		return rootFlag;
	}

	public void setRootFlag(Integer rootFlag) {
		this.rootFlag = rootFlag;
	}
    
}
