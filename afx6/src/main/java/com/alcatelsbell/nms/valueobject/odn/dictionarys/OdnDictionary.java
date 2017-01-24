package com.alcatelsbell.nms.valueobject.odn.dictionarys;

import com.alcatelsbell.nms.common.DicEntry;
import com.alcatelsbell.nms.common.annotation.DicGroup;

/**
 * @author Aaron
 * Date 12.05.11
 * */

public class OdnDictionary {

	/**
	 * 机房性质
	 * */
	@DicGroup(name="ROOMTYPE")
	public static abstract class ROOMTYPE {
		/**1：机房*/
		public static final DicEntry ORDINARYROOM = new DicEntry("机房",1,"ORDINARYROOM");
		/**2：局站*/
		public static final DicEntry LOCALSTATION = new DicEntry("局站",2,"LOCALSTATION");
		/**3：户外资源点*/
		public static final DicEntry OUTERSOURCE = new DicEntry("户外资源点",3,"OUTERSOURCE");
	}


    /**
     * 机房性质
     * */
    @DicGroup(name="RACKSTATUS")
    public static abstract class RACKSTATUS {
        /**1：机房*/
        public static final DicEntry  ONLINE = new DicEntry("在线",1,"ONLINE","00FF00");
        /**2：局站*/
        public static final DicEntry  OFFLINE = new DicEntry("离线",2,"OFFLINE","FF0000");
        /**3：户外资源点*/
        public static final DicEntry NO_POWER = new DicEntry("无源状态",3,"NO_POWER","FFFF00");
    }
	/**
	 * 机房类型
	 * */
	@DicGroup(name="ROOMCATEGORY")
	public static abstract class ROOMCATEGORY {
		/**2：地下进线室*/
		public static final DicEntry UNDERGROUNDLINEROOM = new DicEntry("地下进线室",2,"UNDERGROUNDLINE");
		/**3：传输机房*/
		public static final DicEntry TRANSMISSIONROOM = new DicEntry("传输机房",3,"TRANSMISSION");
		/**4：综合机房*/
		public static final DicEntry COMPREHENSIVEROOM = new DicEntry("综合机房",4,"COMPREHENSIVE");
		/**5：接入机房*/
		public static final DicEntry ACCESSROOM = new DicEntry("接入机房",5,"ACCESS");
		/**6：数据机房*/
		public static final DicEntry DATAROOM = new DicEntry("数据机房",6,"DATA");
		/**7：交换机房*/
		public static final DicEntry EXCHANGEROOM = new DicEntry("交换机房",7,"EXCHANGE");
	}
	
	/**
	 * 机框单双面
	 * */
	@DicGroup(name="SIDEDCATEGORY")
	public static abstract class SIDEDCATEGORY {
		/**单面*/
		public static final DicEntry SINGLESIDE = new DicEntry("单面",1,"SIGNLE");
		/**双面*/
		public static final DicEntry DOUBLESIDE = new DicEntry("双面",2,"DOUBLE") ;
	}
	
	/**
	 * 设备类型
	 * */
	@DicGroup(name="RACKTYPE")
	public static abstract class RACKTYPE{
		/**ODF*/
		public static final DicEntry ODF = new DicEntry("ODF",1,"ODF");
		/**综合机架*/
		public static final DicEntry OCC = new DicEntry("光交接箱",2,"OCC");
        public static final DicEntry OSS = new DicEntry("光分纤盒",3,"OSS");
    }
	
