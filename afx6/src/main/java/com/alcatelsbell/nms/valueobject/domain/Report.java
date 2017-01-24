package com.alcatelsbell.nms.valueobject.domain;

import java.util.Date;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alcatelsbell.nms.valueobject.BObject;

@Table(name = "REPORT")
@Entity
public class Report extends BObject implements java.io.Serializable{
	
	    private long customerid;
	    private Date starttime;
	    private Date endtime;
	    private int type;
	    private String filename;
	    @Transient
	    private byte[] fileBlob;
	    private String submitter;
	    private long submitterId;
	    private int periodType;
	    private Date createTime;
	    
	    private String customerProperty;
	    private String custoemrLevel;
	    private String customerRegion;
	    
	    private String productType; //业务类型
	    private String customerdn; //客户dn
	    private String alarmLevel; //告警级别
	    private String busPropety; //业务属性
	    private String dispatchbill;   //派单状态
            
	    public String getAlarmLevel() {
            return alarmLevel;
        }
        public void setAlarmLevel(String alarmLevel) {
            this.alarmLevel = alarmLevel;
        }
        public String getBusPropety() {
            return busPropety;
        }
        public void setBusPropety(String busPropety) {
            this.busPropety = busPropety;
        }
        public String getBusLevel() {
            return busLevel;
        }
        public void setBusLevel(String busLevel) {
            this.busLevel = busLevel;
        }
        public String getDispatchbill() {
            return dispatchbill;
        }
        public void setDispatchbill(String dispatchbill) {
            this.dispatchbill = dispatchbill;
        }
        private String busLevel;   //业务等级
	   

	    
	    public String getCustomerdn() {
            return customerdn;
        }
        public void setCustomerdn(String customerdn) {
            this.customerdn = customerdn;
        }
        public String getCustomerProperty() {
			return customerProperty;
		}
		public void setCustomerProperty(String customerProperty) {
			this.customerProperty = customerProperty;
		}
		public String getCustoemrLevel() {
			return custoemrLevel;
		}
		public void setCustoemrLevel(String custoemrLevel) {
			this.custoemrLevel = custoemrLevel;
		}
		public String getCustomerRegion() {
			return customerRegion;
		}
		public void setCustomerRegion(String customerRegion) {
			this.customerRegion = customerRegion;
		}
		public String getProductType() {
			return productType;
		}
		public void setProductType(String productType) {
			this.productType = productType;
		}
		public Report(){
	    	
	    }
	    public Report(long customerid, Date starttime, Date endtime,
				long version, int type, String filename, byte[] fileBlob,
				String submitter, long submitterId, int periodType,
				Date createTime, int specType, Vector reportCustomers,
				String sourceName, String reportName,String customerProperty,
			    String custoemrLevel,String customerRegion, String productType) {
			super();
			this.customerid = customerid;
			this.starttime = starttime;
			this.endtime = endtime;
			this.version = version;
			this.type = type;
			this.filename = filename;
			this.fileBlob = fileBlob;
			this.submitter = submitter;
			this.submitterId = submitterId;
			this.periodType = periodType;
			this.createTime = createTime;
			this.specType = specType;
			this.reportCustomers = reportCustomers;
			this.sourceName = sourceName;
			this.ReportName = reportName;
			this.customerProperty = customerProperty;
			this.custoemrLevel = custoemrLevel;
			this.customerRegion = customerRegion;
			this.productType = productType;
		}
		private int specType;
	    public long getCustomerid() {
			return customerid;
		}
		public void setCustomerid(long customerid) {
			this.customerid = customerid;
		}
		public Date getStarttime() {
			return starttime;
		}
		public void setStarttime(Date starttime) {
			this.starttime = starttime;
		}
		public Date getEndtime() {
			return endtime;
		}
		public void setEndtime(Date endtime) {
			this.endtime = endtime;
		}

		public void setVersion(long version) {
			this.version = version;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public byte[] getFileBlob() {
			return fileBlob;
		}
		public void setFileBlob(byte[] fileBlob) {
			this.fileBlob = fileBlob;
		}
		public String getSubmitter() {
			return submitter;
		}
		public void setSubmitter(String submitter) {
			this.submitter = submitter;
		}
		public long getSubmitterId() {
			return submitterId;
		}
		public void setSubmitterId(long submitterId) {
			this.submitterId = submitterId;
		}
		public int getPeriodType() {
			return periodType;
		}
		public void setPeriodType(int periodType) {
			this.periodType = periodType;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public int getSpecType() {
			return specType;
		}
		public void setSpecType(int specType) {
			this.specType = specType;
		}
		public Vector getReportCustomers() {
			return reportCustomers;
		}
		public void setReportCustomers(Vector reportCustomers) {
			this.reportCustomers = reportCustomers;
		}
		public String getSourceName() {
			return sourceName;
		}
		public void setSourceName(String sourceName) {
			this.sourceName = sourceName;
		}
		public String getReportName() {
			return ReportName;
		}
		public void setReportName(String reportName) {
			ReportName = reportName;
		}

		private Vector reportCustomers;
	    private String sourceName;
	    private String ReportName;
	    
//	    @Transient

}
