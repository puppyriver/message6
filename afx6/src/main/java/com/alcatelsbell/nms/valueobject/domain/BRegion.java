package com.alcatelsbell.nms.valueobject.domain;

import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.valueobject.BObject;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * User: Ronnie.Chen
 * Date: 11-5-16
 * Time: 5:06
 */
@Entity
@Table(name = "B_REGION")
public class BRegion extends BObject
    implements Serializable{
  private String namecn;
  private String namepy;
  private String abbrevcn;
  private String address;
  private String officername;
  private String officerphone;
  private Integer xposition;
  private Integer yposition;
  private Double offsetx;
  private Double offsety;
  private Double scale;
  private Long version;
  private Integer labelposition;
  private String background;
  private Integer type;
  private String code;
  private Integer serviceorder;
  private String comments;
  private String ticketprefix;
  private Long administrativeareaid;
  private String alias;
  private String regionno;

  private Long parentregionid;

  public BRegion() {
    super();

    xposition = SysConst.NULL_INT_FIELD;
    yposition = SysConst.NULL_INT_FIELD;
    labelposition = SysConst.NULL_INT_FIELD;
    type = SysConst.NULL_INT_FIELD;
    serviceorder = SysConst.NULL_INT_FIELD;
    alias = "";
    regionno = "";
    namecn = "";
    namepy = "";
    abbrevcn = "";
    address = "";
    officername = "";
    officerphone = "";
    background = "";
    code = "";
    comments = "";
    ticketprefix = "";

    oid = SysConst.NULL_NUMERICAL_FIELD;
    parentregionid = (long)SysConst.NULL_NUMERICAL_FIELD;
  }



  public String getAbbrevcn() {
    return this.abbrevcn;
  }

  public String getAddress() {
    return this.address;
  }

  public Long getAdministrativeareaid() {
    return this.administrativeareaid;
  }

  public String getAlias() {
    return this.alias;
  }

  public String getBackground() {
    return this.background;
  }

  public String getCode() {
    return this.code;
  }

  public String getComments() {
    return this.comments;
  }



  public Integer getLabelposition() {
    return this.labelposition;
  }

  public String getNamecn() {
    return this.namecn;
  }

  public String getNamepy() {
    return this.namepy;
  }

  public String getOfficername() {
    return this.officername;
  }

  public String getOfficerphone() {
    return this.officerphone;
  }

  public Double getOffsetx() {
    return this.offsetx;
  }

  public Double getOffsety() {
    return this.offsety;
  }

public Long getParentregionid() {
	return  this.parentregionid;
}

//public Collection getRegionCollection() {
//	return this.regionCollection;
//}

  public String getRegionno() {
    return this.regionno;
  }

  public Double getScale() {
    return this.scale;
  }

  public Integer getServiceorder() {
    return this.serviceorder;
  }

  public String getTicketprefix() {
    return this.ticketprefix;
  }

  public Integer getType() {
    return this.type;
  }

  public Long getVersion() {
    return this.version;
  }

  public Integer getXposition() {
    return this.xposition;
  }

  public Integer getYposition() {
    return this.yposition;
  }


  public void setAbbrevcn(String abbrevcn) {
    this.abbrevcn = abbrevcn;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setAdministrativeareaid(Long administrativeareaid) {
    this.administrativeareaid = administrativeareaid;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public void setBackground(String background) {
    this.background = background;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }



  public void setLabelposition(Integer labelposition) {
    this.labelposition = labelposition;
  }

  public void setNamecn(String namecn) {
    this.namecn = namecn;
  }

  public void setNamepy(String namepy) {
    this.namepy = namepy;
  }

  public void setOfficername(String officername) {
    this.officername = officername;
  }

  public void setOfficerphone(String officerphone) {
    this.officerphone = officerphone;
  }

  public void setOffsetx(Double offsetx) {
    this.offsetx = offsetx;
  }

  public void setOffsety(Double offsety) {
    this.offsety = offsety;
  }

public void setParentregionid(Long  _parentregionid) {
	this.parentregionid = _parentregionid;
}

  public void setRegionno(String regionno) {
    this.regionno = regionno;
  }

  public void setScale(Double scale) {
    this.scale = scale;
  }

  public void setServiceorder(Integer serviceorder) {
    this.serviceorder = serviceorder;
  }

  public void setTicketprefix(String ticketprefix) {
    this.ticketprefix = ticketprefix;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public void setXposition(Integer xposition) {
    this.xposition = xposition;
  }

  public void setYposition(Integer yposition) {
    this.yposition = yposition;
  }

  protected byte persistType;
  public void setPersistType(byte _persistType) {
    this.persistType = _persistType;
  }

  public byte getPersistType() {
    return persistType;
  }

  public void copyShallow(BObject bObject) {
    BRegion b = (BRegion) bObject;

    this.type = b.getType();
    this.administrativeareaid = b.getAdministrativeareaid();
    this.xposition = b.getXposition();
    this.yposition = b.getYposition();
    this.labelposition = b.getLabelposition();
    this.serviceorder = b.getServiceorder();
    this.alias = b.getAlias();
    this.regionno = b.getRegionno();
    this.namecn = b.getNamecn();
    this.namepy = b.getNamepy();
    this.abbrevcn = b.getAbbrevcn();
    this.address = b.getAddress();
    this.officername = b.getOfficername();
    this.officerphone = b.getOfficerphone();
    this.background = b.getBackground();
    this.code = b.getCode();
    this.comments = b.getComments();
    this.ticketprefix = b.getTicketprefix();
    this.offsetx = b.getOffsetx();
    this.offsety = b.getOffsety();
    this.scale = b.getScale();
    this.parentregionid = b.getParentregionid();
    this.version = b.getVersion();
  }

  public String toString() {
    return namecn;
  }

}
