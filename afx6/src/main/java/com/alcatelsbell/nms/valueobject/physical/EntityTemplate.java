package com.alcatelsbell.nms.valueobject.physical;

import com.alcatelsbell.nms.common.CommonDictionary;
import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.valueobject.BObject;

/**
 * User: Ronnie
 * Date: 12-5-7
 * Time: 下午2:13
 */
public class EntityTemplate extends BObject {

    private String code;
    private String memo;
    private String creator;
    private String entityTypeDn;
    private String updator;
    private String name;
    private String vendorDn;
    private Integer length;
    private Integer width;
    private Integer depth;


    @DicGroupMapping(groupName = "EntityTemplate_Type",definitionClass = CommonDictionary.class)
    private int entityTemplateType;


//    CODE                     VARCHAR2(255 BYTE),
//    MEMO                     VARCHAR2(255 BYTE),
//    CREATEDATE               DATE                 NOT NULL,
//    CREATOR                  VARCHAR2(100 BYTE)   NOT NULL,
//    UPDATEDATE               DATE                 NOT NULL,
//    ENTITYTYPE_ID            NUMBER(9),
//    UPDATER                  VARCHAR2(50 BYTE)    NOT NULL,
//    ENTITYHIERARCHYTYPE      NUMBER(4),
//    METAENTITYTYPE_ID        NUMBER(9),
//    ENTITYTEMPLATETYPE       NUMBER(4),
//    PARENTENTITYTEMPLATE_ID  NUMBER(9),
//    ORDINAL                  NUMBER(6),
//    ENTITYTEMPLATEGROUP_ID   NUMBER(9)
}
