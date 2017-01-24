package com.alcatelsbell.nms.domain;

import com.alcatelsbell.nms.db.components.client.JpaClient;
import com.alcatelsbell.nms.valueobject.domain.RRegion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Ronnie
 * Date: 11-12-7
 * Time: 下午1:28
 */
public class RegionDataCache {
    private static class InstHolder {
        public static RegionDataCache inst = new RegionDataCache();
    }

    public static RegionDataCache getInstance() {
        return InstHolder.inst;
    }
    
    private Log logger = LogFactory.getLog(getClass());
    private HashMap<String,RRegion> regionDnCache = new HashMap<String, RRegion>();
    private HashMap<Long,RRegion> regionIdCache = new HashMap<Long, RRegion>();
    private ReentrantLock dnlock = new ReentrantLock();
    public RRegion getRRegion(String dn) {
        if (dn == null) return null;
        if (regionDnCache.get(dn) == null) {
            RRegion region  = null;
            try {
                region = (RRegion) JpaClient.getInstance().findObjectByDN(RRegion.class,dn);
            } catch (Exception e) {
                logger.error(e,e);
            }
            if (region != null) {
                initCache(region);

            } else {
                logger.error("Failed to find RRegiondn:"+dn);
            }
             return region;
        }else{
        	return regionDnCache.get(dn);
        }
    }
    public RRegion getRRegion(Long id) {
        if (id == null) return null;
        if (regionDnCache.get(id) == null) {
            RRegion region  = null;
            try {
                region = (RRegion) JpaClient.getInstance().findObjectById(RRegion.class,id);
            } catch (Exception e) {
                logger.error(e,e);
            }
            if (region != null) {
                initCache(region);

            } else {
                logger.error("Failed to find RRegiondn:"+id);
            }
             return region;
        }else{
        	return regionDnCache.get(id);
        }
    }
    
    public List<RRegion> getAllRRegion() {
    	List<RRegion> result=null;
    	try {
            result = (List<RRegion>) JpaClient.getInstance().findAllObjects(RRegion.class);
        } catch (Exception e) {
            logger.error(e,e);
        }
    	return result;
    }

    public List<RRegion> getSubRegions(RRegion region) {
        if (regionDnCache == null || regionDnCache.isEmpty()) {
            List<RRegion> allregions = this.getAllRRegion();
            if (allregions != null) {
                for (RRegion rRegion : allregions)
                    initCache(rRegion);
            }
        }
        List<RRegion> subRegions = new ArrayList<RRegion>();
        Collection<RRegion> regions = regionDnCache.values();
        if (regions != null) {
            for (RRegion rg : regions) {
                if (rg.getParentRegionDn() != null && rg.getParentRegionDn().equals(region.getDn()))
                    subRegions.add(rg);
            }
        }
        return subRegions;
    }
    
    private void initCache(RRegion region) {
        dnlock.lock();
        try {
            regionDnCache.put(region.getDn(),region);
            regionIdCache.put(region.getId(),region);
        } catch (Exception e) {
            logger.error(e,e);
        } finally {
            dnlock.unlock();
        }
    }


}
