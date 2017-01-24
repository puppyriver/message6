package com.alcatelsbell.nms.valueobject.physical;

import com.alcatelsbell.nms.valueobject.BObject;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * User: Ronnie.Chen
 * Date: 11-5-26
 * Time:
 */
@Entity
@Table(name = "P_CIRCUIT")
public class Circuit extends BObject {
    private String emsdn;

    @Index(name = "CIRCUIT_INDEX_CIRCUITNO")
    private String circuitno;
    private String name;
    private String nativeemsname;
    private String amedn;
    private String zmedn;


    @Index(name = "CIRCUIT_INDEX_APORTDN")
    private String aportdn;

    @Index(name = "CIRCUIT_INDEX_ZPORTDN")
    private String zportdn;

    public String getActp() {
        return actp;
    }

    public void setActp(String actp) {
        this.actp = actp;
    }

    public String getZctp() {
        return zctp;
    }

    public void setZctp(String zctp) {
        this.zctp = zctp;
    }

    private String actp;
    private String zctp;

    @Lob()
   // @Type(type = "org.hibernate.type.StringClobType")
    @Column(name="textRoute", columnDefinition="CLOB", nullable=true)
    private String textRoute;

    public String getEmsdn() {
        return emsdn;
    }

    public void setEmsdn(String emsdn) {
        this.emsdn = emsdn;
    }

    public String getCircuitno() {
        return circuitno;
    }

    public void setCircuitno(String circuitno) {
        this.circuitno = circuitno;
    }

    public String getNativeemsname() {
        return nativeemsname;
    }

    public void setNativeemsname(String nativeemsname) {
        this.nativeemsname = nativeemsname;
    }

    public String getAmedn() {
        return amedn;
    }

    public void setAmedn(String amedn) {
        this.amedn = amedn;
    }

    public String getZmedn() {
        return zmedn;
    }

    public void setZmedn(String zmedn) {
        this.zmedn = zmedn;
    }

    public String getAportdn() {
        return aportdn;
    }

    public void setAportdn(String aportdn) {
        this.aportdn = aportdn;
    }

    public String getZportdn() {
        return zportdn;
    }

    public void setZportdn(String zportdn) {
        this.zportdn = zportdn;
    }

    public String getTextRoute() {
        return textRoute;
    }

    public void setTextRoute(String textRoute) {
        this.textRoute = textRoute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
