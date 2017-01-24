package com.alcatelsbell.nms.valueobject.alarm;

/**
 * User: Ronnie.Chen
 * Date: 11-5-24
 * Time:
 */
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.Model;
import org.hibernate.annotations.Index;
import javax.persistence.*;

import java.util.*;

@Table(name = "F_HISALARM")
@Entity
public class HisAlarm extends BObject {
    @Index(name = "HISALARM_INDEX_IDENTIFIER")
    private String identifier; // 告警标识，可映射为correlationid
    private long serial; // id
    private String node; // 网元名称
    private String nodealias; // 网元别名
    private String manager; // 管理者
    private String agent; // 代理人
    private String alertgroup; //
    private String alertkey; // 定位信息
    private int severity; // 严格
    private String summary; // 总结desc

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified; // 最后一次修改

    @Temporal(TemporalType.TIMESTAMP)
    private Date statechange; //

    @Temporal(TemporalType.TIMESTAMP)
    private Date firstoccurrence;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastoccurrence;

    @Temporal(TemporalType.TIMESTAMP)
    private Date internallast;
    private short poll;
    private short type;
    private long tally; // repeat times
    private short class_;
    private short grade; // 存放影响的客户级别
    private String location; // 空间信息
    private long owneruid;
    private long ownergid;
    private short acknowledged;
    private short flash;
    private String eventid;
    private short expiretime;
    private short processreq;
    private short suppressescl;
    private String customer;
    private String service;
    private short physicalslot; // 物理资源信息
    private short physicalport; // 物理资源信息
    private String physicalcard; // 物理资源信息
    private short tasklist;
    private String nmosserial;
    private short nmosobjinst;
    private short nmoscausetype;
    private String localnodealias;
    private String localpriobj;
    private String localsecobj;
    private String localrootobj;
    private String remotenodealias;
    private String remotepriobj;
    private String remotesecobj;
    private String remoterootobj;
    private short x733eventtype;
    private short x733probablecause;
    private Integer bussImpact;

    @Column(name = "X733SpecificProb")
    private String name;

    @Column(name = "ASB_TTID")
    private String ticketId;

    @Column(name = "ASB_AlarmState")
    private Integer ticketStatus;

    @Column(name = "ASB_CIRCUITID")
    private String circuitdn;

    private String x733corrnotif;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedat;

    private long originalseverity;
    private String servername;
    private long serverserial;
    private String url;
    private String extendedattr;

    private Integer alarmstatus;// 告警生命周期 ，参见 SysConst.CURRALARM_ALARMSTATUS

    private int status; // 告警状态，参见SysConst.CURRALARM_STATUS 和业务无关的处理状态

