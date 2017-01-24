package com.alcatelsbell.nms.valueobject.alarm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alcatelsbell.nms.valueobject.BObject;

@Table(name = "F_GroupSeverityPolicy")
@Entity
public class GroupSeverityPolicy extends BObject{

	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = 6851096224247425195L;

	private String name;
	
	private Integer groupSeverity; //集客故障等级
	
	private String slaLevel; //业务等级（SLA等级）
	
	private String deviceSeverity; //网管告警等级

	@Transient
	private List<Integer> slaList;
	
	@Transient
	private List<Integer> deviceSeverityList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGroupSeverity() {
		return groupSeverity;
	}

	public void setGroupSeverity(Integer groupSeverity) {
		this.groupSeverity = groupSeverity;
	}

	public String getDeviceSeverity() {
		return deviceSeverity;
	}

	public void setDeviceSeverity(String deviceSeverity) {
		this.deviceSeverity = deviceSeverity;
	}

	public List<Integer> getDeviceSeverityList() {
		this.deviceSeverityList = stringToList(deviceSeverity);
		return deviceSeverityList;
	}

	public void setDeviceSeverityList(List<Integer> deviceSeverityList) {
		this.deviceSeverityList = deviceSeverityList;
		this.deviceSeverity = listToString(deviceSeverityList);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSlaLevel(String slaLevel) {
		this.slaLevel = slaLevel;
	}

	public void setSlaList(List<Integer> slaList) {
		this.slaList = slaList;
		this.slaLevel = listToString(slaList);
	}

	public String getSlaLevel() {
		return slaLevel;
	}

	public List<Integer> getSlaList() {
		this.slaList = stringToList(slaLevel);
		return slaList;
	}
	
	private String listToString(List<Integer> values) {
		if(values == null || values.size() == 0) {
			return "";
		} else {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; ; i++) {
				if(i==values.size()-1) {
					builder.append(values.get(i));
					return builder.toString();
				} else {
					builder.append(values.get(i) + ",");
				}
			}
		}
	}
	
	private List<Integer> stringToList(String value) {
		if(value == null || value.trim().equalsIgnoreCase("")) {
			return null;
		} else {
			String[] temp = value.split(",");
			List<Integer> values = new ArrayList<Integer>();
			for (String string : temp) {
				values.add(Integer.parseInt(string));
			}
			return values;
		}
	}
	
	public static void main(String...strings) {
//		List<Integer> temp = new ArrayList<Integer>();
//		temp.add(1);
		GroupSeverityPolicy policy = new GroupSeverityPolicy();
//		System.out.println(policy.listToString(temp));
		List<Integer> values = policy.stringToList("aa,bb");
		for (Integer integer : values) {
			System.out.println(integer);
		}
	}
}
