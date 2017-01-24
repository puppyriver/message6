package com.alcatelsbell.nms.util;


import java.util.*;

/**
 * User: Ronnie
 * Date: 12-6-1
 * Time: 下午2:26
 */
public class NamingUtil {
    private static HashMap<String,Object> services = new HashMap();

    public static void register(String name,Object service) {
        services.put(name,service);
    }

    public static Object getAnyOneService(String contextName)
    {
         return services.get(contextName);

    }

    public static void main(String[] args) {
      // NamingUtil.loadLocalServices();
    }

}
