package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Ronnie.Chen
 * Date: 11-7-15
 */

@Entity
@Table(name = "R_TOPOLINK")
public class RTopoLink  extends BObject implements java.io.Serializable {

	// Fields




    @ManyToOne(fetch= FetchType.EAGER,cascade={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "parenttoponodeid")
	private RTopoNode RTopoNodeByParenttoponodeid;


    @ManyToOne(fetch= FetchType.EAGER ,cascade={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "anodeid")
    private RTopoNode RTopoNodeByAnodeid;


    @ManyToOne(fetch= FetchType.EAGER ,cascade={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "znodeid")
    private RTopoNode RTopoNodeByZnodeid;


	private String name;
	private String code;
	private String memo;
	private String rate;
	private String color;
	private Long style;
	private Long sizes;
	private Long linkresourceid;
	private String linkresourcename;
	private String anodedesc;
	private String znodedesc;
	private String znodecode;
    private String anodecode;
    @Transient
    private Map propertiesMap = new HashMap();
    private String properties;
    @Transient
    private Object userObject = null;

    public String getZnodecode() {
        return znodecode;
    }

    public void setZnodecode(String znodecode) {
        this.znodecode = znodecode;
    }

    public String getAnodecode() {
        return anodecode;
    }

    public void setAnodecode(String anodecode) {
        this.anodecode = anodecode;
    }

    

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

   
	// Constructors

    public Object getUserObject() {
        return userObject;
    }

    public void setUserObject(Object userObject) {
        this.userObject = userObject;
    }

   

	/** default constructor */
	public RTopoLink() {
	}

	/** full constructor */
	public RTopoLink(RTopoNode RTopoNodeByParenttoponodeid,
			RTopoNode RTopoNodeByAnodeid, RTopoNode RTopoNodeByZnodeid,
			String name, String code, String memo, String rate, String color,
			Long style, Long sizes, Long linkresourceid,
			String linkresourcename, String anodedesc, String znodedesc) {
		this.RTopoNodeByParenttoponodeid = RTopoNodeByParenttoponodeid;
		this.RTopoNodeByAnodeid = RTopoNodeByAnodeid;
		this.RTopoNodeByZnodeid = RTopoNodeByZnodeid;
		this.name = name;
		this.code = code;
		this.memo = memo;
		this.rate = rate;
		this.color = color;
		this.style = style;
		this.sizes = sizes;
		this.linkresourceid = linkresourceid;
		this.linkresourcename = linkresourcename;
		this.anodedesc = anodedesc;
		this.znodedesc = znodedesc;
	}


	public RTopoNode getRTopoNodeByParenttoponodeid() {
		return this.RTopoNodeByParenttoponodeid;
	}

	public void setRTopoNodeByParenttoponodeid(
			RTopoNode RTopoNodeByParenttoponodeid) {
		this.RTopoNodeByParenttoponodeid = RTopoNodeByParenttoponodeid;
	}

	public RTopoNode getRTopoNodeByAnodeid() {
		return this.RTopoNodeByAnodeid;
	}

	public void setRTopoNodeByAnodeid(RTopoNode RTopoNodeByAnodeid) {
		this.RTopoNodeByAnodeid = RTopoNodeByAnodeid;
	}

	public RTopoNode getRTopoNodeByZnodeid() {
		return this.RTopoNodeByZnodeid;
	}

	public void setRTopoNodeByZnodeid(RTopoNode RTopoNodeByZnodeid) {
		this.RTopoNodeByZnodeid = RTopoNodeByZnodeid;
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

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getStyle() {
		return this.style;
	}

	public void setStyle(Long style) {
		this.style = style;
	}

	public Long getSizes() {
		return this.sizes;
	}

	public void setSizes(Long sizes) {
		this.sizes = sizes;
	}

	public Long getLinkresourceid() {
		return this.linkresourceid;
	}

	public void setLinkresourceid(Long linkresourceid) {
		this.linkresourceid = linkresourceid;
	}

	public String getLinkresourcename() {
		return this.linkresourcename;
	}

	public void setLinkresourcename(String linkresourcename) {
		this.linkresourcename = linkresourcename;
	}

	public String getAnodedesc() {
		return this.anodedesc;
	}

	public void setAnodedesc(String anodedesc) {
		this.anodedesc = anodedesc;
	}

	public String getZnodedesc() {
		return this.znodedesc;
	}

	public void setZnodedesc(String znodedesc) {
		this.znodedesc = znodedesc;
	}

}