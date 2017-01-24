package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.common.DicEntry;
import com.alcatelsbell.nms.common.annotation.DicGroup;

/**
 * User: Ronnie
 * Date: 12-6-15
 * Time: 下午4:35
 */
public class SysDictionarys {
    /**
     * 机框单双面
     * */
    @DicGroup(name="MOBILETYPE")
    public static abstract class MOBILETYPE {

        public static final DicEntry MOBILE = new DicEntry("手机",1,"PHONE");

        public static final DicEntry TABLET = new DicEntry("平板",2,"TABLET") ;
    }

    @DicGroup(name="ACCOUNTSTATUS")
    public static abstract class ACCOUNTSTATUS {
        public static final DicEntry REQUEST = new DicEntry("请求",0,"REQUEST","FFFF00");
        public static final DicEntry ACTIVE = new DicEntry("启用",1,"ACTIVE","00FF00");

        public static final DicEntry PASSTIVE = new DicEntry("停用",2,"PASSTIVE","FF0000") ;
    }
    
    /**
     * 区域类型
     * */
    @DicGroup(name="REGIONTYPE")
    public static abstract class REGIONTYPE {

        public static final DicEntry PROVINCE = new DicEntry("省（直辖市、自治区）",1,"PROVINCE");

        public static final DicEntry CITY = new DicEntry("市（自治州）",2,"CITY") ;
        
        public static final DicEntry COUNTY = new DicEntry("县（县级市、区）",3,"COUNTY") ;
        
        public static final DicEntry TOWN = new DicEntry("乡镇",4,"TOWN") ;
    }
    
    /**
     * 区域类型
     * */
    @DicGroup(name="ROOTFLAG")
    public static abstract class ROOTFLAG {

        public static final DicEntry ROOTREGION = new DicEntry("是",1,"ROOTREGION");

        public static final DicEntry ORDINARYREGION = new DicEntry("否",0,"ORDINARYREGION") ;
    }
}
