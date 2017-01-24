package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.alcatelsbell.nms.valueobject.BObject;
@Table(name="P_MTHRESHOLDVALUE")
@Entity

public class PmThresholdValue extends BObject implements Serializable {
	/**
	 * serialVersionUID
	 */
	
	private static final long serialVersionUID = 6803236132549346782L;
			/** 
			 * @author jxiao
			 * time:2013-10-15
			 */
				private Integer granularity;
				private Integer standard;
				private String units;//单位 
				private int info;//告警说明
				private Integer alarmSeverity;//告警级别用常量表示
				private double lowValue;  //下限值
				private double highValue;//上限值
				private Integer defaultLowValue;//默认下限值
				private Integer defaultHighValue;//默认上限值
				private long pmThreasholdRuleId;//外健ID
				private Integer entityId; //设备的ID
			    private Integer entityType;//区分是否是设备还是端口以0,1来表示
			    private Integer noid;
			   
			    public Integer getNoid() {
					return noid;
				}
				public void setNoid(Integer noid) {
					this.noid = noid;
				}
				public Integer getEntityId() {
					return entityId;
				}
				public void setEntityId(Integer entityId) {
					this.entityId = entityId;
				}
				public Integer getEntityType() {
					return entityType;
				}
				public void setEntityType(Integer entityType) {
					this.entityType = entityType;
				}
				
//				 @Transient
//				private ArrayList roleses;
//				public ArrayList getRoleses() {
//					return roleses;
//				}
//				public void setRoleses(ArrayList roleses) {
//					this.roleses = roleses;
//				}
				public Integer getGranularity() {
					return granularity;
				}
				public void setGranularity(Integer granularity) {
					this.granularity = granularity;
				}
				public Integer getStandard() {
					return standard;
				}
				public void setStandard(Integer standard) {
					this.standard = standard;
				}
				public String getUnits() {
					return units;
				}
				public void setUnits(String units) {
					this.units = units;
				}
				public int getInfo() {
					return info;
				}
				public void setInfo(int info) {
					this.info = info;
				}
				
				public Integer getAlarmSeverity() {
					return alarmSeverity;
				}
				public void setAlarmSeverity(Integer alarmSeverity) {
					this.alarmSeverity = alarmSeverity;
				}
				public double getLowValue() {
					return lowValue;
				}
				public void setLowValue(double lowValue) {
					this.lowValue = lowValue;
				}
				public double getHighValue() {
					return highValue;
				}
				public void setHighValue(double highValue) {
					this.highValue = highValue;
				}
				public Integer getDefaultLowValue() {
					return defaultLowValue;
				}
				public void setDefaultLowValue(Integer defaultLowValue) {
					this.defaultLowValue = defaultLowValue;
				}
				public Integer getDefaultHighValue() {
					return defaultHighValue;
				}
				public void setDefaultHighValue(Integer defaultHighValue) {
					this.defaultHighValue = defaultHighValue;
				}
				public long getPmThreasholdRuleId() {
					return pmThreasholdRuleId;
				}
				public void setPmThreasholdRuleId(long pmThreasholdRuleId) {
					this.pmThreasholdRuleId = pmThreasholdRuleId;
				}
				public static long getSerialversionuid() {
					return serialVersionUID;
				}
				
				/*private long id;
				public long getId() {
					return id;
				}
				public void setId(long id) {
					this.id = id;
				}*/
				
					

}
