package com.alcatelsbell.nms.valueobject.domain;

/**
 * User: Ronnie.Chen
 * Date: 11-5-16
 * Time: ����10:09
 */


import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.ExtBObject;
import com.alcatelsbell.nms.valueobject.sys.SysDictionarys;

import java.util.List;
import java.util.Vector;
import javax.persistence.*;


/**
 *
 * @author g
 */
@Entity
public class UsersGroup extends ExtBObject {
  	/*拼装名字*/
	private String name;
	
	@BField(description = "组织名称",searchType = BField.SearchType.NULLABLE)
    private String groupname;
	
	@BField(description = "组织编号")
	private Integer no;
	
	@BField(description = "上级组织",editType = BField.EditType.REQUIRED,
	    dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.UsersGroup",
	    dnReferenceEntityField = "groupname",
	    dnReferenceTransietField = "parentGroupName")
	protected String parentGroupDn;
	
	@DicGroupMapping(groupName = "GROUPTYPE", definitionClass = SysDictionarys.class)
	@BField(description = "组织类型",searchType = BField.SearchType.NULLABLE)
    private Integer type;
	
	@DicGroupMapping(groupName = "ROOTFLAG", definitionClass = SysDictionarys.class)
	@BField(description = "设置根组织")
    private Integer rootFlag;

	 
    /*编码*/
    private String code;
    @Transient
    protected UsersGroup parentGroup = null;

 //   @OneToMany (mappedBy = "parentRegion")
    @Transient
    protected List<UsersGroup> groups = null;

    @Transient
    protected String parentGroupName=null;
   
    public String getParentGroupDn() {
		return parentGroupDn;
	}
 
	public void setParentGroupDn(String parentGroupDn) {
		this.parentGroupDn = parentGroupDn;
	}
 
	public UsersGroup getParentGroup() {
		return parentGroup;
	}
 
	public void setParentGroup(UsersGroup parentGroup) {
		this.parentGroup = parentGroup;
	}
 
	public String getGroupname() {
		return groupname;
	}
 
	public List<UsersGroup> getGroups() {
		return groups;
	}
   public void setParentGroupName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}
    public void setGroups(List<UsersGroup> _groups) {
        groups = _groups;
        if (groups != null) {
            for (UsersGroup region : groups) {
                region.setParentGroup(this);
            }
        }
    }

    public void addGroup(UsersGroup _region) {
        if (groups == null) {
        	groups = new Vector();
        }
        _region.setParentGroup(this);
        groups.add(_region);
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
 
    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
        
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
   @Override
    public String toString() {
        return this.name;
    }

	public String getRegionname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getParentGroupName() {
		return parentGroupName;
	}

	public void setParentRegionName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}

	public Integer getRootFlag() {
		return rootFlag;
	}

	public void setRootFlag(Integer rootFlag) {
		this.rootFlag = rootFlag;
	}
    
}
