package com.alcatelsbell.nms.util.protocol.snmp.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author: Ronnie.Chen
 * Date: 12-10-30
 * Time: 下午4:27
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface MibTableColumn {
    public String oid() default "";
}
