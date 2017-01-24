package com.alcatelsbell.nms.common;

import com.alcatelsbell.nms.db.components.client.JpaClient;
import com.alcatelsbell.nms.db.components.service.JPASupport;
import com.alcatelsbell.nms.db.components.service.JpaServerUtil;
import com.alcatelsbell.nms.valueobject.domain.RRegion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * User: Ronnie.Chen
 * Date: 11-6-23                                                               n
 */
public class RegionUtil {
    private static HashMap<String,RRegion> regionMap = new HashMap<String, RRegion>();
    private static Log logger = LogFactory.getLog(RegionUtil.class);
    static {
        List<RRegion> regions = null;
        try {
            regions = JpaClient.getInstance().findAllObjects(RRegion.class);
//            System.err.println("RegionUtil  load regions size:"+regions.size());
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (regions != null) {
            for (RRegion region : regions) {
                regionMap.put(region.getDn(),region);
            }
        }
    }


    public static RRegion getRegionByDn(String dn) {
//    	System.err.println("RegionUtil  load regions size:"+regionMap.size());
    	if(regionMap==null|regionMap.size()<=0){
    		 List<RRegion> regions = null;
    	        try {
    	            regions = JpaClient.getInstance().findAllObjects(RRegion.class);
//    	            System.err.println("RegionUtil  load regions size:"+regions.size());
    	        } catch (Exception e) {
    	            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    	        }
    	        if (regions != null) {
    	            for (RRegion region : regions) {
    	                regionMap.put(region.getDn(),region);
    	            }
    	        }
    	}
        return regionMap.get(dn);
    }

    public static  RRegion getRootRegion() {
        try {
            List<RRegion> regions = JpaClient.getInstance().findObjects("select c from RRegion c where c.rootFlag = 1");
            if (regions != null && regions.size() > 0) {
                return regions.get(0);
            }
            regions = JpaClient.getInstance().findObjects("select c from RRegion c where c.parentRegionDn is null");
            if (regions != null && regions.size() > 0) {
                return regions.get(0);
            }
        } catch (Exception e) {
            logger.error(e, e);
        }

        return null;

    }

    public static RRegion getRegionByName(String name) {
        Collection<RRegion> regions = regionMap.values();
        if (regions != null) {
            for (RRegion region : regions) {
                if (region.getName().equals(name))
                    return region;
            }
        }
        return null;
    }

    public static List getAllSubRegions(String regionDn) {
        RRegion region = regionMap.get(regionDn);
        List l = new ArrayList();
        l.add(region);
        Collection<RRegion> values = regionMap.values();
        for (Iterator<RRegion> iterator = values.iterator(); iterator.hasNext(); ) {
            RRegion next = iterator.next();
            if (next.getParentRegionDn() != null && next.getParentRegionDn().equals(regionDn)) {
                 l.addAll(getAllSubRegions(next.getDn()));
            }
        }
        return l;
    }


    public static void main(String[] args) throws Exception {
    //    RegionUtil.initRegionData();
        System.out.println(RegionUtil.getRootRegion());
    }
}
