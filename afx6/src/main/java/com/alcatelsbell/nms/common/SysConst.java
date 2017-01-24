package com.alcatelsbell.nms.common;
  
/**                                                      r
 * User: Ronnie.Chen
 * Date: 11-5-16
 * Time:
 */
public class SysConst {
    public static final int NULL_NUMERICAL_FIELD = -1;
    public static final int NULL_INT_FIELD = 0x80000000;

    public static final String OBJECT_NAME_JMS_CONNECTIONFACTORY = "nms.object.jmsConnectionFactory";
    public static final String SERVICE_NAME_JPA = "nms.db";
    public static final String SERVICE_NAME_ODN = "nms.odn";
    public static final String SERVICE_NAME_ODN_ENTITY = "nms.odn.entity";
    public static final String SERVICE_NAME_WORKORDER = "nms.workorder";
    public static final String SERVICE_NAME_EVENT = "nms.event";
    public static final String SERVICE_NAME_LOGIN = "nms.login";
    public static final String SERVICE_NAME_DOMAIN = "nms.domain";
    public static final String SERVICE_NAME_PRODUCT = "nms.product";
    public static final String SERVICE_NAME_ALARM = "nms.alarm";
    public static final String SERVICE_NAME_SECURITY = "nms.security";
    public static final String SERVICE_NAME_REPORT = "nms.report";
    public static final String SERVICE_NAME_FAULTDISBILL = "nms.faultbill";
    public static final String SERVICE_NAME_CUSTOMER = "nms.customer";
    public static final String SERVICE_NAME_MEGROUP = "nms.megroup";
    public static final String SERVICE_NAME_TOPO = "nms.topo";
    public static final String SERVICE_NAME_INTERFACE = "nms.interface";
    public static final String SERVICE_NAME_ACCOUNT = "nms.account";
    public static final String SERVICE_NAME_PMTHRESHOLDRULE = "nms.pmthresholdrule"; ///全局阀值设置PmThresholdRule.Service
    public static final String SERVICE_NAME_PMTHRESHOLDVALUE = "nms.pmthresholdvalue"; ///全局阀值设置PmThresholdValue.Service
    public static final String SERVICE_NAME_PHYSICALTERMINATIONPOINT ="nms.physicalterminationpoint";///单个设备阀值设置
    public static final String SERVICE_NAME_PROCESSMEASSIGN="nms.processmeassign";///进程监控管理Processmeassign.Service
    public static final String SERVICE_NAME_PMANAGEDELEMENT="nms.pmanagedelement";///网元信息管理Pmanagedelement.Service
    public static final String SERVICE_NAME_DATA = "nms.data";
    public static final String SERVICE_NAME_PERFORMANCE = "nms.performance";
    public static final String SERVICE_NAME_MESSAGE = "nms.message";
    public static final String SERVICE_NAME_GROUPSEVERITYPOLICY = "nms.groupseverity";
    public static final String SERVICE_NAME_PROJECT = "nms.project";
    public static final String SERVICE_NAME_MAINTENANCE = "nms.maintenance";
    public static final String SERVICE_NAME_REGULARDATE = "nms.regulardate";
    public static final String SERVICE_NAME_EXCELOPERATE = "nms.exceloperate";
    public static final String SERVICE_NAME_RPRODUCT="smcc.rproduct";
    public static final String SERVICE_NAME_RPRODUCTALARM="smcc.rproductalarm";
    
    
    

    public static final String SERVICE_NAME_RELOAD = "pon.reload";

    public static final String TOPIC_NAME_ALARM_EVENT = "NmsTopic_AlarmEvent";
  //  public static final String TOPIC_NAME_ALARM = "Alarm_Topic";     // Should be never used!!
    public static final String TOPIC_NAME_NMS_ALARM = "NmsAlarm_Topic";
    public static final String TOPIC_NAME_NMS_PRODUCTALARM = "NmsProductAlarm_Topic";
//    public static final String TOPIC_NAME_INTERFACE_COMMAND = "NmsTopic_InterfaceCmd";
    public static final String TOPIC_NAME_EMOS_SENDALARM = "NmsTopic_EMOS_NEW";
//    public static final String TOPIC_NAME_EMOS_CLEARALARM = "NmsTopic_AlarmClear";
    public static final String TOPIC_NAME_ESB = "NmsTopic_ESB";
    public static final String TOPIC_NAME_ESB_BLOB = "NmsTopic_ESB_BLOB";
    public static final String TOPIC_NAME_MESSAGE = "NmsTopic_Message";
    public static final String TOPIC_NAME_NMS_GENERAL = "NmsGeneral_Topic";
//    public static final String TOPIC_NAME_INTERNAL = "Internal_Topic";

