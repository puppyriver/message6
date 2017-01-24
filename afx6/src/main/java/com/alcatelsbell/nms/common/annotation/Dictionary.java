package com.alcatelsbell.nms.common.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * User: Ronnie
 * Date: 12-5-9
 * Time: 上午10:11
 */
@java.lang.annotation.Documented
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@Retention(RUNTIME)
public @interface Dictionary {
    String name() default "";
    String desc() default "";
}