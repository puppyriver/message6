package com.alcatelsbell.nms.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * User: Ronnie
 * Date: 12-5-9
 * Time: 上午10:06
 */

@Target({FIELD})
@Retention(RUNTIME)
public @interface DicGroupMapping {
    public String groupName();
    public Class definitionClass() default DicGroupMapping.class;   //默认无定义字典类，则从系统配置文件dictionary.xml中读取
}


