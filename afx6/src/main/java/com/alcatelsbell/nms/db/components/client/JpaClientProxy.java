package com.alcatelsbell.nms.db.components.client;

//import com.alcatelsbell.nms.util.DBIntercepter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * User: Ronnie
 * Date: 11-9-30
 * Time: 上午9:59
 */
public class JpaClientProxy implements InvocationHandler {
    private Object proxyObj;
    private Log logger = LogFactory.getLog(this.getClass());

    public Object bind(Object obj) {

        this.proxyObj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), this);
    }
    static int count = 0;
    public Object invoke(Object proxy, Method method, Object[] args) throws
            Throwable {
        Object result = null;

        try {

             result = method.invoke(proxyObj, args); //原方法
         } catch (Exception e) {
            e.printStackTrace();
        }

      //  dbIntercepter.intercept(result);
        return result;
    }
}