    @Column(name = "asb_emseventtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emseventtime;

    @Column(name = "ASB_ResolveStatus")
    private Integer resolveStatus;

    @Column(name = "ASB_EmsResolvedTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolveTime;

    @Model
    private int prevseverity;
    @Model
    private String sourceobjectname;

    @Model
    private int objecttype;

    @Model
    private String emsdn;

    @Model
    private String medn;

    @Model
    private String mename;

    @Model
    private String ptpdn;

    @Model
    private int fieldtype;

    @Column(name = "ASB_AdditionalInfo")
    private String additionalinfo;

    @Model
    private String faultCause;

    @Model
    private String memo;

    // ---------------------------
    private String std_businessCategory;// 业务大类

    private String std_address; // 联系地址

    private String std_postcode; // 客户邮编

    private String std_accountmanager; // 客户经理姓名

    private String std_accountmanagerPhone; // 客户经理联系电话号码

    private String std_contact; // 客户联系人姓名

    private String std_contactphone; // 客户联系人电话号码

    private String std_productname; // 业务标准名称

    private String std_productno; // 业务编号

    private Integer std_slalevel; // 业务保障级别

    private Integer std_groupseverity; // 集客告警级别

    private Integer std_maintenancelevel;// 集客故障响应级别

    private String std_billresult; // 派单状态

    private String std_isStandard; // 设备告警是否标准化

    private String std_alarmId; // 网管告警ID

    private String std_alarmDescription; // 告警解释

    private String std_alarmCategory; // 告警逻辑分类

    private String std_alarmSubCategory; // 告警逻辑子类

    private String std_deviceImpact; // 该事件对设备的影响

    private String std_businessImpact; // 该事件对业务的影响

    private String std_vendorName; // 厂家

    private String std_deviceCategory; // 设备类型

    private Integer relationFlag; // 关联标识（字典值）

    private String std_standardName; // 告警标准名（可选）

    private String std_originalSeverity; // 厂家告警级别

    private String std_alarmType; // 告警类型

    private String std_province; // 省

    private String std_prefecture; // 市

    private String std_county; // 县

    private String std_site; // 局站

    private String std_sourceobjecttype; // 告警对象类型

    private String std_layerRate; // 层速率

    private String std_confirmaccount; // 确认用户

    @Temporal(TemporalType.TIMESTAMP)
    private Date std_confirmtime; // 确认时间

    private String std_billno; // 工单号

    private String std_billstatus; // 工单状态

    private String std_deviceType; // 网元型号

    private Integer std_alarmFlag; // 告警标签（字典值）

    private Integer rootcause; // 根告警结果（字典值）

    private String std_alarmProperty; // 告警类型

    private Integer std_productProperty; // 业务属性

    private Integer std_duration; // 告警历时

    private Integer asb_AssociationFlag;
    private Integer asb_PossibleRC;
    private Integer asb_EnrichFlag = 0;
    
    private String std_devicePrefecture;    //设备地市
    private String std_deviceCounty;         //设备区县
    
    
    public String getStd_devicePrefecture() {
        return std_devicePrefecture;
    }

    public void setStd_devicePrefecture(String stdDevicePrefecture) {
        std_devicePrefecture = stdDevicePrefecture;
    }

    public String getStd_deviceCounty() {
        return std_deviceCounty;
    }

    public void setStd_deviceCounty(String stdDeviceCounty) {
        std_deviceCounty = stdDeviceCounty;
    }

    private String regionDn;
    public String getRegionDn() {
        return regionDn;
    }
    
    public void setRegionDn(String regionDn) {
        this.regionDn = regionDn;
    }

    public Integer getAsb_AssociationFlag() {
        return asb_AssociationFlag;
    }

    public void setAsb_AssociationFlag(Integer asb_AssociationFlag) {
        this.asb_AssociationFlag = asb_AssociationFlag;
    }

    public Integer getAsb_PossibleRC() {
        return asb_PossibleRC;
    }

    public void setAsb_PossibleRC(Integer asb_PossibleRC) {
        this.asb_PossibleRC = asb_PossibleRC;
    }

    public Integer getAsb_EnrichFlag() {
        return asb_EnrichFlag;
    }

    public void setAsb_EnrichFlag(Integer asb_EnrichFlag) {
        this.asb_EnrichFlag = asb_EnrichFlag;
    }

    public Integer getStd_duration() {
        return std_duration;
    }

    public void setStd_duration(Integer stdDuration) {
        std_duration = stdDuration;
    }

    public Integer getStd_productProperty() {
        return std_productProperty;
    }

    public void setStd_productProperty(Integer stdProductProperty) {
        std_productProperty = stdProductProperty;
    }

    public String getStd_alarmProperty() {
        return std_alarmProperty;
    }

    public void setStd_alarmProperty(String stdAlarmProperty) {
        std_alarmProperty = stdAlarmProperty;
    }

    public Integer getRootcause() {
        return rootcause;
    }

    public void setRootcause(Integer rootcause) {
        this.rootcause = rootcause;
    }

    public String getStd_businessCategory() {
        return std_businessCategory;
    }

    public void setStd_businessCategory(String stdBusinessCategory) {
        std_businessCategory = stdBusinessCategory;
    }

    public String getStd_address() {
        return std_address;
    }

    public void setStd_address(String stdAddress) {
        std_address = stdAddress;
    }

    public String getStd_postcode() {
        return std_postcode;
    }

    public void setStd_postcode(String stdPostcode) {
        std_postcode = stdPostcode;
    }

    public String getStd_accountmanager() {
        return std_accountmanager;
    }

    public void setStd_accountmanager(String stdAccountmanager) {
        std_accountmanager = stdAccountmanager;
    }

    public String getStd_accountmanagerPhone() {
        return std_accountmanagerPhone;
    }

    public void setStd_accountmanagerPhone(String stdAccountmanagerPhone) {
        std_accountmanagerPhone = stdAccountmanagerPhone;
    }

    public String getStd_contact() {
        return std_contact;
    }

    public void setStd_contact(String stdContact) {
        std_contact = stdContact;
    }

    public String getStd_contactphone() {
        return std_contactphone;
    }

    public void setStd_contactphone(String stdContactphone) {
        std_contactphone = stdContactphone;
    }

    public String getStd_productname() {
        return std_productname;
    }

    public void setStd_productname(String stdProductname) {
        std_productname = stdProductname;
    }

    public String getStd_productno() {
        return std_productno;
    }

    public void setStd_productno(String stdProductno) {
        std_productno = stdProductno;
    }

    public Integer getStd_slalevel() {
        return std_slalevel;
    }

    public void setStd_slalevel(Integer stdSlalevel) {
        std_slalevel = stdSlalevel;
    }

    public Integer getStd_groupseverity() {
        return std_groupseverity;
    }

    public void setStd_groupseverity(Integer stdGroupseverity) {
        std_groupseverity = stdGroupseverity;
    }

    public String getStd_billresult() {
        return std_billresult;
    }

    public void setStd_billresult(String stdBillresult) {
        std_billresult = stdBillresult;
    }

    public String getStd_isStandard() {
        return std_isStandard;
    }

    public void setStd_isStandard(String stdIsStandard) {
        std_isStandard = stdIsStandard;
    }


    public String getStd_alarmId() {
        return std_alarmId;
    }

    public void setStd_alarmId(String stdAlarmId) {
        std_alarmId = stdAlarmId;
    }

    public String getStd_alarmDescription() {
        return std_alarmDescription;
    }

    public void setStd_alarmDescription(String stdAlarmDescription) {
        std_alarmDescription = stdAlarmDescription;
    }

    public String getStd_alarmCategory() {
        return std_alarmCategory;
    }

    public void setStd_alarmCategory(String stdAlarmCategory) {
        std_alarmCategory = stdAlarmCategory;
    }

    public String getStd_alarmSubCategory() {
        return std_alarmSubCategory;
    }

    public void setStd_alarmSubCategory(String stdAlarmSubCategory) {
        std_alarmSubCategory = stdAlarmSubCategory;
    }

    public String getStd_deviceImpact() {
        return std_deviceImpact;
    }

    public void setStd_deviceImpact(String stdDeviceImpact) {
        std_deviceImpact = stdDeviceImpact;
    }

    public String getStd_businessImpact() {
        return std_businessImpact;
    }

    public void setStd_businessImpact(String stdBusinessImpact) {
        std_businessImpact = stdBusinessImpact;
    }

    public String getStd_vendorName() {
        return std_vendorName;
    }

    public void setStd_vendorName(String stdVendorName) {
        std_vendorName = stdVendorName;
    }

    public String getStd_deviceCategory() {
        return std_deviceCategory;
    }

    public void setStd_deviceCategory(String stdDeviceCategory) {
        std_deviceCategory = stdDeviceCategory;
    }

    public Integer getRelationFlag() {
        return relationFlag;
    }

    public void setRelationFlag(Integer relationFlag) {
        this.relationFlag = relationFlag;
    }

    public String getStd_standardName() {
        return std_standardName;
    }

    public void setStd_standardName(String stdStandardName) {
        std_standardName = stdStandardName;
    }

    public String getStd_province() {
        return std_province;
    }

    public void setStd_province(String stdProvince) {
        std_province = stdProvince;
    }

    public Integer getStd_maintenancelevel() {
        return std_maintenancelevel;
    }

    public void setStd_maintenancelevel(Integer stdMaintenancelevel) {
        std_maintenancelevel = stdMaintenancelevel;
    }

    public String getStd_originalSeverity() {
        return std_originalSeverity;
    }

    public void setStd_originalSeverity(String stdOriginalSeverity) {
        std_originalSeverity = stdOriginalSeverity;
    }

    public String getStd_alarmType() {
        return std_alarmType;
    }

    public void setStd_alarmType(String stdAlarmType) {
        std_alarmType = stdAlarmType;
    }

    public String getStd_prefecture() {
        return std_prefecture;
    }

    public void setStd_prefecture(String stdPrefecture) {
        std_prefecture = stdPrefecture;
    }

    public String getStd_county() {
        return std_county;
    }

    public void setStd_county(String stdCounty) {
        std_county = stdCounty;
    }

    public String getStd_site() {
        return std_site;
    }

    public void setStd_site(String stdSite) {
        std_site = stdSite;
    }

    public String getStd_sourceobjecttype() {
        return std_sourceobjecttype;
    }

    public void setStd_sourceobjecttype(String stdSourceobjecttype) {
        std_sourceobjecttype = stdSourceobjecttype;
    }

    public String getStd_layerRate() {
        return std_layerRate;
    }

    public void setStd_layerRate(String stdLayerRate) {
        std_layerRate = stdLayerRate;
    }

    public String getStd_confirmaccount() {
        return std_confirmaccount;
    }

    public void setStd_confirmaccount(String stdConfirmaccount) {
        std_confirmaccount = stdConfirmaccount;
    }

    public Date getStd_confirmtime() {
        return std_confirmtime;
    }

    public void setStd_confirmtime(Date stdConfirmtime) {
        std_confirmtime = stdConfirmtime;
    }

    public String getStd_billno() {
        return std_billno;
    }

    public void setStd_billno(String stdBillno) {
        std_billno = stdBillno;
    }

    public String getStd_billstatus() {
        return std_billstatus;
    }

    public void setStd_billstatus(String stdBillstatus) {
        std_billstatus = stdBillstatus;
    }

    public String getStd_deviceType() {
        return std_deviceType;
    }

    public void setStd_deviceType(String stdDeviceType) {
        std_deviceType = stdDeviceType;
    }

    public Integer getStd_alarmFlag() {
        return std_alarmFlag;
    }

    public void setStd_alarmFlag(Integer stdAlarmFlag) {
        std_alarmFlag = stdAlarmFlag;
    }

    public String getStd_rootcause() {
        return std_rootcause;
    }

    public void setStd_rootcause(String stdRootcause) {
        std_rootcause = stdRootcause;
    }

    private String std_rootcause;

    public String getFaultCause() {
        return faultCause;
    }

    public void setFaultCause(String faultCause) {
        this.faultCause = faultCause;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String toString() {
        return "CURRALARM" + ":" + this.getId() + " NAME=" + this.identifier + " DESC=" + this.summary;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getNodealias() {
        return nodealias;
    }

    public void setNodealias(String nodealias) {
        this.nodealias = nodealias;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAlertgroup() {
        return alertgroup;
    }

    public void setAlertgroup(String alertgroup) {
        this.alertgroup = alertgroup;
    }

    public String getAlertkey() {
        return alertkey;
    }

    public void setAlertkey(String alertkey) {
        this.alertkey = alertkey;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public Date getStatechange() {
        return statechange;
    }

    public void setStatechange(Date statechange) {
        this.statechange = statechange;
    }

    public Date getFirstoccurrence() {
        return firstoccurrence;
    }

    public void setFirstoccurrence(Date firstoccurrence) {
        this.firstoccurrence = firstoccurrence;
    }

    public Date getLastoccurrence() {
        return lastoccurrence;
    }

    public void setLastoccurrence(Date lastoccurrence) {
        this.lastoccurrence = lastoccurrence;
    }

    public Date getInternallast() {
        return internallast;
    }

    public void setInternallast(Date internallast) {
        this.internallast = internallast;
    }

    public short getPoll() {
        return poll;
    }

    public void setPoll(short poll) {
        this.poll = poll;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public long getTally() {
        return tally;
    }

    public void setTally(long tally) {
        this.tally = tally;
    }

    public short getClass_() {
        return class_;
    }

    public void setClass_(short class_) {
        this.class_ = class_;
    }

    public short getGrade() {
        return grade;
    }

    public void setGrade(short grade) {
        this.grade = grade;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getOwneruid() {
        return owneruid;
    }

    public void setOwneruid(long owneruid) {
        this.owneruid = owneruid;
    }

    public long getOwnergid() {
        return ownergid;
    }

    public void setOwnergid(long ownergid) {
        this.ownergid = ownergid;
    }

    public short getAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(short acknowledged) {
        this.acknowledged = acknowledged;
    }

    public short getFlash() {
        return flash;
    }

    public void setFlash(short flash) {
        this.flash = flash;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public short getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(short expiretime) {
        this.expiretime = expiretime;
    }

    public short getProcessreq() {
        return processreq;
    }

    public void setProcessreq(short processreq) {
        this.processreq = processreq;
    }

    public short getSuppressescl() {
        return suppressescl;
    }

    public void setSuppressescl(short suppressescl) {
        this.suppressescl = suppressescl;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public short getPhysicalslot() {
        return physicalslot;
    }

    public void setPhysicalslot(short physicalslot) {
        this.physicalslot = physicalslot;
    }

    public short getPhysicalport() {
        return physicalport;
    }

    public void setPhysicalport(short physicalport) {
        this.physicalport = physicalport;
    }

    public String getPhysicalcard() {
        return physicalcard;
    }

    public void setPhysicalcard(String physicalcard) {
        this.physicalcard = physicalcard;
    }

    public short getTasklist() {
        return tasklist;
    }

    public void setTasklist(short tasklist) {
        this.tasklist = tasklist;
    }

    public String getNmosserial() {
        return nmosserial;
    }

    public void setNmosserial(String nmosserial) {
        this.nmosserial = nmosserial;
    }

    public short getNmosobjinst() {
        return nmosobjinst;
    }

    public void setNmosobjinst(short nmosobjinst) {
        this.nmosobjinst = nmosobjinst;
    }

    public short getNmoscausetype() {
        return nmoscausetype;
    }

    public void setNmoscausetype(short nmoscausetype) {
        this.nmoscausetype = nmoscausetype;
    }

    public String getLocalnodealias() {
        return localnodealias;
    }

    public void setLocalnodealias(String localnodealias) {
        this.localnodealias = localnodealias;
    }

    public String getLocalpriobj() {
        return localpriobj;
    }

    public void setLocalpriobj(String localpriobj) {
        this.localpriobj = localpriobj;
    }

    public String getLocalsecobj() {
        return localsecobj;
    }

    public void setLocalsecobj(String localsecobj) {
        this.localsecobj = localsecobj;
    }

    public String getLocalrootobj() {
        return localrootobj;
    }

    public void setLocalrootobj(String localrootobj) {
        this.localrootobj = localrootobj;
    }

    public String getRemotenodealias() {
        return remotenodealias;
    }

    public void setRemotenodealias(String remotenodealias) {
        this.remotenodealias = remotenodealias;
    }

    public String getRemotepriobj() {
        return remotepriobj;
    }

    public void setRemotepriobj(String remotepriobj) {
        this.remotepriobj = remotepriobj;
    }

    public String getRemotesecobj() {
        return remotesecobj;
    }

    public void setRemotesecobj(String remotesecobj) {
        this.remotesecobj = remotesecobj;
    }

    public String getRemoterootobj() {
        return remoterootobj;
    }

    public void setRemoterootobj(String remoterootobj) {
        this.remoterootobj = remoterootobj;
    }

    public short getX733eventtype() {
        return x733eventtype;
    }

    public void setX733eventtype(short x733eventtype) {
        this.x733eventtype = x733eventtype;
    }

    public short getX733probablecause() {
        return x733probablecause;
    }

    public void setX733probablecause(short x733probablecause) {
        this.x733probablecause = x733probablecause;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getX733corrnotif() {
        return x733corrnotif;
    }

    public void setX733corrnotif(String x733corrnotif) {
        this.x733corrnotif = x733corrnotif;
    }

    public Date getDeletedat() {
        return deletedat;
    }

    public void setDeletedat(Date deletedat) {
        this.deletedat = deletedat;
    }

    public long getOriginalseverity() {
        return originalseverity;
    }

    public void setOriginalseverity(long originalseverity) {
        this.originalseverity = originalseverity;
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public long getServerserial() {
        return serverserial;
    }

    public void setServerserial(long serverserial) {
        this.serverserial = serverserial;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtendedattr() {
        return extendedattr;
    }

    public void setExtendedattr(String extendedattr) {
        this.extendedattr = extendedattr;
    }

    public Integer getAlarmstatus() {
        return alarmstatus;
    }

    public void setAlarmstatus(Integer alarmstatus) {
        this.alarmstatus = alarmstatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BObject copyShallow(BObject _alarm) {
        Curralarm rp2 = (Curralarm)_alarm;
        this.setSerial(rp2.getSerial());
        this.setNode(rp2.getNode());
        this.setNodealias(rp2.getNodealias());
        this.setAgent(rp2.getAgent());
        this.setAlertgroup(rp2.getAlertgroup());
        this.setAlertkey(rp2.getAlertkey());
        this.setSummary(rp2.getSummary());
        this.setTally(rp2.getTally());
        this.setOwneruid(rp2.getOwneruid());
        this.setOwnergid(rp2.getOwnergid());
        this.setEventid(rp2.getEventid());
        this.setCustomer(rp2.getCustomer());
        this.setService(rp2.getService());
        this.setPhysicalcard(rp2.getPhysicalcard());
        this.setNmosserial(rp2.getNmosserial());
        this.setLocalnodealias(rp2.getLocalnodealias());
        this.setLocalpriobj(rp2.getLocalpriobj());
        this.setLocalsecobj(rp2.getLocalsecobj());
        this.setLocalrootobj(rp2.getLocalrootobj());
        this.setRemotenodealias(rp2.getRemotenodealias());
        this.setRemotepriobj(rp2.getRemotepriobj());
        this.setRemotesecobj(rp2.getRemotesecobj());
        this.setRemoterootobj(rp2.getRemoterootobj());
        this.setName(rp2.getName());
        this.setX733corrnotif(rp2.getX733corrnotif());
        this.setOriginalseverity(rp2.getOriginalseverity());
        this.setServername(rp2.getServername());
        this.setServerserial(rp2.getServerserial());
        this.setUrl(rp2.getUrl());
        this.setExtendedattr(rp2.getExtendedattr());
        this.setAlarmstatus(rp2.getAlarmstatus());
        this.setLocation(rp2.getLocation());
        this.setManager(rp2.getManager());
        this.setStatus(rp2.getStatus());
        this.setIdentifier(rp2.getIdentifier());
        this.setTicketId(rp2.getTicketId());
        this.setTicketStatus(rp2.getTicketStatus());
        this.setOid(rp2.getOid());
        this.setFromWhere(rp2.getFromWhere());
        this.setDn(rp2.getDn());
        this.setTag1(rp2.getTag1());
        this.setTag2(rp2.getTag2());
        this.setTag3(rp2.getTag3());

        return this;
    }

    public Date getEmseventtime() {
        return emseventtime;
    }

    public void setEmseventtime(Date emseventtime) {
        this.emseventtime = emseventtime;
    }

    public int getPrevseverity() {
        return prevseverity;
    }

    public void setPrevseverity(int preseverity) {
        this.prevseverity = preseverity;
    }

    public String getSourceobjectname() {
        return sourceobjectname;
    }

    public void setSourceobjectname(String sourceobjectname) {
        this.sourceobjectname = sourceobjectname;
    }

    public int getFieldtype() {
        return fieldtype;
    }

    public void setFieldtype(int fieldtype) {
        this.fieldtype = fieldtype;
    }

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    public int getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(int objecttype) {
        this.objecttype = objecttype;
    }

    public String getEmsdn() {
        return emsdn;
    }

    public void setEmsdn(String emsdn) {
        this.emsdn = emsdn;
    }

    public String getMedn() {
        return medn;
    }

    public void setMedn(String medn) {
        this.medn = medn;
    }

    public String getPtpdn() {
        return ptpdn;
    }

    public void setPtpdn(String ptpdn) {
        this.ptpdn = ptpdn;
    }

    public String getMename() {
        return mename;
    }

    public void setMename(String mename) {
        this.mename = mename;
    }

    public String getCircuitdn() {
        return circuitdn;
    }

    public void setCircuitdn(String circuitdn) {
        this.circuitdn = circuitdn;
    }

    public Integer getBussImpact() {
        return bussImpact;
    }

    public void setBussImpact(Integer bussImpact) {
        this.bussImpact = bussImpact;
    }

    public Integer getResolveStatus() {
        return resolveStatus;
    }

    public void setResolveStatus(Integer resolveStatus) {
        this.resolveStatus = resolveStatus;
    }

    public Date getResolveTime() {
        return resolveTime;
    }

    public void setResolveTime(Date resolveTime) {
        this.resolveTime = resolveTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
