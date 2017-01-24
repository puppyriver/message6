package com.alcatelsbell.nms.util.protocol.snmp;


import com.alcatelsbell.nms.common.CommonUtil;
import com.alcatelsbell.nms.util.ObjectUtil;
import com.alcatelsbell.nms.util.SysProperty;
import com.alcatelsbell.nms.util.protocol.snmp.annotation.MibLeaf;
import com.alcatelsbell.nms.util.protocol.snmp.annotation.MibTableColumn;
import com.alcatelsbell.nms.util.protocol.snmp.annotation.SnmpTableEntry;
import org.snmp4j.smi.*;
import org.snmp4j.util.TableEvent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Ronnie.Chen
 * Date: 12-10-22
 * Time: 下午3:01
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class SnmpClient {
    private static SnmpClient client = new SnmpClient();
    public static SnmpClient getInstance() {
        return client;
    }
    private boolean save = SysProperty.getString("snmp.cache.save","").equalsIgnoreCase("true");
    private boolean debug = SysProperty.getString("snmp.cache.debug","").equalsIgnoreCase("true");
    private SnmpClient(){

    }

    public Object snmpGetObject(SnmpUtil util,Class cls) throws Exception {
        String key = util.getAddress()+"."+cls.getSimpleName();
        if (debug) {
            Object o = ObjectUtil.readObject(key);
            if (o != null) return o;
        }
        Object o = snmpGetObject(util, cls, 1);
        if (save)
            ObjectUtil.saveObject(key,o);
        return o;
    }
    public Object snmpGetObject(SnmpUtil util,Class cls, int depth) throws Exception {
        Object object = cls.newInstance();

        List<OID> oidArrayList = new ArrayList<OID>();
        List<String> oidStringList = new ArrayList<String>();
        List<Field> mibLeafFields = new ArrayList<Field>();

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            MibLeaf mibLeaf = field.getAnnotation(MibLeaf.class);
            if (mibLeaf != null) {
                oidArrayList.add(new OID(mibLeaf.oid()));
                oidStringList.add(mibLeaf.oid());
                mibLeafFields.add(field);
            }
        }


        List<VariableBinding> variableBindings = util.snmpGet(oidStringList);
        for (int i = 0; i < variableBindings.size(); i++) {
            VariableBinding vb = variableBindings.get(i);
            Field field = mibLeafFields.get(i);
            Variable variable = vb.getVariable();
            if (field.getType().equals(String.class)) {
                CommonUtil.getInstance().setFiledValue(object,field,variable.toString());
            } else if (field.getType().equals(Integer.class)) {
                CommonUtil.getInstance().setFiledValue(object,field,variable.toInt());
            } else if (field.getType().equals(Long.class)) {
                CommonUtil.getInstance().setFiledValue(object,field,variable.toLong());
            }
        }



        return object;
    }



    public List snmpGetTable(SnmpUtil util,Class cls) throws  Exception {
        String key = util.getAddress()+"_Table"+"."+cls.getSimpleName();
        if (debug) {
            Object o = ObjectUtil.readObject(key);
            if (o != null) return (List)o;
        }


        List objectList = new ArrayList();
        List<OID> oidArrayList = new ArrayList<OID>();
        List<String> oidStringList = new ArrayList<String>();
        List<Field> mibLeafFields = new ArrayList<Field>();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            MibTableColumn mibLeaf = field.getAnnotation(MibTableColumn.class);
            if (mibLeaf != null) {
                oidArrayList.add(new OID(mibLeaf.oid()));
                oidStringList.add(mibLeaf.oid());
                mibLeafFields.add(field);
            }
        }

        OID[] oids = new OID[oidArrayList.size()];
        for (int i = 0; i < oidArrayList.size(); i++) {
            oids[i] = oidArrayList.get(i);
        }

        List<TableEvent> list = util.snmpGetTable(oids,null,null);
        if (list != null) {
            for (TableEvent event : list) {
                Object object = cls.newInstance();
                if (object instanceof SnmpTableEntry && event.getIndex() != null) {
                    ((SnmpTableEntry) object).setIndex(event.getIndex().toString());
                }
                VariableBinding[] variableBindings = event.getColumns();
                for (int i = 0; i < variableBindings.length; i++) {
                    VariableBinding vb = variableBindings[i];
                    if (vb == null) continue;;
                    Field field = mibLeafFields.get(i);
                    Variable variable = vb.getVariable();
                    if (field.getType().equals(String.class)) {
                        String value = null;
                        if (variable instanceof OctetString) {
                            if (((OctetString) variable).toByteArray() != null)
                                value = new String(((OctetString) variable).toByteArray(),"gb2312");
                        } else {
                            value =variable.toString();
                        }
                        CommonUtil.getInstance().setFieldValueByMethod(object,field,value);
                    } else if (field.getType().equals(Integer.class)) {
                        CommonUtil.getInstance().setFieldValueByMethod(object,field,variable.toInt());
                    } else if (field.getType().equals(Long.class)) {
                        CommonUtil.getInstance().setFieldValueByMethod(object,field,variable.toLong());
                    } else if (field.getType().equals(Date.class)) {
                        Date value = null;
                        if (variable instanceof TimeTicks) {
                            long l = ((TimeTicks) variable).toMilliseconds();
                            value = new Date(l);
                        } else {
                            value = new Date(variable.toLong());
                        }
                        CommonUtil.getInstance().setFieldValueByMethod(object,field,value);
                    }else if (field.getType().equals(new byte[0].getClass())) {
                        byte[] value = new byte[0];
                        if (variable instanceof OctetString)   {
                            value = ((OctetString) variable).toByteArray();
                        }
                        CommonUtil.getInstance().setFieldValueByMethod(object,field,value);
                    }
                }

                objectList.add(object);

            }
        }

        if (save)
            ObjectUtil.saveObject(key,objectList);
        return objectList;
    }
}
