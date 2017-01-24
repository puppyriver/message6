package com.alcatelsbell.nms.security.ditp;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-25
 * Time: 下午1:57
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface MessageField {
    /**
     * start from 1
     * @return
     */
    int index();
    int byteSize();
    byte[] value() default {};
    String sizeFunction() default "";
}
