package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import com.alcatelsbell.nms.valueobject.BObject;

@Entity
@Table(name = "R_PmParameter")
public class PmParameter extends BObject implements Serializable {

    private String info;
	private String name ;
	private int type ;
	private String unit;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