    public static final String QUEUE_NAME_ALARM_EVENT = "Queue_AlarmEvent";

    public static final int REGION_TYPE_PROVINCE = 1;
    public static final int REGION_TYPE_CITY = 2;
    public static final int REGION_TYPE_DISTRICT = 3;

    public static final int PORTTYPE_BUSINESS_PORT = 1;//端口类型:业务端口
    public static final int PORTTYPE_POWER_PORT = 2;//端口类型:电源端口
    public static final int PORTTYPE_SIGNAL_PORT = 3;//端口类型:信号端口
    public static final int PORTTYPE_SPLITTER_IN_PORT = 4;//端口类型:分光器入端口
    public static final int PORTTYPE_SPLITTER_OUT_PORT = 5;//端口类型:分光器出端口
    
    
	public static final int PORT_CONNECTED = 1;//端口状态：已连接
	public static final int PORT_UNCONNECTED = 2;//端口状态：未连接
	public static final int PORT_LINKED = 3;//端口状态：已直连
    
    public static final int REGION_ORDINARY = 0;//普通region
    public static final int REGION_ROOT = 1;//根region

     public static final  String[] ALARM_SEVERITY_VALUES = {"严重","主要","次要","警告","一般","清除"};
    
    public static final int ALARM_SEVERITY_CRITICAL = 0; // 严重  一级告警
    public static final int ALARM_SEVERITY_MAJOR = 1; // 主要     二级告警
    public static final int ALARM_SEVERITY_MINOR = 2; // 次要
    public static final int SEND_VERSION_HAS = 0; // 已派单
    public static final int SEND_VERSION_NOT = 1; // 未派单
    public static final int SEND_VERSION_ALL = 2; // 全部
    public static final int ALARM_SEVERITY_WARNING = 3; // 一般
    public static final int ALARM_SEVERITY_INTERMEDIATE = 4; // 一般
    public static final int ALARM_SEVERITY_CLEAR = 5; // 清除
    public static final int ALARM_SEVERITY_INFO = 6; // 信息
    public static final int ALARM_SEVERITY_UNKNOWN_SEVERITY = 7; // 信息
    public static final int PARAM_ID=5;

    //客户保障响应等级
	public static final Integer PROVINCE = 1; //省级
	public static final Integer PREFECTURE = 0; //地市级2
    
    public final static byte ALARM_OBJECTTYPE_EMS = 0; //
    public final static byte ALARM_OBJECTTYPE_NE = 1; // 网元
    public final static byte ALARM_OBJECTTYPE_SUBNETWORK = 2; // OT_SUBNETWORK,
    public final static byte ALARM_OBJECTTYPE_SUBNETWORK_CONNECTION = 3; // OT_SUBNETWORK_CONNECTION
    public final static byte ALARM_OBJECTTYPE_PTP = 4; // 端口
    public final static byte ALARM_OBJECTTYPE_CTP = 5; // OT_CTP
    public final static byte ALARM_OBJECTTYPE_TNTRAIL = 6; // OT_TNTRAIL
    public final static byte ALARM_OBJECTTYPE_TOPOLOGICAL_LINK = 7; // OT__TOPOLOGICAL_LIN
    public final static byte ALARM_OBJECTTYPE_PROTECTION_GROUP = 8; // OT_PROTECTION_GROUP
    public final static byte ALARM_OBJECTTYPE_SHELF = 9; // OT_SHELF
    public final static byte ALARM_OBJECTTYPE_SLOT = 10; // OT_SLOT
    public final static byte ALARM_OBJECTTYPE_CIRCUITPACK = 11; // OT_CIRCUITPACK
    public final static byte ALARM_OBJECTTYPE_AID = 12; // OT_AID
    public final static byte ALARM_OBJECTTYPE_PRODUCT = 13; //
    public final static byte ALARM_OBJECTTYPE_CARD = 14; //
    public final static byte ALARM_OBJECTTYPE_BUSINESS_TDM = 15; // OT_BUSINESS_TDM
    public final static byte ALARM_OBJECTTYPE_OLT = 16;
    public final static byte ALARM_OBJECTTYPE_ONU = 17;

