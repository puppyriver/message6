

package com.alcatelsbell.nms.alarm.service;




import com.alcatelsbell.nms.common.ServiceException;
import java.lang.reflect.UndeclaredThrowableException;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author Ronnie
 * @version 1.0
 */
public class AlarmExceptionUtil {
    static protected AlarmExceptionUtil alarmExcUtil = null;

    static synchronized public AlarmExceptionUtil getInstance() {
        if (alarmExcUtil == null) {
            alarmExcUtil = new AlarmExceptionUtil();
        }
        return alarmExcUtil;
    }

    protected AlarmExceptionUtil() {
    }

    public ServiceException convertException(Exception ex) {
        ServiceException ae = null;
        Throwable throwable = ex;
        if(ex instanceof UndeclaredThrowableException) {
            UndeclaredThrowableException unEx = (UndeclaredThrowableException)ex;
            if(unEx.getUndeclaredThrowable() instanceof InvocationTargetException) {
                throwable = ((InvocationTargetException)(unEx).getUndeclaredThrowable()).getTargetException();
            }
        }
        return ae;
    }
}
