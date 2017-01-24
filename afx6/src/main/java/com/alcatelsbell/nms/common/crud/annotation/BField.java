package com.alcatelsbell.nms.common.crud.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * User: Ronnie
 * Date: 12-2-13
 * Time: 下午2:51
 */
@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface BField {
    public enum ViewType {
        HIDE,
        SHOW,  //详细信息和列表中都能看到
        DETAIL //只在详细信息中可以看到
    }


    public enum CreateType {
        HIDE,NULLABLE,REQUIRED
    }

    public enum EditType {
        HIDE,NULLABLE,REQUIRED
    }

    public enum SearchType {
        HIDE,NULLABLE,REQUIRED
    }
    
    /**
     * 数据更新合并类型
     * OVERRIDE：覆盖数据库
     * RESERVED：保留数据库里面值
     * */
    public enum MergeType {
    	OVERRIDE,RESERVED
    }
    public String description() default "";

    /**
     * Use DicGroupMapping instead
     * @return
     */
    @Deprecated
    public String[] values() default {};
//    public boolean nullable();

    public ViewType viewType() default ViewType.SHOW;
    public CreateType createType() default CreateType.NULLABLE;
    public EditType editType() default EditType.NULLABLE;
    public SearchType searchType() default SearchType.HIDE;
    public MergeType mergeType()default MergeType.OVERRIDE;

    public String dnField() default "dn";
    public String dnReferenceEntityName() default "";
    public String dnReferenceEntityField() default "";
    public String dnReferenceTransietField() default "";
    public int sequence() default 100;
    public String lazyLoadExp() default "";
}