    public final static byte ALARM_OBJECTTYPE_CTC = 16; // OT_TERMINATION_POINT_POOL
    public final static byte ALARM_OBJECTTYPE_TRAFFIC_DESCRIPTOR = 17; //OT_TRAFFIC_DESCRIPTOR;
    public final static byte ALARM_OBJECTTYPE_CROSS_CONNECT = 18; //OT_CROSS_CONNECT;
    public final static byte ALARM_OBJECTTYPE_STATION = 19; //OT_STATION;
    public final static byte ALARM_OBJECTTYPE_LOGICAL_AREA = 20; //OT_LOGICAL_AREA;
    public final static byte ALARM_OBJECTTYPE_AREA = 21; //OT_AREA;
    public final static byte ALARM_OBJECTTYPE_PM_TASK = 22; //OT_PM_TASK;
    
    public final static byte ALARM_OBJECTTYPE_UNKNOWN = 100; // OT_AID
  

    public final static int EVENT_FIELDTYPE_SDH = 0; // sdh
    public final static int EVENT_FIELDTYPE_ATM = 1; // ATM
    public final static int EVENT_FIELDTYPE_IP = 2; // ip
    public final static int EVENT_FIELDTYPE_DDN = 3; // DDN
    public final static int EVENT_FIELDTYPE_FR = 4; // FR
    public final static int EVENT_FIELDTYPE_EXCHANGE = 5;
    public final static int EVENT_FIELDTYPE_PC = 6; // protocal covert
    public final static int EVENT_FIELDTYPE_PBX = 7;
    public final static int EVENT_FIELDTYPE_PON = 8;
    public final static int EVENT_FIELDTYPE_MSAP = 10;
    public final static int EVENT_FIELDTYPE_SLA = 9;
    public final static int EVENT_FIELDTYPE_EMS = 100; //
    public final static int EVENT_FIELDTYPE_SYSTEM = 99; //

    public final static String BILL_RESULT_TYPE_DEFAULT = "等待派单";
    public final static String BILL_RESULT_TYPE_HOLD_OUTTIME = "系统抑制，超时告警";
    public final static String BILL_RESULT_TYPE_HOLD_REGULAR = "系统抑制，例行告警";
    public final static String BILL_RESULT_TYPE_HOLD_PROJECT = "系统抑制，工程告警";
    public final static String BILL_RESULT_TYPE_HOLD_STRATEGY = "系统抑制，不满足策略";
    public final static String BILL_RESULT_TYPE_HOLD_CLEAR = "系统抑制，在派单时延内清除";
    public final static String BILL_RESULT_TYPE_HOLD_MANUAL = "手工抑制";
    public final static String BILL_RESULT_TYPE_SUCCESS_SYSTEM = "派单成功，系统自动派单";
    public final static String BILL_RESULT_TYPE_SUCCESS_MANUAL = "派单成功，手工派单";
    public final static String BILL_RESULT_TYPE_FAILD_AUTO = "派单失败，系统自动派单";
    public final static String BILL_RESULT_TYPE_FAILD_MANUAL = "派单失败，手工派单";
    
    public final static String BILL_RESULT_TYPE_MANUAL = "手工派单";
    
