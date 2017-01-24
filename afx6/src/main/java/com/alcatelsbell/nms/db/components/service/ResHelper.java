package com.alcatelsbell.nms.db.components.service;

/**
 * User: Ronnie
 * Date: 11-9-28
 * Time: 下午1:46
 */
public class ResHelper {
    public static JPASupport createJPASupport() {
        JPASupport support =  new JPASupportSpringImpl("resEntityManagerFactory");
        return support;
    }
}
