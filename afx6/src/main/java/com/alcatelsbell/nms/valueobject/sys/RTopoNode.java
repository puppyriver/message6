package com.alcatelsbell.nms.valueobject.sys;

/**
 * User: Ronnie.Chen
 * Date: 11-7-15
 */

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.util.HashSet;

@Entity
@Table(name = "R_TOPONODE")
public class RTopoNode extends BObject implements java.io.Serializable {

	// PARENTTOPONODEID
	private RTopoNode RTopoNode;
	private String name;
	private String code;
	private String memo;
	private Long domain;
	private Long city;
	private Long category;
	private Long isstatic;
	private Long istopview;
	private String rate;
	private Long levels;
	private Long layout;
	private String entitytypename;
	private Long entityid;
	private String displayattribute;
	private String textvalue;
	private String icon;
	private Long isdrilldownable;
	private Long dummydisplaytype;
	private Double xposition;
	private Double yposition;
	@Transient
	private Map propertiesMap = new HashMap();
	private String properties;
	@Transient
	private Object userObject = null;
	@Column(name = "parenttoponodeid", insertable = false, updatable = false)
	private Long parenttoponodeid;
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	@JoinColumn(name = "parenttoponodeid")
	private RTopoNode parentNode;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH,
			CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, mappedBy = "parentNode")
	private Set<RTopoNode> RTopoNodes = new HashSet<RTopoNode>(0);

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH,
			CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, mappedBy = "RTopoNodeByParenttoponodeid")
	private Set<RTopoLink> RTopoLinks = new HashSet<RTopoLink>(0);

	public Map getPropertiesMap() {
		return propertiesMap;
	}

	public void setPropertiesMap(Map propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public Object getUserObject() {
		return userObject;
	}

	public void setUserObject(Object userObject) {
		this.userObject = userObject;
	}

	public Long getParenttoponodeid() {
		return parenttoponodeid;
	}

	public void setParenttoponodeid(Long parenttoponodeid) {
		this.parenttoponodeid = parenttoponodeid;
	}

	// private Set<RTopoNode> RTopoLinksForParenttoponodeid = new
	// HashSet<RTopoNode>(0);
	// private Set<RTopoNode> RTopoLinksForAnodeid = new HashSet<RTopoNode>(0);
	// private Set<RTopoNode> RTopoLinksForZnodeid = new HashSet<RTopoNode>(0);

	public RTopoNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(RTopoNode parentNode) {
		this.parentNode = parentNode;
	}

	public Set<RTopoLink> getRTopoLinks() {
		return RTopoLinks;
	}

	public void setRTopoLinks(Set<RTopoLink> RTopoLinks) {
		this.RTopoLinks = RTopoLinks;
	}

	// Constructors

	/** default constructor */
	public RTopoNode() {
	}

	/** minimal constructor */
	public RTopoNode(Long category, Long isstatic, Long istopview, Long layout) {
		this.category = category;
		this.isstatic = isstatic;
		this.istopview = istopview;
		this.layout = layout;
	}

	/** full constructor */
	public RTopoNode(RTopoNode RTopoNode, String name, String code,
			String memo, Long domain, Long city, Long category, Long isstatic,
			Long istopview, String rate, Long levels, Long layout,
			String entitytypename, Long entityid, String displayattribute,
			String textvalue, String icon, Long isdrilldownable,
			Long dummydisplaytype, Double xposition, Double yposition,
			// Set<RTopoNode> RTopoLinksForParenttoponodeid, Set<RTopoNode>
			// RTopoLinksForAnodeid,
			// Set<RTopoNode> RTopoLinksForZnodeid,
			Set<RTopoNode> RTopoNodes) {
		this.RTopoNode = RTopoNode;
		this.name = name;
		this.code = code;
		this.memo = memo;
		this.domain = domain;
		this.city = city;
		this.category = category;
		this.isstatic = isstatic;
		this.istopview = istopview;
		this.rate = rate;
		this.levels = levels;
		this.layout = layout;
		this.entitytypename = entitytypename;
		this.entityid = entityid;
		this.displayattribute = displayattribute;
		this.textvalue = textvalue;
		this.icon = icon;
		this.isdrilldownable = isdrilldownable;
		this.dummydisplaytype = dummydisplaytype;
		this.xposition = xposition;
		this.yposition = yposition;
		// this.RTopoLinksForParenttoponodeid = RTopoLinksForParenttoponodeid;
		// this.RTopoLinksForAnodeid = RTopoLinksForAnodeid;
		// this.RTopoLinksForZnodeid = RTopoLinksForZnodeid;
		this.RTopoNodes = RTopoNodes;
	}

	public RTopoNode getRTopoNode() {
		return this.RTopoNode;
	}

	public void setRTopoNode(RTopoNode RTopoNode) {
		this.RTopoNode = RTopoNode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getDomain() {
		return this.domain;
	}

	public void setDomain(Long domain) {
		this.domain = domain;
	}

	public Long getCity() {
		return this.city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getCategory() {
		return this.category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getIsstatic() {
		return this.isstatic;
	}

	public void setIsstatic(Long isstatic) {
		this.isstatic = isstatic;
	}

	public Long getIstopview() {
		return this.istopview;
	}

	public void setIstopview(Long istopview) {
		this.istopview = istopview;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Long getLevels() {
		return this.levels;
	}

	public void setLevels(Long levels) {
		this.levels = levels;
	}

	public Long getLayout() {
		return this.layout;
	}

	public void setLayout(Long layout) {
		this.layout = layout;
	}

	public String getEntitytypename() {
		return this.entitytypename;
	}

	public void setEntitytypename(String entitytypename) {
		this.entitytypename = entitytypename;
	}

	public Long getEntityid() {
		return this.entityid;
	}

	public void setEntityid(Long entityid) {
		this.entityid = entityid;
	}

	public String getDisplayattribute() {
		return this.displayattribute;
	}

	public void setDisplayattribute(String displayattribute) {
		this.displayattribute = displayattribute;
	}

	public String getTextvalue() {
		return this.textvalue;
	}

	public void setTextvalue(String textvalue) {
		this.textvalue = textvalue;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getIsdrilldownable() {
		return this.isdrilldownable;
	}

	public void setIsdrilldownable(Long isdrilldownable) {
		this.isdrilldownable = isdrilldownable;
	}

	public Long getDummydisplaytype() {
		return this.dummydisplaytype;
	}

	public void setDummydisplaytype(Long dummydisplaytype) {
		this.dummydisplaytype = dummydisplaytype;
	}

	public Double getXposition() {
		return this.xposition;
	}

	public void setXposition(Double xposition) {
		this.xposition = xposition;
	}

	public Double getYposition() {
		return this.yposition;
	}

	public void setYposition(Double yposition) {
		this.yposition = yposition;
	}

	// public Set<RTopoNode> getRTopoLinksForParenttoponodeid() {
	// return this.RTopoLinksForParenttoponodeid;
	// }
	//
	// public void setRTopoLinksForParenttoponodeid(
	// Set<RTopoNode> RTopoLinksForParenttoponodeid) {
	// this.RTopoLinksForParenttoponodeid = RTopoLinksForParenttoponodeid;
	// }
	//
	// public Set<RTopoNode> getRTopoLinksForAnodeid() {
	// return this.RTopoLinksForAnodeid;
	// }
	//
	// public void setRTopoLinksForAnodeid(Set<RTopoNode> RTopoLinksForAnodeid)
	// {
	// this.RTopoLinksForAnodeid = RTopoLinksForAnodeid;
	// }
	//
	// public Set<RTopoNode> getRTopoLinksForZnodeid() {
	// return this.RTopoLinksForZnodeid;
	// }
	//
	// public void setRTopoLinksForZnodeid(Set<RTopoNode> RTopoLinksForZnodeid)
	// {
	// this.RTopoLinksForZnodeid = RTopoLinksForZnodeid;
	// }

	public Set<RTopoNode> getRTopoNodes() {
		return this.RTopoNodes;
	}

	public void setRTopoNodes(Set<RTopoNode> RTopoNodes) {
		this.RTopoNodes = RTopoNodes;
	}

}