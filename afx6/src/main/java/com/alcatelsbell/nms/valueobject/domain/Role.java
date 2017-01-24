/**************************************************************************
 * $RCSfile: Role.java,v $  $Revision: 1.1 $  $Date: 2008/07/23 02:37:07 $
 *
 * $Log: Role.java,v $
 * Revision 1.1  2008/07/23 02:37:07  jaty
 * MR#:ITmanager-10
 *
 * Revision 1.1  2005/06/05 06:00:37  lukezhao
 * MR#:VPN-1
 *
 * Revision 1.1.1.1  2004/10/11 07:45:56  taienzhi
 * no message
 *
 * Revision 1.3  2004/04/05 05:14:15  brookqi
 * MR#: NM60-159
 * ����toString()
 *
 * Revision 1.2  2002/06/28 01:59:56  brookqi
 * add cvs head
 *
 ***************************************************************************/
package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;
import java.util.Vector ;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.alcatelsbell.nms.valueobject.BObject;
//import gxlu.afx.system.common.constant.SysConst;


@Table(name="ROLE")
@Entity
public class Role extends BObject  implements Serializable{


  protected byte predefined;
  protected String name;
  protected String description;
  
  public      int oid;
  //add by Panfengxia 2001/07/30
  protected String appModule;
  //add end 2001/07/30


  public byte getPredefined()
  {
    return predefined ;
  }

  public void setPredefined(byte m_predefined)
  {
    this.predefined = m_predefined;
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
 
  
   
    
}