    //日志管理
    public static final int LOGMANAGER_TYPE_SYSTEM  = 1;
    public static final int LOGMANAGER_TYPE_SECURITY  = 2;
    public static final int LOGMANAGER_TYPE_BUSINESS = 15;
    public static final int LOGMANAGER_TYPE_CUSTOMER = 20;
    public static final int LOGMANAGER_TYPE_STRATEGY = 21;
    public static final int LOGMANAGER_TYPE_SLATEMPLATE = 22;
    public static final int LOGMANAGER_TYPE_SLA = 23;
    public static final int LOGMANAGER_TYPE_REPORT = 24;
    public static final int LOGMANAGER_TYPE_SUGGEST = 25;
    public static final int LOGMANAGER_TYPE_BEREAUREPORT = 26;
    public static final int LOGMANAGER_TYPE_AUTOREPORT = 27;
    public static final int LOGMANAGER_TYPE_ALARMMANAGER = 28;
    public static final int LOGMANAGER_TYPE_SYSTEMTOOL = 29;
    


    public static final int CURRALARM_STATUS_ACTIVE = 0; // 激活
    public static final int CURRALARM_STATUS_ACKED = 1; // 已确认
    public static final int CURRALARM_STATUS_CLEARED = 2; // 已清除
    public static final int CURRALARM_STATUS_ACKEDCLEARED = 3; // 已确认清除

//    public static final int CURRALARM_ALARMSTATUS_ACTIVE = 0; // 告警激活默认状态
//    public static final int CURRALARM_ALARMSTATUS_PROCESSED = 1; //
//    public static final int CURRALARM_ALARMSTATUS_SENDING = 2; // 准备发送工单
//    public static final int CURRALARM_ALARMSTATUS_TICKET = 3; // 工单已发送
//    public static final int CURRALARM_ALARMSTATUS_TICKET_FAILED = 4; // 工单发送失败
//    public static final int CURRALARM_ALARMSTATUS_TICKET_OVERTIME = 5; // 工单超时
//    public static final int CURRALARM_ALARMSTATUS_TICKET_PROCCESSING = 6; // 工单处理中
//    public static final int CURRALARM_ALARMSTATUS_TICKET_REPLY = 7; // 工单已回复
//    public static final int CURRALARM_ALARMSTATUS_TICKET_CLOSE = 8; // 关闭
//    public static final int CURRALARM_ALARMSTATUS_SHIELD = 999; // 告警激活默认状态

    public static final int CURRALARM_ALARMSTATUS_RECEIVED = 0;
    public static final int CURRALARM_ALARMSTATUS_VALID = 1;
    public static final int CURRALARM_ALARMSTATUS_INVALID = 2;


    public static  final int PTP_DIRECTION_SINGLE_WAY = 0;
    public static  final int PTP_DIRECTION_TWO_WAY = 1;

    public static final int PTP_RATE_2M = 0;
    public static final int PTP_RATE_8M = 1;
    public static final int PTP_RATE_34M = 2;
    public static final int PTP_RATE_140M = 3;
    public static final int PTP_RATE_565M = 4;

    public static final int PTP_TYPE_ELE = 0; //电口
    public static final int PTP_TYPE_OPT = 1;   //光口
    public static final int PTP_TYPE_LOG = 2;   //逻辑端口

    public static final int SUGGEST_TYPE=1;
    public static final int REPORT_TYPE=0;
    public static final int CUSTOMER_LEVEL_A = 5;
    public static final int CUSTOMER_LEVEL_B = 4;
    public static final int CUSTOMER_LEVEL_C = 3;
    public static final int CUSTOMER_LEVEL_D = 2;

    public static final int PRODUCT_SLA_AAA = 0;
    public static final int PRODUCT_SLA_AA = 1;
    public static final int PRODUCT_SLA_A = 2;
    public static final int PRODUCT_SLA_NORMAL = 3;

    public static final int CUSTOMER_TYPE_GROUP = 0;    //集团客户    跨省客户
    public static final int CUSTOMER_TYPE_PROVINCE =1;   //省级客户
    public static final int CUSTOMER_TYPE_LOCAL = 2;     //本地客户
    
    public static final int PRODUCT_TYPE_GROUP = 0;    // 跨省     areatype
    public static final int PRODUCT_TYPE_PROVINCE =1;   //跨区
    public static final int PRODUCT_TYPE_LOCAL = 2;     //本地

