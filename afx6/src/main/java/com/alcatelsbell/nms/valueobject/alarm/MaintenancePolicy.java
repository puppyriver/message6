package com.alcatelsbell.nms.valueobject.alarm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;

@Table(name = "F_MaintenancePolicy")
@Entity
public class MaintenancePolicy extends BObject {

	private static final long serialVersionUID = 851992879778279222L;

	private String name; // 规则名称

	private Integer maintenanceLevel; // 集客故障响应等级，省级和地市级

	private String customerName;// 客户名称

	private String customerLevel;// 客户级别

	private String customerProperty;// 客户属性

	private String customerRegion;// 客户所属区域

	private String productCategory;// 业务大类

	private String slaLevel;// 业务保障级别（SLA等级）

	private String productType;// 业务类型

	private String productProperty;// 业务属性

	private String groupSeverity;// 集客告警级别

	private String deviceSeverity;// 网管告警级别

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMaintenanceLevel() {
		return maintenanceLevel;
	}

	public void setMaintenanceLevel(Integer maintenanceLevel) {
		this.maintenanceLevel = maintenanceLevel;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public List<Object> getCustomerLevels() {
		return stringToList(customerLevel, Integer.class);
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public String getCustomerProperty() {
		return customerProperty;
	}

	public List<Object> getCustomerProperties() {
		return stringToList(customerProperty, Integer.class);
	}

	public void setCustomerProperty(String customerProperty) {
		this.customerProperty = customerProperty;
	}

	public String getCustomerRegion() {
		return customerRegion;
	}

	public List<Object> getCustomerRegions() {
		return stringToList(customerRegion, String.class);
	}

	public void setCustomerRegion(String customerRegion) {
		this.customerRegion = customerRegion;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public List<Object> getProductCategories() {
		return stringToList(productCategory, Integer.class);
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getSlaLevel() {
		return slaLevel;
	}

	public List<Object> getSlaLevels() {
		return stringToList(slaLevel, Integer.class);
	}

	public void setSlaLevel(String slaLevel) {
		this.slaLevel = slaLevel;
	}

	public String getProductType() {
		return productType;
	}

	public List<Object> getProductTypes() {
		return stringToList(productType, Integer.class);
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getGroupSeverity() {
		return groupSeverity;
	}

	public List<Object> getGroupSeverities() {
		return stringToList(groupSeverity, Integer.class);
	}

	public void setGroupSeverity(String groupSeverity) {
		this.groupSeverity = groupSeverity;
	}

	public String getDeviceSeverity() {
		return deviceSeverity;
	}

	public void setDeviceSeverity(String deviceSeverity) {
		this.deviceSeverity = deviceSeverity;
	}

	public List<Object> getDeviceSeverities() {
		return stringToList(deviceSeverity, Integer.class);
	}

	public String getProductProperty() {
		return productProperty;
	}

	public List<Object> getProductProperties() {
		return stringToList(productProperty, Integer.class);
	}

	public void setProductProperty(String productProperty) {
		this.productProperty = productProperty;
	}

	@SuppressWarnings("rawtypes")
	private List<Object> stringToList(String value, Class clazz) {
		if (value == null || value.trim().equalsIgnoreCase("")) {
			return null;
		} else {
			String[] temp = value.split(",");
			List<Object> values = new ArrayList<Object>();
			for (String string : temp) {
				if (clazz == String.class) {
					values.add(string);
				} else if (clazz == Integer.class) {
					values.add(Integer.parseInt(string));
				}
			}
			return values;
		}
	}
}
