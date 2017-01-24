package com.alcatelsbell.nms.common;

import com.alcatelsbell.nms.common.annotation.DicGroup;
import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * User: Ronnie
 * Date: 12-5-9
 * Time: 上午10:23
 */
public class DictionaryUtil {
//    private static Vector<Class> loadedClasses = new Vector<Class>();
//    public static void load(Class cls) {
//        loadedClasses.add(cls);
//    }
    public static HashMap getXMLDicEntrys(String groupName) {

            HashMap<Integer,String[]> map = new HashMap<Integer, String[]>();

            List<DicEntry> dicEntries = XMLLoadDictionary.getInstance().getDicEntries(groupName);
            if (dicEntries != null) {
                for (int i = 0; i < dicEntries.size(); i++) {
                    DicEntry dicEntry = dicEntries.get(i);
                    if (dicEntry.value >= 0) {
                        map.put(dicEntry.value,new String[]{dicEntry.desc,dicEntry.color,dicEntry.code});
                    } else {
                        map.put(-1-i,new String[]{dicEntry.desc,dicEntry.color,dicEntry.code});
                    }
                }
            }
            return map;

    }
    public static HashMap getDicEntrys(Class dicClass,String groupName) {
        if (dicClass.equals(DicGroupMapping.class))
             return getXMLDicEntrys(groupName);

        Class[] classes = dicClass.getDeclaredClasses();
        HashMap<Integer,String[]> map = new HashMap<Integer, String[]>();
        if (classes != null) {
            for (Class cls1 : classes) {
                DicGroup dg = (DicGroup)cls1.getAnnotation(DicGroup.class);
                if ( (dg != null && dg.name().equals(groupName)) || cls1.getSimpleName().equals(groupName)) {
                    Field[] fields = cls1.getFields();
                    for (Field field : fields) {
                        Object v = null;
                        try {
                            v = field.get(dicClass);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        if (v != null && v instanceof DicEntry) {


                            String desc = ((DicEntry) v).desc;
                            String color = ((DicEntry) v).color;
                            String code = ((DicEntry) v).code;
                            int idx = ((DicEntry) v).value;
                            map.put(idx,new String[]{desc,color,code});

                        }

                    }
                }
            }
        }

        return map;
    }

    private static DicEntry getDicEntry(Class dicGroupCls,int value) {

        Field[] fields = dicGroupCls.getFields();
        for (Field field : fields) {
            Object v = null;
            try {
                v = field.get(dicGroupCls);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (v != null && v instanceof DicEntry) {
                if (((DicEntry) v).value == value) {
                   return(DicEntry) v;
                }
            }

        }
        return null;

    }

    public static String getDicCode(Class dicGroupClass,int value) {
        DicEntry entry = getDicEntry(dicGroupClass,value);
        return entry == null ? null : entry.code ;
    }


    public static DicEntry getDicEntry(Class dicClass,String groupName,int value) {
        Class[] classes = dicClass.getDeclaredClasses();
        if (classes != null) {
            for (Class cls1 : classes) {
                DicGroup dg = (DicGroup)cls1.getAnnotation(DicGroup.class);
                if ( (dg != null && dg.name().equals(groupName)) || cls1.getSimpleName().equals(groupName)) {
                    DicEntry entry = getDicEntry(cls1,value);
                    if (entry != null)
                        return entry;
                }
            }
        }

        return null;
    }

    /**
     *
     * @param dicClass
     * @param groupName
     * @param value
     * @return
     */
    public static String getDicDesc(Class dicClass,String groupName,int value) {
        DicEntry entry = getDicEntry(dicClass,groupName,value);
        return entry != null ? entry.desc  : null;
    }

    /**
     *
     * @param cls
     * @param fieldName      The field must be specified by the annotation @DicGroupMapping, if not will return null.
     * @param value
     * @return
     * @throws NoSuchFieldException
     */
    public static String getFieldDesc(Class cls,String fieldName,int value) throws NoSuchFieldException {

        Field field = cls.getDeclaredField(fieldName);
        if (field != null) {
            DicGroupMapping dicGroupMapping = field.getAnnotation(DicGroupMapping.class);
            if (dicGroupMapping != null) {
                Class dicClass = dicGroupMapping.definitionClass();
                String groupName = dicGroupMapping.groupName();
                return getDicDesc(dicClass,groupName,value);
            }
        }

        return null;
    }
    
    /**
     * 根据描述获取值
     * @throws NoSuchFieldException 
     * @throws SecurityException 
     * */
    public static int getFieldValue(Class cls,String fieldName,String desc) throws SecurityException, NoSuchFieldException{
    	Field field = cls.getDeclaredField(fieldName);
    	if(field !=null){
    		DicGroupMapping dicGroupMapping = field.getAnnotation(DicGroupMapping.class);
    		if (dicGroupMapping != null) {
                Class dicClass = dicGroupMapping.definitionClass();
                String groupName = dicGroupMapping.groupName();
                return getDicValue(dicClass,groupName,desc);
            }else{
            	return Integer.MIN_VALUE;
            }
    	}
    	return Integer.MAX_VALUE;
    }

    /**
	 * @param dicClass
	 * @param groupName
	 * @param desc
	 * @return
	 */
	public static int getDicValue(Class dicClass, String groupName,String desc) {
		DicEntry entry = getDicEntry(dicClass,groupName,desc);
        return entry != null ? entry.value  : Integer.MAX_VALUE;
	}
	
	/**
	 * 
	 * @param dicClass
	 * @param groupName
	 * @param value
	 * @return
	 */
	public static DicEntry getDicEntry(Class dicClass,String groupName,String desc) {
        Class[] classes = dicClass.getDeclaredClasses();
        if (classes != null) {
            for (Class cls1 : classes) {
                DicGroup dg = (DicGroup)cls1.getAnnotation(DicGroup.class);
                if ( (dg != null && dg.name().equals(groupName)) || cls1.getSimpleName().equals(groupName)) {
                    DicEntry entry = getDicEntry(cls1,desc);
                    if (entry != null)
                        return entry;
                }
            }
        }

        return null;
    }
	
	/**
	 * @param cls1
	 * @param desc
	 * @return
	 */
	private static DicEntry getDicEntry(Class<?> dicGroupCls, String desc) {
		Field[] fields = dicGroupCls.getFields();
        for (Field field : fields) {
            Object v = null;
            try {
                v = field.get(dicGroupCls);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (v != null && v instanceof DicEntry) {
                if (((DicEntry) v).desc.equalsIgnoreCase(desc)) {
                   return(DicEntry) v;
                }
            }

        }
        return null;
	}

	public static void main(String[] args) {
        DictionaryUtil.getDicEntrys(OdnDictionary.class,"SIDEDCATEGORY");
    }

}