    public static final String EQUIPMENT_TYPE_DEVICE = "DEVICE";
    public static final String EQUIPMENT_TYPE_SLOT = "SLOT";
    public static final String EQUIPMENT_TYPE_CARD = "CARD";
    public static final String EQUIPMENT_TYPE_PORT = "PORT";

    public static final String EQUIPMENT_FIELD_SDH = "SDH";
    public static final String EQUIPMENT_FIELD_MSAP = "MSAP";

    //数据专线,互联网专线,GPRS专线,语音专线,短彩信专线
    public static final int PRODUCT_TYPE_RENTLINE = 1;   //数据专线
    public static final int PRODUCT_TYPE_INTERNET = 5;    //互联网专线
    public static final int PRODUCT_TYPE_GPRS = 3;     //GPRS专线
    public static final int PRODUCT_TYPE_VOICE = 6;      //语音专线
    public static final int PRODUCT_TYPE_SMS = 4;        //短彩信专线
    public static final int PRODUCT_TYPE_SUBDISTRICT = 2;        //短彩信专线

    /**
     * 该事件对业务的影响程度
    1：业务全阻
    2：可能业务全阻
    3：业务受影响
    4：可能业务受影响
    5：业务质量下降
    6：无影响
     */
    public static final int BUSS_IMPACT_BLOCK = 1;
    public static final int BUSS_IMPACT_PRO_BLOCK = 2;
    public static final int BUSS_IMPACT_AFFECTED = 3;
    public static final int BUSS_IMPACT_PRO_AFFECTED = 4;
    public static final int BUSS_IMPACT_QUALITY = 5;
    public static final int BUSS_IMPACT_NONE = 6;

    public static final String TOPO_ENTITY_TYPE_CUSTOMER = "RCUSTOMER";
    public static final String TOPO_ENTITY_TYPE_MANAGEDLEMENT = "MANAGEDELEMENT";
    public static final String TOPO_ENTITY_TYPE_TXT = "TXT";
    public static final String TOPO_ENTITY_TYPE_REGION = "REGION";
    public static final String TOPO_ENTITY_TYPE_ROOM = "ROOM";
    public static final String TOPO_ENTITY_TYPE_RACK = "RACK";
    public static final String TOPO_ENTITY_TYPE_SHELF = "SHELF";
    public static final String TOPO_ENTITY_TYPE_CARD = "CARD";
    public static final String TOPO_ENTITY_TYPE_PORT = "PORT";
    public static final String TOPO_ENTITY_TYPE_OROUTE = "OROUTE";

    public static final long TOPO_CATEGORY_CUSTOMER = 99;
    public static final long TOPO_CATEGORY_PRODUCT = 98;
    public static final long TOPO_CATEGORY_ROUTE = 97;
    public static final long TOPO_CATEGORY_TXT = 96;
    public static final long TOPO_CATEGORY_MANAGEDELEMENT = 95;
    public static final long TOPO_CATEGORY_REGION = 94;
    public static final long TOPO_CATEGORY_ROOM = 93;
    public static final long TOPO_CATEGORY_RACK = 92;

    public static final String PROPERTY_MUTI_REFERENCED = "PROPERTY_MUTI_REFERENCED";
    public static final String PROPERTY_INTERNET_REFERENCED = "PROPERTY_INTERNET_REFERENCED";
    public static final String PROPERTY_MAX_ALARM_SEVERITY = "PROPERTY_MAX_ALARM_SEVERITY";
    public static final String PROPERTY_LINE_COLOR = "PROPERTY_LINE_COLOR";
    public static final String PROPERTY_LINE_DASH = "PROPERTY_LINE_DASH";
    public static final String PROPERTY_ME_TYPE = "PROPERTY_ME_TYPE";
    public static final String PERMISSION_TYPE_REGION = "REGION";


    /**
     * 0：网元自动清除
        1：活动告警
        2：同步清除
        注：禁止在网管系统里进行手工清除
     */
    public static final int CURRALARM_CLEARTYPE_AUTO = 0;
    public static final int CURRALARM_CLEARTYPE_UNCLEAR = -1;
    public static final int CURRALARM_CLEARTYPE_ACTIVE = 1;
    public static final int CURRALARM_CLEARTYPE_SYNC = 2;
    public static final int CURRALARM_CLEARTYPE_USER = 100;