	/**
	 * 机框,板卡,端口 排列顺序
	 * */
	@DicGroup(name="ORDEROFARRANGE")
	public static abstract class ORDEROFARRANGE{
		/**从上到下*/
		public static final DicEntry UP2DOWN = new DicEntry("从上到下",1,"UP2DOWN");
		/**从下到上*/
		public static final DicEntry DOWN2UP = new DicEntry("从下到上",2,"DOWN2UP");
		/**从左到右*/
		public static final DicEntry LEFT2RIGHT = new DicEntry("从左到右",3,"LEFT2RIGHT");
		/**从右到左*/
		public static final DicEntry RIGHT2LEFT = new DicEntry("从右到左",4,"RIGHT2LEFT") ;
	}
	/**
	 * 机架正反面属性
	 * */
	@DicGroup(name="RACKINSTALLATIONSIDE")
	public static abstract class RACKINSTALLATIONSIDE{
		/**A面*/
		public static final DicEntry SIDE_A = new DicEntry("A面",1,"SIDE_A");
		/**B面*/
		public static final DicEntry SIDE_B = new DicEntry("B面",2,"SIDE_B");
	}
	
	/**
	 * 机框类型
	 * */
	@DicGroup(name="SHELFTYPE")
	public static abstract class SHELFTYPE{
		/**中控框*/
		public static final DicEntry CENTRAL_SHELF = new DicEntry("中控框",1,"CENTRAL");
		/**单控框*/
		public static final DicEntry SINGLE_SHELF = new DicEntry("单控框",2,"SINGLE");
	}
	
	/**
	 * 板卡类型
	 * */
	@DicGroup(name="CARDTYPE")
	public static abstract class CARDTYPE{
		/**业务板*/
		public static final DicEntry BUSINESS_CARD=new DicEntry("业务板",1,"BUSINESS"); 
		/**控制板*/
		public static final DicEntry CONTROL_CARD=new DicEntry("控制板",2,"CONTROL");
		/**分光器板*/
		public static final DicEntry SPLITTER_CARD= new DicEntry("分光器板",3,"SPLITTER");
	}

    /**
     * 机架类别
     * */
    @DicGroup(name="RACKCATEGORY")
    public static abstract class RACKCATEGORY{

        public static final DicEntry SMART_RACK=new DicEntry("智能机架",1,"SMART");

        public static final DicEntry TRADITIONAL_BOX=new DicEntry("传统机架",2,"TRADITIONAL");
    }

	/**
	 * 物理状态
	 * */
	@DicGroup(name="PHYSICALSTATUS")
	public static abstract class PHYSICALSTATUS{
        /**未施工*/
        public static final DicEntry UNCONSTRUCT = new DicEntry("未施工",0,"UNCONSTRUCT");
		/**可用*/
		public static final DicEntry AVAIABLE = new DicEntry("可用",1,"AVAIABLE");
		/**故障*/
		public static final DicEntry FAULT = new DicEntry("故障",2,"FAULT");
	}
	
	/**
	 * 端口类型
	 * */
	@DicGroup(name="PORTTYPE")
	public static abstract class PORTTYPE{
		/**业务端口*/
		public static final DicEntry BUSINESS_PORT = new DicEntry("业务端口",1,"BUSINESS");
		/**电源端口*/
		public static final DicEntry POWER_PORT = new DicEntry("电源端口",2,"POWER");
		/**信号端口*/
		public static final DicEntry SIGNAL_PORT = new DicEntry("信号端口",3,"SIGNAL");
		/**分光器入端口*/
        public static final DicEntry SPLITTER_IN_PORT = new DicEntry("分光器入端口",4,"SPLITTERINPORT");
        /**分光器出端口*/
        public static final DicEntry SPLITTER_OUT_PORT = new DicEntry("分光器出端口",5,"SPLITTEROUTPORT");
    }
	
