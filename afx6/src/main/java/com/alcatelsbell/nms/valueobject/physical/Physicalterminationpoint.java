package com.alcatelsbell.nms.valueobject.physical;

/**
 * User: Ronnie.Chen
 * Date: 11-5-25
 * Time:
 */


import com.alcatelsbell.nms.valueobject.BObject;
import org.hibernate.annotations.Index;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "P_Physicalterminationpoint")
@Entity
public class Physicalterminationpoint
        extends BObject {

    @Index(name = "PTP_INDEX_NAME")
    private String name;

    private String userlabel;
    private String nativeemsname;
    private int direction;
    private String additionalinfo;
    private String medn;


    @Index(name = "PTP_INDEX_CARDDN")
    private String carddn;

    //  private int rate;
    private int no;
    private int ptpCapacity;
    private int ptpRate;

    private String comments;
    private String emsdn;
    private int ptptype;
    private String type;

    private String cardname;
    private String slotNo;
    private String portNo;
    private Integer adminStatus;
    private Integer operStatus;
    private String mtu;

    @Column(name="ifusage")
    private Double usage;
    private Double inflow;
    private Double outflow;
    private Double value1;
    private Double value2;
    private Double value3;

    public Double getUsage() {
        return usage;
    }

    public void setUsage(Double usage) {
        this.usage = usage;
    }

    public Double getInflow() {
        return inflow;
    }

    public void setInflow(Double inflow) {
        this.inflow = inflow;
    }

    public Double getOutflow() {
        return outflow;
    }

    public void setOutflow(Double outflow) {
        this.outflow = outflow;
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

    public Integer getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(Integer adminStatus) {
        this.adminStatus = adminStatus;
    }

    public Integer getOperStatus() {
        return operStatus;
    }

    public void setOperStatus(Integer operStatus) {
        this.operStatus = operStatus;
    }

    public String getMtu() {
        return mtu;
    }

    public void setMtu(String mtu) {
        this.mtu = mtu;
    }

    public String getEmsdn() {
        return emsdn;
    }

    public void setEmsdn(String emsdn) {
        this.emsdn = emsdn;
    }


    public void copyShallow(BObject bObject) {
        Physicalterminationpoint b = (Physicalterminationpoint) bObject;
        this.id = b.getId();
        this.dn = b.getDn();

        this.setNo(b.getNo());

        this.setPtpCapacity(b.getPtpCapacity());

        this.setPtpRate(b.getPtpRate());

        this.setComments(b.getComments());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserlabel() {
        return userlabel;
    }

    public void setUserlabel(String userlabel) {
        this.userlabel = userlabel;
    }

    public String getNativeemsname() {
        return nativeemsname;
    }

    public void setNativeemsname(String nativeemsname) {
        this.nativeemsname = nativeemsname;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    public String getMedn() {
        return medn;
    }

    public void setMedn(String medn) {
        this.medn = medn;
    }

    public String getCarddn() {
        return carddn;
    }

    public void setCarddn(String carddn) {
        this.carddn = carddn;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPtpCapacity() {
        return ptpCapacity;
    }

    public void setPtpCapacity(int ptpCapacity) {
        this.ptpCapacity = ptpCapacity;
    }

    public int getPtpRate() {
        return ptpRate;
    }

    public void setPtpRate(int ptpRate) {
        this.ptpRate = ptpRate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getPtptype() {
        return ptptype;
    }

    public void setPtptype(int ptptype) {
        this.ptptype = ptptype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }

    public String getPortNo() {
        return portNo;
    }

    public void setPortNo(String portNo) {
        this.portNo = portNo;
    }
}
