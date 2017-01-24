/**
 * 
 */
package com.alcatelsbell.nms.common.crud.helper;

import com.alcatelsbell.nms.common.CommonUtil;
import com.alcatelsbell.nms.common.Detect;
import com.alcatelsbell.nms.common.crud.BFieldDesc;
import com.alcatelsbell.nms.common.crud.annotation.BField;

import com.alcatelsbell.nms.db.components.client.JpaClient;
import com.alcatelsbell.nms.db.components.service.DBUtil;
import com.alcatelsbell.nms.db.components.service.JPASupport;
import com.alcatelsbell.nms.db.components.service.JPAUtil;
import com.alcatelsbell.nms.valueobject.BObject;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.*;


/**
 * @author: Aaron
 * Date: 2012-8-27
 * Time: 下午04:01:09
 */
public class BObjectBFieldParser {
	/**
	 * if context is null,the use JpaClient
	 * else use DBUtil
	 * @param context
	 * @param obj
	 * @throws RemoteException
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void queryAndParseBField(JPASupport context,BObject obj) throws RemoteException, Exception{
		Class cls = obj.getClass();
		String dn = obj.getDn();
		Field[] all_fields=cls.getDeclaredFields();
		Map field_bFieldDesc_Map = new HashMap();
		for(Field field : all_fields){
			if(field.getAnnotation(BField.class)==null)
				continue;
			field_bFieldDesc_Map.put(field,new BFieldDesc(field));
		}
		String clsName = cls.getSimpleName();
		StringBuffer selectSb = new StringBuffer().append("SELECT ").append(clsName).append(".dn as ").append(clsName).append("_dn");
		StringBuffer fromSb = new StringBuffer().append("FROM ").append(CommonUtil.getTableNameByClass(cls)).append(" as ").append(clsName);
		StringBuffer leftJoinOnSb = new StringBuffer();
		StringBuffer whereSb = new StringBuffer().append("WHERE ").append(clsName).append(".dn='").append(dn).append("'");
		Set<Map.Entry<Field, BFieldDesc>> fieldEntrySet=field_bFieldDesc_Map.entrySet();
		List<Map.Entry<Field, BFieldDesc>> targetFieldEntryList = new ArrayList();
		for(Map.Entry<Field, BFieldDesc> entry : fieldEntrySet){
			Field field = entry.getKey();
			BFieldDesc bfdesc = entry.getValue();
			
			String dnReferenceEntityName = bfdesc.getDnReferenceEntityName();
			String dnReferenceEntityField = bfdesc.getDnReferenceEntityField(); 
			//String dnReferenceTransietField = bfdesc.getDnReferenceTransietField();
			
			if(Detect.notEmpty(dnReferenceEntityName)){
				Class refCls = Class.forName(dnReferenceEntityName);
				String refClsName = refCls.getSimpleName();
				selectSb.append(",").append(refClsName).append(".").append(dnReferenceEntityField).append(" as ").append(refClsName).append("_").append(dnReferenceEntityField);
				//fromSb.append(" ,").append(refClsName).append(" ").append(refClsName1);
				leftJoinOnSb.append(" left join (").append(CommonUtil.getTableNameByClass(refCls)).append(" as ").append(refClsName).append(") on (").append(clsName).append(".").append(field.getName()).append("=").append(refClsName).append(".dn)");
				//whereSb.append(" and ").append(clsName1).append(".").append(field.getName()).append("=").append(refClsName1).append(".dn");
				targetFieldEntryList.add(entry);
			}			
		}
		
		String sqlStr = selectSb.toString()+" "+fromSb.toString()+leftJoinOnSb+" "+whereSb.toString();
		List result=null;
		if(context==null){
			result = JpaClient.getInstance().querySql(sqlStr);
		}else{
			result = DBUtil.getInstance().querySQL(context,sqlStr);	
		}
		Object[] resultObjs = (Object[]) result.get(0);
		for(int i=1;i< resultObjs.length;i++){
			Map.Entry<Field, BFieldDesc> entry = targetFieldEntryList.get(i-1);
			BFieldDesc bfdesc = entry.getValue();
			PropertyDescriptor pd = new PropertyDescriptor(bfdesc.getDnReferenceTransietField(),obj.getClass());
			Method wmethod=pd.getWriteMethod();
			wmethod.invoke(obj, resultObjs[i]);
		}
		System.out.println(obj);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void parseBFieldOfBObject(JPASupport context,BObject obj) throws ClassNotFoundException, Exception{
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
			parseBField(context,obj,field,bfdesc);
		}
		} catch (Exception e) {
    		e.printStackTrace();
            throw e;
    	}
	}

	@SuppressWarnings("rawtypes")
	private static void parseBField(JPASupport context,BObject obj, Field field, BFieldDesc bfdesc) throws ClassNotFoundException, Exception {
		boolean oldAccessible= field.isAccessible();
		field.setAccessible(true);
		Object dn= field.get(obj);
		field.setAccessible(oldAccessible);
		
		String dnReferenceEntityName = bfdesc.getDnReferenceEntityName();
		String dnReferenceEntityField = bfdesc.getDnReferenceEntityField(); 
		String dnReferenceTransietField = bfdesc.getDnReferenceTransietField();
		
		if(Detect.notEmpty(dnReferenceEntityName)){
			BObject refEntity = (BObject)JPAUtil.getInstance().findObjectByDn(context,-1L,Class.forName(dnReferenceEntityName),(String)dn);
			if(refEntity!=null && Detect.notEmpty(dnReferenceEntityField)&& Detect.notEmpty(dnReferenceTransietField)){
				if(Detect.notEmpty(dnReferenceEntityField)){
					PropertyDescriptor pdOnRefEntity = new PropertyDescriptor(dnReferenceEntityField,refEntity.getClass());
					Method getMethod = pdOnRefEntity.getReadMethod();
					Object refValue = getMethod.invoke(refEntity);
					PropertyDescriptor pdOnOrginalEntity = new PropertyDescriptor(dnReferenceTransietField,obj.getClass());
					Method setMethod = pdOnOrginalEntity.getWriteMethod();
					setMethod.invoke(obj, refValue);					
				}
			}
		}
	}
}
