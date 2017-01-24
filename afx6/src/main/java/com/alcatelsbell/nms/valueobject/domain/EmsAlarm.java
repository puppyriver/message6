package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;




@Table(name="E_MSALARM")
@Entity
public class EmsAlarm  extends BObject implements Serializable{
	
	
	         private static final long serialVersionUID = -1L;
	         
	         
	         private String emsname;
	         
	         @BField(description = "任务编号",searchType = BField.SearchType.NULLABLE)
	         private String taskSerial;
	         
	         @BField(description = "备注")
	         @Column(length = 2048)
	         private String additinalInfo;
	         
	         @Column(length = 2048)
	         private String valuer;
	         
	         
	         
	         
	         public String getEmsname() {
				return emsname;
			}

			public void setEmsname(String emsname) {
				this.emsname = emsname;
			}

			

			public String getTaskSerial() {
				return taskSerial;
			}

			public void setTaskSerial(String taskSerial) {
				this.taskSerial = taskSerial;
			}

			public String getAdditinalInfo() {
				return additinalInfo;
			}

			public void setAdditinalInfo(String additinalInfo) {
				this.additinalInfo = additinalInfo;
			}

			public String getValuer() {
				return valuer;
			}

			public void setValuer(String valuer) {
				this.valuer = valuer;
			}

			public static long getSerialversionuid() {
				return serialVersionUID;
			}

			

}