    public static final short CURRALARM_AUTO_ACKNOWLEDGED = 1;
    public static final short CURRALARM_MANU_ACKNOWLEDGED = 2;
    public static final short CURRALARM_UNACKNOWLEDGED = 0;
    
    /**
     * 统计
     */
    public static final int STATISTIC_TYPE_REGION_LEVEL = 0;  //当前客户按区域和级别统计报表
    
    public static final int STATISTIC_TYPE_REGION_LEVEL_LEVEL = 10;  //总客户数量按客户等级统计分布图
    
    public static final int STATISTIC_TYPE_REGION_LEVEL_GOLDEN = 20;  //金牌客户按照客户属性统计分布图
    
    public static final int STATISTIC_TYPE_REGION_LEVEL_SILVER = 30;  //银牌客户按照客户属性统计分布图
    
    public static final int STATISTIC_TYPE_REGION_LEVEL_COPPER = 40;  //铜牌客户按照客户属性统计分布图
    
    public static final int STATISTIC_TYPE_REGION_LEVEL_STANDARD = 50;  //标准客户按照客户属性统计分布图


    
    public static final int NMS_ALARM_SEVERIT_CRITICAL = 0;  //一级告警
    public static final int NMS_ALARM_SEVERIT_MAJOR = 1;     //二级告警
    public static final int NMS_ALARM_SEVERIT_MINOR = 2;     //三级告警
    public static final int NMS_ALARM_SEVERIT_WARNING = 3;   //四级告警
    
    public static final int NMS_PRODUCT_TYPE_STANDARD = 1;  //标准业务
    public static final int NMS_ALARM_SEVERIT_UNSTANDARD = 2;  //非标准业务
    public static final int REPORT_CIRCUIT_TYPE_2M = 0;
    public static final int REPORT_CIRCUIT_TYPE_10M = 1;
    public static final int REPORT_CIRCUIT_TYPE_LIKE_2M = 2;
    public static final int REPORT_CIRCUIT_TYPE_155M = 3;

      //日志大类  @
    public static final String LOG_CATEGORY_INTERFACE = "EMS";         //接口日志
    public static final String LOG_CATEGORY_OPERATOR = "OPERATOR";  //用户操作日志
    public static final String LOG_CATEGORY_SECURITY = "SECURITY";  //安全日志
    public static final String LOG_CATEGORY_TASK = "TASK";    //任务执行日志
    public static final String LOG_CATEGORY_SYSTEM = "SYSTEM";  //系统 日志
    public static final String LOG_CATEGORY_APP = "APP";   //模块日志
    public static final String LOG_CATEGORY_TABLET = "TABLET";   //终端日志

    public static final String LOG_MODULE_TOPO = "TOPO";   //模块日志
    public static final String LOG_MODULE_SLA = "SLA";   //模块日志
    public static final String LOG_MODULE_ALARM = "ALARM";   //模块日志
    public static final String LOG_MODULE_STRATEGY = "STRATEGY";   //模块日志

 //   public static final String LOG_MODULE_DB = "DB";   //模块日志
    public static final String LOG_SYSNAME_NMS = "NMS";   //模块日志

    public static final String LOG_SYSNAME_PON = "PON";   //模块日志



    ///////////////////////////////////////////////////////////////////
    public static final String FLOW_COMPONENT_TYPE_ACTIVITY = "ACTIVITY";
    public static final String FLOW_COMPONENT_TYPE_EVENT = "EVENT";
    public static final String FLOW_COMPONENT_TYPE_GATEWAY = "GATEWAY";
    public static final String FLOW_COMPONENT_TYPE_TRANSITION = "TRANSITION";

    public static final String FLOW_EVENT_TYPE_START = "START";
    public static final String FLOW_EVENT_TYPE_END = "END";
    
    public static final int PERFORMANCE_TIME_TYPE_DAY = 0;  //最近一天
    public static final int PERFORMANCE_TIME_TYPE_WEEK = 1;  //最近一周
    public static final int PERFORMANCE_TIME_TYPE_MOUTH = 2;  //最近一月

