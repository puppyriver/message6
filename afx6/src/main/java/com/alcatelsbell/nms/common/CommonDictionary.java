package com.alcatelsbell.nms.common;

import com.alcatelsbell.nms.common.annotation.DicGroup;
import com.alcatelsbell.nms.common.annotation.Dictionary;

/**
 * User: Ronnie
 * Date: 12-5-7
 * Time: 下午4:24
 *
 */

@Dictionary
public class CommonDictionary {


    static {

    }

   public static final class ALARM_STATUS {
       public static final DicEntry ALARM_STATUS_ACTIVE = new DicEntry("激活",0,"ACTIVE");
       public static final DicEntry ALARM_STATUS_ACKED = new DicEntry("已确认",1,"ACK");
       public static final DicEntry ALARM_STATUS_CLEARED = new DicEntry("已清除",2,"CLEAR");
       public static final DicEntry ALARM_STATUS_ACK_CLEARED = new DicEntry("确认清除",3,"ACK_CLEAR");
   }

    @DicGroup(name="ALARM_SEVERITY")
    public static final class ALARM_SEVERITY {
        public static final DicEntry ALARM_SEVERITY_CRITICAL = new DicEntry("严重告警",0,"CRITICAL");
        public static final DicEntry ALARM_SEVERITY_MAJOR = new DicEntry("主要告警",1,"MAJOR");
        public static final DicEntry ALARM_SEVERITY_MINOR = new DicEntry("次要告警",2,"MINOR");
        public static final DicEntry ALARM_SEVERITY_WARNING = new DicEntry("警告告警",3,"WARNING");
        public static final DicEntry ALARM_SEVERITY_INTERMEDIATE = new DicEntry("一般告警",4,"INTERMEDIATE");
        public static final DicEntry ALARM_SEVERITY_CLEAR = new DicEntry("清除告警",5,"CLEAR");
    }
    
    @DicGroup(name="ALARM_SEVERITY_ALIAS")
    public static final class ALARM_SEVERITY_ALIAS {
        public static final DicEntry ALARM_SEVERITY_CRITICAL = new DicEntry("一级告警",0,"CRITICAL");
        public static final DicEntry ALARM_SEVERITY_MAJOR = new DicEntry("二级告警",1,"MAJOR");
        public static final DicEntry ALARM_SEVERITY_MINOR = new DicEntry("三级告警",2,"MINOR");
        public static final DicEntry ALARM_SEVERITY_WARNING = new DicEntry("四级告警",3,"NORMAL");
    }

    @DicGroup(name = "EntityTemplate_Type")
    public static class EntityTemplate_Type {
        public static final DicEntry ENTITYTEMPLATE_TYPE_ODF_TYPE = new DicEntry("ODF类型模板",0,"ODF_TYPE");
        public static final DicEntry ENTITYTEMPLATE_TYPE_ODFSHELF_TYPE = new DicEntry("光设施子框类型模板",1,"ODFSHELF_TYPE");
        public static final DicEntry ENTITYTEMPLATE_TYPE_ODM_TYPE = new DicEntry("熔配盘类型模板",2,"ODM_TYPE");
        public static final DicEntry ENTITYTEMPLATE_TYPE_CONNECTOR_TYPE = new DicEntry("端子类型模板",3,"CONNECTOR_TYPE");
        public static final DicEntry ENTITYTEMPLATE_TYPE_ODF_CONFIG = new DicEntry("ODF架配置模板",4,"ODF_CONFIG");
        public static final DicEntry ENTITYTEMPLATE_TYPE_ODFSHELF_CONFIG = new DicEntry("光设施子框配置模板",5,"ODFSHELF_CONFIG");
        public static final DicEntry ENTITYTEMPLATE_TYPE_ODM_CONFIG = new DicEntry("熔配盘配置模板",6,"ODM_CONFIG");
    }

    public static final class ALARM_ISSHIELD {
        public static final DicEntry ALARM_ISSHIELD_YES = new DicEntry("屏蔽",1,"SHIELD");
        public static final DicEntry ALARM_ISSHIELD_NO = new DicEntry("不屏蔽",0,"NOSHIELD");

    }





}
