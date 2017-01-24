/**************************************************************************
 *
 * $RCSfile: Permission.java,v $  $Revision: 1.1 $  $Date: 2008/07/23 02:36:38 $
 *
 * $Log: Permission.java,v $
 * Revision 1.1  2008/07/23 02:36:38  jaty
 * MR#:ITmanager-10
 *
 * Revision 1.1  2005/06/05 06:00:37  lukezhao
 * MR#:VPN-1
 *
 * Revision 1.1.1.1  2004/10/11 07:45:56  taienzhi
 * no message
 *
 * Revision 1.1  2002/02/20 08:02:33  wzwu
 * no message
 *
 * Revision 1.4  2002/02/06 01:21:04  wzwu
 * no message
 *
 * Revision 1.3  2002/02/05 09:31:55  yxjjxy
 * change package
 *
 * Revision 1.2  2001/10/08 03:22:32  zma
 * Synchronize Core1.1 with Core1.0
 *
 * Revision 1.2  2001/08/31 01:48:48  yxjjxy
 * ADD PROJECT,ISVALID
 *
 * Revision 1.1  2001/08/27 06:43:03  zma
 * initialize for core
 *
 * Revision 1.3  2001/07/30 02:23:35  panfx
 * add appmodule
 *
 * Revision 1.2  2001/03/28 09:16:28  yxjjxy
 * remove ref to sysconst
 *
 * Revision 1.1  2001/03/23 01:45:18  huyong
 * be removed to framework
 *
 * Revision 1.1.2.1  2000/12/05 02:31:59  yxjjxy
 * modify security management
 *
 *
 *
 ***************************************************************************/

package com.alcatelsbell.nms.valueobject.domain;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Table(name="PERMISSION")
@Entity
public class Permission extends BObject implements Serializable{

  protected String name;
  protected String description;
  private String type;
  private String targetKey;
  private Long targetId;
  public      int oid;
  //add by Panfengxia 2001/07/30
  protected String appModule;
  //add end 2001/07/30
  protected long appliedProject;

  public Permission()
  {
   
  }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName()
  {
    return name ;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description ;
  }

  public void setDescription(String m_description)
  {
    this.description = m_description;
  }
  //add by Panfengxia 2001/07/30 for netguard
  public String getAppmodule()
  {
      return appModule;
  }
  public void setAppmodule(String _appModule)
  {
      this.appModule = _appModule;
  }
  //add end 2001/07/30
  public void setPersistType(byte pt)
  {
    this.persistType = pt;
  }

  public byte getPersistType()
  {
    return this.persistType;
  }

  protected byte persistType;


  /**
    used as optimitic lock control
  */

  

  public long getAppliedProject()
  {
      return appliedProject;
  }
  public void setAppliedProject(long _project)
  {
      appliedProject = _project;
  }

    public String getTargetKey() {
        return targetKey;
    }

    public void setTargetKey(String targetKey) {
        this.targetKey = targetKey;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}