    public static final int NODE_STATE_START = 0;
    public static final int NODE_STATE_DIE = 1;

    public static final int EMS_STATUS_STARTUP = 0;
    public static final int EMS_STATUS_DISCONNECT = 1;
    public static final int EMS_STATUS_EXCEPTION = 2;


    public static final String CURRALARM_CHANGE_EVENT_PROPERTY_KEY_ENRICHED = "CURRALARM_CHANGE_EVENT_PROPERTY_KEY_ENRICHED";
    public static final String CURRALARM_CHANGE_EVENT_PROPERTY_KEY_SEVERITY_CHANGE = "CURRALARM_CHANGE_EVENT_PROPERTY_KEY_SEVERITY_CHANGE";
    public static final String CURRALARM_CHANGE_EVENT_PROPERTY_KEY_STATUS_CHANGE = "CURRALARM_CHANGE_EVENT_PROPERTY_KEY_STATUS_CHANGE";
    public static final String CURRALARM_CHANGE_EVENT_PROPERTY_KEY_AFFECTED_CUSTOMERS= "CURRALARM_CHANGE_EVENT_PROPERTY_KEY_AFFECTED_CUSTOMERS";
    public static final String CURRALARM_CHANGE_EVENT_PROPERTY_KEY_AFFECTED_PRODUCTS= "CURRALARM_CHANGE_EVENT_PROPERTY_KEY_AFFECTED_PRODUCTS";
    public static final String CURRALARM_CHANGE_EVENT_PROPERTY_KEY_EVENT= "CURRALARM_CHANGE_EVENT_PROPERTY_KEY_EVENT";
    public static final String CURRALARM_CHANGE_EVENT_PROPERTY_KEY_ALARM_WRAPPER= "CURRALARM_CHANGE_EVENT_PROPERTY_KEY_ALARM_WRAPPER";

    /**
     * ASB扩充字段：工单状态
     0//未申告(默认值）     未处理过派单流程的告警
     1//准备申告
     2//申告成功
     3//申告失败
     4//工单超时
     5//重复工单
     6//受理
     7//回单
     8//INMS发送数据格式不正确
     100 + x//挂起,x表示挂起前的状态，解挂时减100即可
     25//关闭
     */
    public static final int  TICKET_STATUS_DEFAULT = 0;
    public static final int TICKET_STATUS_PREPARE = 1;
    public static final int TICKET_STATUS_SEND_SUCCESS = 2;
    public static final int TICKET_STATUS_SEND_FAIL = 3;
    public static final int TICKET_STATUS_TIMEOUT = 4;
    public static final int TICKET_STATUS_DUPLICATE = 5;
    public static final int TICKET_STATUS_PROCESSING = 6;
    public static final int TICKET_STATUS_REPLY = 7;
    public static final int TICKET_STATUS_FORMAT_ERROR = 8;
    public static final int TICKET_STATUS_UNNORMAL_ALARM = 9;  //告警上报时间过早,比如6月份的告警在9月份才收到 or 时间异常的告警，比如2037年之类的
    public static final int TICKET_STATUS_DISCARD = 98; // 被过滤，或者其他类似原因在派单处理的时候未被派单
    public static final int TICKET_STATUS_NO_TICKET = 99;
    public static final int TICKET_STATUS_SUSPEND = 100;
    public static final int TICKET_STATUS_CLOSE = 25;     //已归档  已归档:sheetNo=JX-051-120912-12682
 //   public static final String SPRING_BEAN_ID_JMSTEMPLATE = "";
    
    
    public static final int EMS_SYNISOK_YES = 1;
    public static final int EMS_SYNISOK_NO = 0;
    public static final int EMS_SYNISOK_PROCESSING = 2;
    
    public static final int EMS_STATUS_DEFAULT=0;

    public static final String META_CONFIG_TYPE_SHORTCUT = "SHORTCUT";
    
    public static final String META_CONFIG_TYPE_PON_MOBILE_CLASS = "PON_MOBILE_CLASS";
    public static final String META_CONFIG_TYPE_CONFIG = "TYPE_CONFIG";
    public static final String META_CONFIG_TYPE_PON_MOBILE_FIELD = "PON_MOBILE_FIELD";
    
