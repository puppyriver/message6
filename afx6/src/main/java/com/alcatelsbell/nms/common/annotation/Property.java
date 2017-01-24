package com.alcatelsbell.nms.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author: Ronnie.Chen
 * Date: 13-1-18
 * Time: 下午1:27
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface Property {
    enum PropertyType {
        String,Int,Date,Dictionary,DictionaryMulti
    }
    PropertyType type() default PropertyType.String;
    String key() default "";
    String desc() default "";
    Class dicClass() default Object.class;


}