	/**
	 * 业务状态
	 * */
	@DicGroup(name="SERVICESTATUS")
	public static abstract class SERVICESTATUS{
		/**空闲*/
		public static final DicEntry IDLE = new DicEntry("空闲",1,"IDLE");
		/**占用*/
		public static final DicEntry OCCUPIED = new DicEntry("占用",2,"OCCUPIED");
		/**保留*/
		public static final DicEntry RESERVED = new DicEntry("保留",3,"RESERVED");
	}
 	/**
	 * 连接状态
	 * */
	@DicGroup(name="CONNECTSTATUS")
	public static abstract class CONNECTSTATUS{
		/**已连接*/
		public static final DicEntry CONNECTED = new DicEntry("已连接",1,"CONNECTED");
		/**未连接*/
		public static final DicEntry UNCONNECTED= new DicEntry("未连接",2,"UNCONNECTED");
		/**已直连*/
		public static final DicEntry LINKED = new DicEntry("已直连",3,"LINKED");
		/**已预占用*/
		public static final DicEntry PREOCCUPIED = new DicEntry("预占用",4,"PREOCCUPIED");
	}
	/**
	 * 连接类型
	 * */
	@DicGroup(name="CONNECTTYPE")
	public static abstract class CONNECTTYPE{
		/**关联*/
		public static final DicEntry ASSOC = new DicEntry("关联",1,"ASSOC");
		/**跳接*/
		public static final DicEntry JUMPER = new DicEntry("跳接",2,"JUMPER");
		/**直连*/
		public static final DicEntry LINK = new DicEntry("直连",3,"LINK");
	}
	
	/**
	 * **********************************************************************************
	 * 工单类字典值
	 * **********************************************************************************
	 * */
	/**
	 * 工单状态
	 * */
	@DicGroup(name="WORKORDERSTATUS")
	public static abstract class WORKORDERSTATUS{
		/**已创建任务单*/
		public static final DicEntry WITHORDERSHEET = new DicEntry("已创建任务单",1,"WITHORDERSHEET");
		/**未创建任务单*/
		public static final DicEntry  WITHOUTORDERSHEET= new DicEntry("未创建任务单",2,"WITHOUTORDERSHEET");
	}
	/**
	 * 任务单状态
	 * */
	@DicGroup(name="WORKSHEETSTATUS")
	public static abstract class WORKSHEETSTATUS{
		/**已下发*/
		public static final DicEntry DELIVERED = new DicEntry("已下发",1,"DELIVERED");
		/**未下发*/
		public static final DicEntry NOTDELIVERED = new DicEntry("未下发",2,"NOTDELIVERED");
		/**已挂起*/
		public static final DicEntry SUSPENDED = new DicEntry("已挂起",3,"SUSPENDED");
	}
	/**
	 * 施工状态
	 * */
	@DicGroup(name="IMPLEMENTSTATUS")
	public static abstract class IMPLEMENTSTATUS{
		/**未施工*/
		public static final DicEntry NOTIMPLEMENTED = new DicEntry("未施工",1,"NOTIMPLEMENTED","FF0000");
		/**正在施工*/
		public static final DicEntry IMPLEMENTING = new DicEntry("正在施工",2,"IMPLEMENTING","FFFF00");
		/**施工完毕*/
		public static final DicEntry IMPLEMENTED = new DicEntry("施工完毕",3,"IMPLEMENTED","00FF00");
		/**施工异常*/
		public static final DicEntry IMPLEMENTEDEXCEPTION = new DicEntry("施工异常",4,"IMPLEMENTEDEXCEPTION");
		/**待核查*/
		public static final DicEntry  UNCHECKED= new DicEntry("待核查",5,"UNCHECKED");
	}
	/**
	 * 任务单操作类型
	 * */
	@DicGroup(name="WORKSHEETTYPE")
	public static abstract class WORKSHEETTYPE{
		/**创建跳接*/
		public static final DicEntry CREATEJUMPER = new DicEntry("创建跳接",1,"CREATEJUMPER");
		/**更改跳接*/
		public static final DicEntry MODIFYJUMPER = new DicEntry("更改跳接",2,"MODIFYJUMPER");
		/**删除跳接*/
		public static final DicEntry DELETEJUMPER = new DicEntry("删除跳接",3,"DELETEJUMPER");
		/**巡检设备*/
		public static final DicEntry CHECKDEVICE = new DicEntry("巡检设备",4,"CHECKDEVICE");
	}
	/**
	 * 分光器的分光比
	 * */
	@DicGroup(name="SPLITEERWAYNUMBER")
	public static abstract class SPLITEERWAYNUMBER{
		/**1:2*/
		public static final DicEntry ONE_TWO = new DicEntry("1:2",2,"ONE_TWO");
		/**1:4*/
		public static final DicEntry ONE_FOUR = new DicEntry("1:4",4,"ONE_FOUR");
		/**1:8*/
		public static final DicEntry ONE_EIGHT = new DicEntry("1:8",8,"ONE_EIGHT");
		/**1:16*/
		public static final DicEntry ONE_SIXTEEN = new DicEntry("1:16",16,"ONE_SIXTEEN");
		/**1:32*/
		public static final DicEntry ONE_THIRTYTWO = new DicEntry("1:32",32,"ONE_THIRTYTWO");
	}
	/**
	 * 光缆级别
	 * */
	@DicGroup(name="OPTICALCABLELEVEL")
	public static abstract class OPTICALCABLELEVEL{
		public static final DicEntry C1= new DicEntry("C1",1,"C1");
		public static final DicEntry C2= new DicEntry("C2",2,"C2");
		public static final DicEntry TRUNK= new DicEntry("骨干层",3,"TRUNK");
		public static final DicEntry CONVERGE= new DicEntry("汇聚层",4,"CONVERGE");
		public static final DicEntry ACCESSMAINLINE= new DicEntry("接入主配线层",5,"ACCESSMAINLINE");
		public static final DicEntry ACCESSAUXILIARLINE= new DicEntry("接入辅配线层",6,"ACCESSAUXILIARLINE");
		public static final DicEntry ACCESSLEADIN= new DicEntry("接入引入层",7,"ACCESSLEADIN");
	}
	
