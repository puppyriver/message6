package com.alcatelsbell.nms.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author: Ronnie.Chen
 * Date: 13-1-18
 * Time: 下午1:26
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface PropertiesBean {
    String fileName();
}
