package com.alcatelsbell.nms.valueobject;

import java.util.List;
import javax.persistence.Transient;

@SuppressWarnings({ "serial", "rawtypes" })
public class ExtBObject extends BObject implements Comparable,Cloneable{
	
	@Transient
	private List childrenBObjectList;

	public List getChildrenBObjectList() {
		return childrenBObjectList;
	}

	public void setChildrenBObjectList(List childrenBObjectList) {
		this.childrenBObjectList = childrenBObjectList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		return (id<((ExtBObject)o).getId()?-1:(id==((ExtBObject) o).getId()? 0 : 1));
	}
	
}