	/**
	 * 光缆类型
	 * */
	public static abstract class OPTICALCABLETYPE{
		public static final DicEntry MAINLINE= new DicEntry("主干光缆",1,"TRUNK");
		public static final DicEntry AUXILIARLINE= new DicEntry("配线光缆",2,"AUXILIARLINE");
		public static final DicEntry RELAY= new DicEntry("中继光缆",3,"RELAY");
		public static final DicEntry CONNECT= new DicEntry("联络光缆",4,"CONNECT");
		public static final DicEntry STATION= new DicEntry("局内光缆",5,"STATION");
		public static final DicEntry ACCESS= new DicEntry("接入光缆",6,"ACCESS");
		
	}
	/**
	 * 纤芯类别
	 * */
	public static abstract class FIBERCATEGORY{
		public static final DicEntry G6252= new DicEntry("G.652性能最佳光纤",1,"G6252");
		public static final DicEntry G6253= new DicEntry("G.653色散最佳光纤",2,"G6253");
		public static final DicEntry G6254= new DicEntry("G.654损耗最佳光纤",3,"G6254");
		public static final DicEntry G6255= new DicEntry("G.655损耗最佳光纤",4,"G6255");
		public static final DicEntry GSSPT= new DicEntry("色散平坦光纤",5,"GSSPT");
		public static final DicEntry GUNKNOW= new DicEntry("未知",6,"GUNKNOW");
	}
	
	/**
	 * 光缆模式
	 * */
	public static abstract class OPTICALCABLEMODE{
		public static final DicEntry SINGLEMODE= new DicEntry("单模",1,"SINGLEMODE");
		public static final DicEntry MUTILMODE= new DicEntry("多模",2,"MUTILMODE");
	}
	
	/**
	 * 线芯关联状态
	 * */
	public static abstract class FIBERASSOCIATIONSTATUS{
		public static final DicEntry UNASSOCIATED= new DicEntry("未关联",1,"UNASSOCIATED");
		public static final DicEntry AASSOCIATED= new DicEntry("A端关联",2,"AASSOCIATED");
		public static final DicEntry ZASSOCIATED= new DicEntry("Z端关联",3,"ZASSOCIATED");
		public static final DicEntry DOUBLEASSOCIATED= new DicEntry("两端关联",4,"DOUBLEASSOCIATED");
	}