    public static final String META_CONFIG_TYPE_PON_MOBILE_FIELD_VALUE2_SELECT = "0";
    public static final String META_CONFIG_TYPE_PON_MOBILE_FIELD_VALUE2_SHOW = "1";
    public static final int METACONFIG_TYPE_PON_MOBILE_FIELD_VALUE2_SELECT = 0;
    public static final int METACONFIG_TYPE_PON_MOBILE_FIELD_VALUE2_SHOW = 1;

    public static final int RULE_TYPE_ALARM_CLIENT = 0;
    public static final int RULE_TYPE_ALARM_SERVER = 1;
    public static final int RULE_TYPE_ALARM_EMOS = 2;
    
    
     /**
     * 省外客户
     */
    public static final int CUSTOMER_TYPE_ZERO = 0; 
    /**
     * 省级客户
     */
    public static final int CUSTOMER_TYPE_ONE = 1; 
    /**
     * 本地客户
     */
    public static final int CUSTOMER_TYPE_TWO = 2; 
    
    /**
     * 图片管理
     */
    public static final int BINARYOBJECT_IMAGE_CATEGORY = 3;
    /**
     * 家客户
     */
    public static final int CUSTOMER_TYPE_THREE = 3;
    
    
    
    public static final int CUSTOMER_LEVEL_TWO = 2; // 标准客户
    public static final int CUSTOMER_LEVEL_THREE = 3; // 铜牌客户
    public static final int CUSTOMER_LEVEL_FOUR = 4; // 银牌客户
    public static final int CUSTOMER_LEVEL_FIVE = 5; // 金牌客户

    public static final int DEVICE_TYPE_MSAP = 0;
    public static final int DEVICE_TYPE_OLT = 1;
    public static final int DEVICE_TYPE_ONU = 2;

    public static final String MANAGEDELEMENT_TYPE_OLT = "OLT";
    public static final String MANAGEDELEMENT_TYPE_ONU = "ONU";
    public static final String MANAGEDELEMENT_TYPE_POS = "POS";
    public static final String MANAGEDELEMENT_TYPE_ODF = "ODF";
    public static final String MANAGEDELEMENT_TYPE_GJ = "GJ";
    public static final String MANAGEDELEMENT_TYPE_GF = "GF";
    public static final String MANAGEDELEMENT_TYPE_OCONNECTOR = "OCONNECTOR";

    public static final int MAX_RESULT =30;


    public static final int SMESSAGE_TYPE_BROADCAST = 0;
    public static final int SMESSAGE_TYPE_TO_OPERATOR = 1;
    public static final int SMESSAGE_TYPE_TO_ROLE = 2;

    public static final int SMESSAGE_READ_STATUS_UNREAD = 0;
    public static final int SMESSAGE_READ_STATUS_READ = 1;

    public static final String SYSTEM_PROPERTY_CURRALARM_INTERCEPTOR_FACTORY = "CurralarmInterceptorFactory";
    
    public static final String STANDARD_YES = "是";
    public static final String STANDARD_NO = "否";

    public static final Integer ALARM_VALID = 1;  //正常告警
    public static final Integer ALARM_INVALID = 2; //超时告警
    public static final Integer ALARM_REGULAR = 3; //例行告警
    public static final Integer ALARM_ENGINEER = 4; //工程告警
    
    public static final Integer ROOTCAUSE_DERIVE = 1; //衍生告警
    public static final Integer ROOTCAUSE_ROOTALARM = 2;//主告警
    public static final Integer ROOTCAUSE_SUBALARM = 3;//子告警
    
    public static final Integer RELATION_TRUE = 1;//已关联
    public static final Integer RELATION_NO = 0;  //没有关联


    public static final String MODULE_INTELLIJ_TERMINAL="智能终端";
    public static final String MODULE_DEVICE_ODF="有源ODF设备";
    public static final String MODULE_DEVICE="设备";
    public static final String MODULE_WORKORDER_MGR="工单管理";
    public static final String MODULE_OPTICAL_MGR="光路管理";
    public static final String MODULE_USER_MGR="用户管理";
}

