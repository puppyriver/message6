package com.alcatelsbell.nms.db.components.service;

import com.alcatelsbell.nms.common.SysUtil;

import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.beans.PropertyVetoException;
import java.util.Date;
import java.util.List;


/**
 * User: Ronnie
 * Date: 11-11-26
 * Time: 下午11:22
 */
public class JPASupportFactory {
    public static JPASupport createJPASupport() {
        return new JPASupportSpringImpl();
    }

    /**
     *     <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
     <property name="driverClass"><value>org.sqlite.JDBC</value></property>
     <property name="jdbcUrl"><value>jdbc:sqlite:db/smartodn2.db</value></property>
     <property name="user"><value>root</value></property>
     <property name="password"><value>root</value></property>
     <property name="initialPoolSize"><value>10</value></property>
     <property name="minPoolSize"><value>10</value></property>
     <property name="maxPoolSize"><value>100</value></property>

     </bean>

     * @return
     */



}
