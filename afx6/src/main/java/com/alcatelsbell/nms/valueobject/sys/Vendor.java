package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Vector;

/**
 * User: Ronnie
 * Date: 11-11-15
 * Time: 上午11:39
 * @see gxlu.vpn.common.vauleobject.physical.Vendor
 */
@Entity
@Table(name = "S_VENDOR")
public class Vendor extends BObject{

    @BField(description = "名称",searchType = BField.SearchType.NULLABLE)
    private String name;
    private String abbrev;
    
    @BField(description = "备注",searchType = BField.SearchType.NULLABLE)
    private String comments;

    @Transient
    private Vector meTypes;


    public Vendor() {
      super();

    }

    public String getAbbrev() {
      return this.abbrev;
    }

    public String getComments() {
      return this.comments;
    }



    public String getName() {
      return this.name;
    }



    public void setAbbrev(String abbrev) {
      this.abbrev = abbrev;
    }

    public void setComments(String comments) {
      this.comments = comments;
    }

    public void setId(long id) {
      this.id = id;
    }

    public void setName(String name) {
      this.name = name;
    }


    @Transient
    protected byte persistType;
    public void setPersistType(byte _persistType) {
      this.persistType = _persistType;
    }

    public byte getPersistType() {
      return persistType;
    }

    public int oid;
    public int getOid() {
      return oid;
    }

    public void setOid(int _oid) {
      this.oid = _oid;
    }

    public Vector getMeTypes()
    {
      return meTypes;
    }

    public void setMeTypes( Vector _meTypes )
    {
      meTypes = _meTypes;
    }



}
