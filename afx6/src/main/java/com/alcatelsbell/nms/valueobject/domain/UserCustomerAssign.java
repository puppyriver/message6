package com.alcatelsbell.nms.valueobject.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;

/**
 * @author:LiXiaoBing
 * @date:2012-11-30
 * @time:下午02:10:08
 */
@Table(name="USERCUSTOMERASSIGN")
@Entity
public class UserCustomerAssign extends BObject{
    
   private String customerDn;
   private String operatorDn;
   private Long customerId;
   private Long operatorId;
   
    public String getCustomerDn() {
        return customerDn;
    }
    public void setCustomerDn(String customerDn) {
        this.customerDn = customerDn;
    }
    public String getOperatorDn() {
        return operatorDn;
    }
    public void setOperatorDn(String operatorDn) {
        this.operatorDn = operatorDn;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
       
           
}
