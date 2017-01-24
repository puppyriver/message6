package com.alcatelsbell.nms.common.crud.helper;

import com.alcatelsbell.nms.common.Detect;
import com.alcatelsbell.nms.common.crud.BFieldDesc;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.db.components.client.JpaClient;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.odn.Connect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//import org.junit.Test;

/**
 * @author Aaron
 * Date 2012-06-26
 * Description:This class is used for parsing BField of the Object
 * */
public class BFieldParser {
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void parseBObject(BObject obj) throws ClassNotFoundException, Exception{
		try {
		Class cls = obj.getClass();
		Field[] all_fields=cls.getDeclaredFields();
		Map field_bFieldDesc_Map = new HashMap();
		for(Field field : all_fields){
			if(field.getAnnotation(BField.class)==null)
				continue;
			field_bFieldDesc_Map.put(field,new BFieldDesc(field));
		}
		Set<Map.Entry<Field, BFieldDesc>> fieldEntrySet=field_bFieldDesc_Map.entrySet();
		for(Map.Entry<Field, BFieldDesc> entry : fieldEntrySet){
			Field field = entry.getKey();
			BFieldDesc bfdesc = entry.getValue();
			parseBField(obj,field,bfdesc);
		}
		} catch (Exception e) {
    		e.printStackTrace();
            throw e;
    	}
	}

	@SuppressWarnings("rawtypes")
	private static void parseBField(BObject obj, Field field, BFieldDesc bfdesc) throws ClassNotFoundException, Exception {
		boolean oldAccessible= field.isAccessible();
		field.setAccessible(true);
		Object dn= field.get(obj);
		field.setAccessible(oldAccessible);
		
		String dnReferenceEntityName = bfdesc.getDnReferenceEntityName();
		String dnReferenceEntityField = bfdesc.getDnReferenceEntityField(); 
		String dnReferenceTransietField = bfdesc.getDnReferenceTransietField();
		
		if(Detect.notEmpty(dnReferenceEntityName)&&dn!=null){
            String sqlstr="select c from "+dnReferenceEntityName+" as c where c."+bfdesc.getDnField()+" = '"+((dn instanceof Long)?Long.toString((Long)dn):dn.toString())+"'";
			BObject refEntity = (BObject)JpaClient.getInstance().findOneObject(sqlstr);
//			BObject refEntity = (BObject)JpaClient.getInstance().findObjectByDN(Class.forName(dnReferenceEntityName), (String)dn);
			if(refEntity!=null){
				if(Detect.notEmpty(dnReferenceEntityField)){
					Field refField=null;
					Object refField_Value=null;
					Class temp_target_obj=refEntity.getClass();
					
					while(true){
						try {
							refField = temp_target_obj.getDeclaredField(dnReferenceEntityField);
						} catch (SecurityException e) {
								throw e;
						} catch (NoSuchFieldException e) {
								temp_target_obj=temp_target_obj.getSuperclass();
								if(temp_target_obj == Object.class)
									break;
								continue;
						}
						break;
					}	
					
					if(refField!=null){
						oldAccessible= refField.isAccessible();
						refField.setAccessible(true);
						refField_Value= refField.get(refEntity);
						refField.setAccessible(oldAccessible);
					}
									
					if(Detect.notEmpty(dnReferenceTransietField)||refField_Value!=null){
						Field transientField = obj.getClass().getDeclaredField(dnReferenceTransietField);
						oldAccessible= transientField.isAccessible();
						transientField.setAccessible(true);
						transientField.set(obj, refField_Value);
						refField.setAccessible(oldAccessible);
					}	
				}
			}
		}
	}
	
	//@Test
	@Deprecated
	public void test_parseBObject() throws Exception{
		Connect conn = (Connect) JpaClient.getInstance().findObjectByDN(Connect.class, "f1027137-97a1-4083-a999-71e988b6c18b");
		parseBObject(conn);
	}
}
