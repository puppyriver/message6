package com.alcatelsbell.nms.valueobject;

/**
 * User: Ronnie.Chen
 * Date: 11-5-17
 * Time: 8:45
 */

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.FIELD )
public @interface Model {
    String description() default "";

    String name() default "";

    String type() default "";       //enum

}
