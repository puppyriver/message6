package com.alcatelsbell.nms.valueobject.extend;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.alcatelsbell.nms.valueobject.ExtBObject;
import com.alcatelsbell.nms.valueobject.flow.WorkOrder;
import com.alcatelsbell.nms.valueobject.odn.Rack;

/**
 * @author Aaron
 * Date 12.05.11
 * 用于扩展
 * */
@XmlRootElement(name = "Envelope")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Envelope")
public class Envelope extends ExtBObject {
	
	private static final long serialVersionUID = 1L;
	
	private String attr1;
    private String attr2;
    private String attr3;
    
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public String getAttr3() {
		return attr3;
	}
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}
    
}
