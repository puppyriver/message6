/**
 * 
 */
package com.alcatelsbell.nms.valueobject.flow;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.ExtBObject;

/**
 * @author: Aaron
 * Date: 2012-10-18
 * Time: 下午02:19:57
 */
@Entity
@Table(name = "R_WORKORDER_OPTICALLINK")
public class WorkOrderOpticallinkRelationShip extends ExtBObject{
	
	/*工单dn*/
	private String workOrderDn;
	
	/*光路dn*/
	private String OpticallinkDn;
	
	public String getWorkOrderDn() {
		return workOrderDn;
	}
	public void setWorkOrderDn(String workOrderDn) {
		this.workOrderDn = workOrderDn;
	}
	public String getOpticallinkDn() {
		return OpticallinkDn;
	}
	public void setOpticallinkDn(String opticallinkDn) {
		OpticallinkDn = opticallinkDn;
	}
}
