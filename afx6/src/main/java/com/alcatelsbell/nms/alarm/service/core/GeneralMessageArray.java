package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.common.message.GeneralMessage;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: Ronnie
 * Date: 12-7-20
 * Time: 上午10:57
 */
public class GeneralMessageArray {
    private static GeneralMessageArray ourInstance = new GeneralMessageArray();
    private Log logger = LogFactory.getLog(getClass());
    public static GeneralMessageArray getInstance() {
        return ourInstance;
    }

    private GeneralMessageArray() {
    }

    TreeSet messageSet = new TreeSet(new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Curralarm alarm1 = (Curralarm)((GeneralMessage)o1).getMsgObj();
            Curralarm alarm2 = (Curralarm)((GeneralMessage)o2).getMsgObj();
            long l = (alarm1.getSerial() - alarm2.getSerial()) ;
            if (l > 0) return 1;
            if (l == 0) return 0;
            if (l < 0) return -1;
            return 0;
        }
    });

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void addGeneralMessage(GeneralMessage message) {
        lock.writeLock().lock();
        try {
            if (messageSet.size() > 10000) {
                messageSet.pollFirst();
            }
            messageSet.add(message);
        } catch (Exception e) {
            logger.error(e,e);
        } finally {
            lock.writeLock().unlock();
        }


    }

    public List<GeneralMessage> read(long serialId) {
        List<GeneralMessage> list = new ArrayList<GeneralMessage>();
         lock.readLock().lock();

        try {
            Object[] array = messageSet.toArray();
            for (int i = array.length-1; i >= 0; i --) {
                GeneralMessage message = (GeneralMessage)array[i];
                long serial = ((Curralarm)message.getMsgObj()).getSerial();
                if (serial > serialId) {
                    list.add(message);
                }  else {
                    break;
                }
            }
        } catch (Exception e) {
            logger.error(e,e);
        } finally {
            lock.readLock().unlock();
        }

        List returnList = new ArrayList();
        for (int i = list.size()-1; i >=0  ; i--) {
            returnList.add(list.get(i));
        }

        return returnList;
    }
}
