

package com.alcatelsbell.nms.alarm.common;

import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import com.alcatelsbell.nms.valueobject.domain.RCustomer;
import com.alcatelsbell.nms.valueobject.domain.RProduct;


import java.io.Serializable;
import java.util.Collection;

import java.util.HashMap;
import java.util.Vector;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: ASB</p>
 *
 * @author Ronnie
 * @version 1.0
 */
public class AlarmWrapper implements Serializable
{
    private Curralarm m_alarm;
    private Alarminformation m_event;
    private Vector<RProduct> m_alarmproducts = new Vector<RProduct>();
    private Vector<RCustomer> m_alarmcustomers = new Vector<RCustomer>();
    private HashMap dataMap = new HashMap();

    public void setData(Object key,Object value) {
        dataMap.put(key,value);
    }

    public Object getData(Object key) {
        return dataMap.get(key);
    }

//    private VendorAlarmLib m_vendorAlarmLib;
    public AlarmWrapper( Curralarm _alarm, Alarminformation _event )
    {
        m_alarm = _alarm;
        m_event = _event;
 //       m_vendorAlarmLib = null;
//        m_event.setAlarmid( m_alarm.getId() );
//
//        m_alarmproducts = new Vector<AlarmBusinessAssign>();
    }
    public AlarmWrapper( Curralarm _alarm )
    {
        m_alarm = _alarm;

    }

//    public VendorAlarmLib getVendorAlarmLib()
//    {
//        return m_vendorAlarmLib;
//    }
//
//    public void setVendorAlarmLib( VendorAlarmLib _val )
//    {
//        m_vendorAlarmLib = _val;
//    }

    public Vector<RProduct> getAlarmProductAssigns()
    {
        Vector<RProduct> aca = new Vector<RProduct>();
        aca.addAll(m_alarmproducts);

        return aca;
    }

    public void setAlarmProductAssigns( Vector<RProduct> _aca )
    {
        m_alarmproducts.clear();
        m_alarmproducts.addAll( _aca );
    }
     public Vector<RCustomer> getAlarmCustomerAssigns()
    {
        Vector<RCustomer> aca = new Vector<RCustomer>();
        aca.addAll(m_alarmcustomers);

        return aca;
    }

    public void setAlarmCustomerAssigns( Collection<RCustomer> _aca )
    {
        m_alarmcustomers.clear();
        m_alarmcustomers.addAll( _aca );
    }
    public Curralarm getCurralarm()
    {
        return m_alarm;
    }
    public void setCurralarm( Curralarm _alarm )
    {
        m_alarm = _alarm;
    }

    public Alarminformation getEvent()
    {
        return m_event;
    }
    public void setEvent( Alarminformation _event )
    {
        m_event = _event;
    }
}
