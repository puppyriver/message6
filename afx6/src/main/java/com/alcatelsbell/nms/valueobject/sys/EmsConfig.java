package com.alcatelsbell.nms.valueobject.sys;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

@Entity
@Table(name = "S_EMSCONFIG")
public class EmsConfig extends BObject {
	@BField(description = "名称",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private String name;
}
