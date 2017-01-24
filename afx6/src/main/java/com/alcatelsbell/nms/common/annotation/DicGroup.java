package com.alcatelsbell.nms.common.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * User: Ronnie
 * Date: 12-5-7
 * Time: 下午4:56
 */
@java.lang.annotation.Documented
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@Retention(RUNTIME)
public @interface DicGroup {
   String name();
}
