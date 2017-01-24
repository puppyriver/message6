package com.alcatelsbell.nms.valueobject.domain;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Change Via
 * Date: 12-10-23
 * Time: 下午3:35
 * Enginner And ProductRentLine  relate
 */
@Entity
@Table(name ="TM_ENGINEERASSIGN")
public class EngineerAssign extends BObject implements Serializable {

    @Column (name = "PRODUCT_ID")
    private Long productId;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Engineer.class,cascade = CascadeType.ALL)
    @JoinColumn(name="ENGINEER_ID",nullable = true)
    private Engineer engineer;

    @Override
    public String toString() {
        return "EngineerAssign{" +
                "productId=" + productId +
                ", engineer=" + engineer +
                '}';
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Engineer getEngineer() {
        return engineer;
    }

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
    }
}
