package com.alcatelsbell.nms.common;


import com.alcatelsbell.nms.db.components.service.JPAContext;
import com.alcatelsbell.nms.db.components.service.JPAUtil;
import com.alcatelsbell.nms.valueobject.physical.EquipmentType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Hashtable;
import java.util.List;

/**
 * User: Ronnie.Chen
 * Date: 11-6-21
 */
public class EquipmentTypeUtil {
    static Log logger = LogFactory.getLog(EquipmentTypeUtil.class);
    private static Hashtable<String,EquipmentType> etMap = new Hashtable<String, EquipmentType>();
    private static List<EquipmentType> ets = null;
    static {
        JPAContext ctx = JPAContext.prepareReadOnlyContext();
        try {
            ets = JPAUtil.getInstance().findAllObjects(ctx, EquipmentType.class);
            ctx.end();
        } catch (Exception e) {
            logger.error(e,e);
        } finally {
            ctx.release();
        }
        logger.info("Find EquipmentType size = "+(ets == null ? 0:ets.size()));
        if (ets != null) {
            for (EquipmentType type : ets) {
                etMap.put(type.getDn(),type);
            }
        }
    }

    public static EquipmentType getEquipmentType(String dn) {
        return etMap.get(dn);
    }

    public static void addEquipmentType(EquipmentType type) {
        ets.add(type);
        etMap.put(type.getDn(),type);
    }

    public static EquipmentType getEquipmentType(String vendor,String name) {
        if (ets != null) {
            for (EquipmentType type : ets) {
                if (type.getVendor().equals(vendor) && type.getName().equals(name))
                    return type;
            }
        }
        return null;
    }

    public static EquipmentType findOrCreateEquipmentType(String dn,String name,String vendor,String desc,String type,String field) {
        EquipmentType et = getEquipmentType(dn);
        if (et == null) {
            et = new EquipmentType();
            et.setDescription(desc);
            et.setDn(dn);
            et.setName(name);
            et.setVendor(vendor);
            et.setType(type);
            et.setField(field);
            JPAContext ctx = JPAContext.prepareContext();
            try {
                et = (EquipmentType) JPAUtil.getInstance().createObject(ctx,-1,et);
                ctx.end();
                addEquipmentType(et);
            } catch (Exception e) {
                logger.error(e,e);
            } finally {
                ctx.release();
            }
        }
        return et;
    }
}
