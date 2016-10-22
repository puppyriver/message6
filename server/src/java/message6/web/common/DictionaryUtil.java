package message6.web.common;

import com.alcatelsbell.nms.common.DicEntry;
import com.alcatelsbell.nms.common.XMLLoadDictionary;
import com.alcatelsbell.nms.common.annotation.DicGroup;
import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import message6.web.common.annotation.DicItem;



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

    @Deprecated
    public static HashMap getDicEntrysOld(Class dicClass,String groupName) {
        Class[] classes = dicClass.getDeclaredClasses();
        HashMap<Integer, String[]> map = new HashMap<Integer, String[]>();
        if (classes != null) {
            for (Class cls1 : classes) {
                DicGroup dg = (DicGroup) cls1.getAnnotation(DicGroup.class);
                if ((dg != null && dg.name().equals(groupName)) || cls1.getSimpleName().equals(groupName)) {
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
                            map.put(idx, new String[]{desc, color, code});

                        }

                    }
                }
            }
        }

        return map;
    }

    public static HashMap getDicEntrys(Class dicClass,String groupName) {
        if (dicClass.equals(DicGroupMapping.class))
             return getXMLDicEntrys(groupName);

        if (groupName != null && groupName.trim().length() > 0) {
            return getDicEntrysOld(dicClass, groupName);
        }

        else  {

            HashMap<Integer, String[]> map = new HashMap<Integer, String[]>();
            if (dicClass != null) {


                    Field[] fields = dicClass.getFields();
                    for (Field field : fields) {
                        Object v = null;
                        try {
                            v = field.get(dicClass);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        DicItem dicItem = field.getAnnotation(DicItem.class);
                        if (dicItem != null)
                        {


                            String desc = (dicItem).desc();
                            String color = (dicItem).color();
                            String code = (dicItem).code();
                            int idx = Integer.parseInt(String.valueOf( v));
                            map.put(idx, new String[]{desc, color, code});

                        }

                    }


            }

            return map;
        }
    }



//    public static String getDicCode(Class dicGroupClass,int value) {
//        D_Dictionary entry = getDicEntry(dicGroupClass,value);
//        return entry == null ? null : entry.code ;
//    }
//
//
//    public static D_Dictionary getDicEntry(Class dicGroupCls, int value) {
//        Field[] fields = dicGroupCls.getFields();
//        for (Field field : fields) {
//            Object v = null;
//            try {
//                v = field.get(dicGroupCls);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//            DicItem dicItem = field.getAnnotation(DicItem.class);
//            if (value == (dicItem.value())) {
//                return new D_Dictionary(dicItem.desc(),Integer.parseInt(String.valueOf(v)),dicItem.code(),dicItem.color(),dicGroupCls.getName());
//            }
//
//        }
//        return null;
//    }
//
//    /**
//     *
//     * @param dicClass
//     * @param
//     * @param value
//     * @return
//     */
//    public static String getDicDesc(Class dicClass ,int value) {
//        D_Dictionary entry = getDicEntry(dicClass,value);
//        return entry != null ? entry.desc  : null;
//    }
//
//    /**
//     *
//     * @param cls
//     * @param fieldName      The field must be specified by the annotation @DicGroupMapping, if not will return null.
//     * @param value
//     * @return
//     * @throws NoSuchFieldException
//     */
//    public static String getFieldDesc(Class cls,String fieldName,int value) throws NoSuchFieldException {
//
//        Field field = cls.getDeclaredField(fieldName);
//        if (field != null) {
//            DicGroupMapping dicGroupMapping = field.getAnnotation(DicGroupMapping.class);
//            if (dicGroupMapping != null) {
//                Class dicClass = dicGroupMapping.definitionClass();
//                String groupName = dicGroupMapping.groupName();
//                return getDicDesc(dicClass,groupName,value);
//            }
//        }
//
//        return null;
//    }
//
//    /**
//     * 根据描述获取值
//     * @throws NoSuchFieldException
//     * @throws SecurityException
//     * */
//    public static int getFieldValue(Class cls,String fieldName,String desc) throws SecurityException, NoSuchFieldException{
//    	Field field = cls.getDeclaredField(fieldName);
//    	if(field !=null){
//    		DicGroupMapping dicGroupMapping = field.getAnnotation(DicGroupMapping.class);
//    		if (dicGroupMapping != null) {
//                Class dicClass = dicGroupMapping.definitionClass();
//                String groupName = dicGroupMapping.groupName();
//                return getDicValue(dicClass,groupName,desc);
//            }else{
//            	return Integer.MIN_VALUE;
//            }
//    	}
//    	return Integer.MAX_VALUE;
//    }

    /**
	 * @param dicClass
	 * @param
	 * @param desc
	 * @return
	 */
//	public static int getDicValue(Class dicClass, String desc) {
//        D_Dictionary entry = getDicEntry(dicClass,desc);
//        return entry != null ? entry.value  : Integer.MAX_VALUE;
//	}
//
//
//	/**
//	 * @param
//	 * @param desc
//	 * @return
//	 */
//	private static D_Dictionary getDicEntry(Class<?> dicGroupCls, String desc) {
//		Field[] fields = dicGroupCls.getFields();
//        for (Field field : fields) {
//            Object v = null;
//            try {
//                v = field.get(dicGroupCls);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//            DicItem dicItem = field.getAnnotation(DicItem.class);
//            if (dicItem != null && desc.equalsIgnoreCase(dicItem.desc())) {
//                return new D_Dictionary(dicItem.desc(),Integer.parseInt(String.valueOf(v)),dicItem.code(),dicItem.color(),dicGroupCls.getName());
//            }
//
//        }
//        return null;
//	}

    private static HashMap<Class,HashMap<Object,String>> codeMap = new HashMap<>();
    public static String  getCode(Class cls,Object value) {

        HashMap<Object, String> map = codeMap.get(cls);
        if (map == null) {
            synchronized (codeMap) {
                map = codeMap.get(cls);
                if (map == null) {
                    map = new HashMap<>();
                    Field[] fields = cls.getDeclaredFields();

                    for (Field field : fields) {
                        DicItem annotation = field.getAnnotation(DicItem.class);
                        if (annotation != null) {
                            String code = annotation.code();
                            try {
                                map.put(field.get(cls),code);
                            } catch (IllegalAccessException e) {
                                //logger.error(e, e);
                            }
                        }
                    }
                    codeMap.put(cls,map);
                }
            }
        }
        return map.get(value);
    }

	public static void main(String[] args) {
     //  DictionaryUtil.getDicValue(Message6Dictionary.Boolean.class,"是");
    }

}
