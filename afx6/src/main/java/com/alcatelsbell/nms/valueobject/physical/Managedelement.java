package com.alcatelsbell.nms.valueobject.physical;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * User: Ronnie.Chen
 * Date: 11-5-24
 * Time:
 */

@Entity
@Table(name = "P_MANAGEDELEMENT")
public class Managedelement extends BObject {
    private static final long serialVersionUID = -1;
	@BField(description = "设备名称",searchType = BField.SearchType.NULLABLE)
    private String name;
    private String nativeemsname;    // 主要用于告警定位 ，告警中经常会带上EMS中的网元信息
    private String emsdn;
    private String userlabel;
    private String neName;
    
    private String type;
    private String equipmenttypedn;

    private String ipaddress;
    private String parentmedn;
    private String macaddress;
    private String parentportdn;
    private String sequence;

    private String softwareVersion;
    private String info1;
    private String info2;
    private String info3;
    private Integer osType;
    private String roomDn;
    private Integer status;
    private Integer devicetype;
    private String regiondn;
    private long metypeid;
    private double gisx;
    private double gisy;

    private Double cpu;
    private Double memory;
    private Double storage;
    private Double value1;
    private Double value2;
    private Double value3;

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public Double getStorage() {
        return storage;
    }

    public void setStorage(Double storage) {
        this.storage = storage;
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    public Double getValue3() {
        return value3;
    }

    public void setValue3(Double value3) {
        this.value3 = value3;
    }

    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public String getRoomDn() {
        return roomDn;
    }

    public void setRoomDn(String roomDn) {
        this.roomDn = roomDn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
	public Integer getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(Integer devicetype) {
		this.devicetype = devicetype;
	}

	public String getNeName() {
		return neName;
	}

	public void setNeName(String neName) {
		this.neName = neName;
	}

	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeemsname() {
        return nativeemsname;
    }

    public void setNativeemsname(String nativeemsname) {
        this.nativeemsname = nativeemsname;
    }

    public String getEmsdn() {
        return emsdn;
    }

    public void setEmsdn(String emsdn) {
        this.emsdn = emsdn;
    }

    public String getUserlabel() {
        return userlabel;
    }

    public void setUserlabel(String userlabel) {
        this.userlabel = userlabel;
    }

    public String getRegiondn() {
        return regiondn;
    }

    public void setRegiondn(String regiondn) {
        this.regiondn = regiondn;
    }

    public long getMetypeid() {
        return metypeid;
    }

    public void setMetypeid(long metypeid) {
        this.metypeid = metypeid;
    }

    public double getGisx() {
        return gisx;
    }

    public void setGisx(double gisx) {
        this.gisx = gisx;
    }

    public double getGisy() {
        return gisy;
    }

    public void setGisy(double gisy) {
        this.gisy = gisy;
    }

    public String getEquipmenttypedn() {
        return equipmenttypedn;
    }

    public void setEquipmenttypedn(String equipmenttypedn) {
        this.equipmenttypedn = equipmenttypedn;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getParentmedn() {
        return parentmedn;
    }

    public void setParentmedn(String parentmedn) {
        this.parentmedn = parentmedn;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public String getParentportdn() {
        return parentportdn;
    }

    public void setParentportdn(String parentportdn) {
        this.parentportdn = parentportdn;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }
}
