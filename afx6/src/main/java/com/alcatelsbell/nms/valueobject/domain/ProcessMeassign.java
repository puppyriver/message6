package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;
@Table(name="P_ROCESSMEASSIGN")
@Entity
public class ProcessMeassign extends BObject implements Serializable{
			 /**p_rocessmeassign
			  * 
			 * 进程信息实体类
			 */
	private static final long serialVersionUID = 1L;
			 private Integer meid;
		     private String processName;
		     private double  isaLive;
		     private double cpu;
		     private double memery;
		     private String description;
		     private double severity;
		     private String name;
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public Integer getMeid() {
				return meid;
			}
			public void setMeid(Integer meid) {
				this.meid = meid;
			}
			public String getProcessName() {
				return processName;
			}
			public void setProcessName(String processName) {
				this.processName = processName;
			}
			public double getIsaLive() {
				return isaLive;
			}
			public void setIsaLive(double isaLive) {
				this.isaLive = isaLive;
			}
			public double getCpu() {
				return cpu;
			}
			public void setCpu(double cpu) {
				this.cpu = cpu;
			}
			public double getMemery() {
				return memery;
			}
			public void setMemery(double memery) {
				this.memery = memery;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			public double getSeverity() {
				return severity;
			}
			public void setSeverity(double severity) {
				this.severity = severity;
			}
}