    public static class OPTICALLINK_CONSTRUCTSTATUS {
        public static final DicEntry NO_CONSTURCT= new DicEntry("未施工",1,"NO_CONSTURCT");
        public static final DicEntry CONSTRUCT_COMPLETE= new DicEntry("施工完毕",2,"CONSTRUCT_COMPLETE");
    }

    public static class OPTICALLINK_VALIDATESTATUS {
        public static final DicEntry NO_VALIDATE= new DicEntry("未校验",1,"NO_VALIDATE");
        public static final DicEntry VALIDATE_SUCCESS = new DicEntry("校验成功",2,"VALIDATE_SUCCESS");
        public static final DicEntry VALIDATE_FAILED = new DicEntry("校验失败",2,"VALIDATE_FAILED");
    }

    public static class OPTICALLINK_INPUTTYPE {
        public static final DicEntry EARLY_CONFIG= new DicEntry("预配置光路",1,"EARLY_CONFIG");
        public static final DicEntry INPUT_TXT = new DicEntry("录入光路",2,"INPUT_TXT");
    }

    public static class ENTITYTYPE_DEFAULT_TYPE {

        public static final DicEntry DEFAULT_TYPE_NONE= new DicEntry("无",1,"DEFAULT_TYPE_NONE");
        public static final DicEntry DEFAULT_TYPE_ODF = new DicEntry("ODF",2,"DEFAULT_TYPE_ODF");
        public static final DicEntry DEFAULT_TYPE_OCC = new DicEntry("交接箱",3,"DEFAULT_TYPE_OCC");
        public static final DicEntry DEFAULT_TYPE_GF = new DicEntry("光分纤盒",4,"DEFAULT_TYPE_GF");
        public static final DicEntry DEFAULT_TYPE_DEFAULT= new DicEntry("默认",5,"DEFAULT_TYPE_DEFAULT");
    }

    @DicGroup(name="ALARM_SEVERITY_ALIAS")
    public static final class ALARM_SEVERITY_ALIAS {
        public static final DicEntry ALARM_SEVERITY_CRITICAL = new DicEntry("严重告警",0,"CRITICAL");
        public static final DicEntry ALARM_SEVERITY_MAJOR = new DicEntry("主要告警",1,"MAJOR");
        public static final DicEntry ALARM_SEVERITY_MINOR = new DicEntry("次要告警",2,"MINOR");
        public static final DicEntry ALARM_SEVERITY_WARNING = new DicEntry("一般告警",3,"NORMAL");
    }
    
    @DicGroup(name="DEVICE_TYPE_ALIAS")
    public static final class DEVICE_TYPE_ALIAS {
        public static final DicEntry DEVICE_TYPE_MSAP = new DicEntry("MSAP",0,"MSAP");
        public static final DicEntry DEVICE_TYPE_SDH = new DicEntry("SDH",1,"SDH");
        public static final DicEntry DEVICE_TYPE_EMS = new DicEntry("EMS",2,"EMS");
        public static final DicEntry DEVICE_TYPE_PON = new DicEntry("PON",3,"PON");
    }
    
   
    @DicGroup(name="DEVICE_VENDOR_ALIAS")
    public static final class DEVICE_VENDOR_ALIAS {
        public static final DicEntry DEVICE_VENDOR_ZHIZHEN = new DicEntry("ZHIZHEN",0,"ZHIZHEN");
        public static final DicEntry DEVICE_VENDOR_RAISCOM = new DicEntry("RAISCOM",1,"RAISCOM");
        public static final DicEntry DEVICE_VENDOR_ZHENGYOU = new DicEntry("ZHENGYOU",2,"ZHENGYOU");
        public static final DicEntry DEVICE_VENDOR_GAOKE = new DicEntry("GAOKE",3,"GAOKE");
        public static final DicEntry DEVICE_VENDOR_PBOSS = new DicEntry("PBOSS",2,"PBOSS");
        public static final DicEntry DEVICE_VENDOR_HUAHUAN = new DicEntry("HUAHUAN",3,"HUAHUAN");
    }
}

