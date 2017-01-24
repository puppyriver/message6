package com.alcatelsbell.nms.valueobject.alarm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;

/**
 * @author:LiXiaoBing
 * @date:2013-3-12
 * @time:下午02:12:12
 */
@Table(name = "T_CUSTOMERTREE")
@Entity
public class CustomerTree  extends BObject {
    
    private Long customerId;
    
    private Long customerDn;
    
    private Integer productCount;
    
    private Integer maxSeverity;
    
    private Integer childrenCount;
    
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerDn() {
        return customerDn;
    }

    public void setCustomerDn(Long customerDn) {
        this.customerDn = customerDn;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getMaxSeverity() {
        return maxSeverity;
    }

    public void setMaxSeverity(Integer maxSeverity) {
        this.maxSeverity = maxSeverity;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

}
