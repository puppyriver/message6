package com.alcatelsbell.nms.valueobject;

import com.alcatelsbell.nms.common.DicEntry;
import com.alcatelsbell.nms.common.annotation.DicGroup;

public class CdcpDictionary {

	@DicGroup(name="EMSVENDOR")
	public static abstract class EMSVENDOR {
		public static final DicEntry VENDORHW = new DicEntry("华为",1,"Huawei");
		public static final DicEntry VENDORFH = new DicEntry("烽火",2,"Fenghuo");
		public static final DicEntry VENDORALU = new DicEntry("阿朗",3,"ALU");
        public static final DicEntry VENDORZTE = new DicEntry("中兴",4,"ZTE");

	}
	
	@DicGroup(name="EMSTYPE")
	public static abstract class EMSTYPE {
		public static final DicEntry TYPEU2000 = new DicEntry("U2000",1,"HWU2000");
		public static final DicEntry TYPEFH = new DicEntry("OTNM2000",2,"FH");
		public static final DicEntry TYPEALU = new DicEntry("ALU",3,"ALU");
        public static final DicEntry TYPEZTE = new DicEntry("ZTE",4,"ZTE");
	}
	
	@DicGroup(name="EMSSTATUS")
	public static abstract class EMSSTATUS {
		public static final DicEntry OK = new DicEntry("正常",0,"OK");
		public static final DicEntry ERROR = new DicEntry("异常",1,"ERROR");
        public static final DicEntry DISABLED = new DicEntry("禁用",2,"DISABLED");
	}
	
	@DicGroup(name="EMSISSYNCOK")
	public static abstract class EMSISSYNCOK {
		public static final DicEntry NO = new DicEntry("否",0,"NO");
		public static final DicEntry YES = new DicEntry("是",1,"YES");
	}
	
	@DicGroup(name="EMSISMONITORED")
	public static abstract class EMSISMONITORED {
		public static final DicEntry NO = new DicEntry("屏蔽",0,"NO");
		public static final DicEntry YES = new DicEntry("启用",1,"YES");
	}
	@DicGroup(name="PROTOCALTYPE")
	public static abstract class PROTOCALTYPE {
		public static final DicEntry PTN = new DicEntry("PTN",1,"PTN");
		public static final DicEntry SDH = new DicEntry("SDH",2,"SDH");
		public static final DicEntry OTN = new DicEntry("OTN",3,"OTN");
		public static final DicEntry WDM = new DicEntry("WDM",4,"WDM");
	}
}
