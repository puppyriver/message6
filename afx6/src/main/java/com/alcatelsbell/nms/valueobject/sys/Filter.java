package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie.Chen
 * Date: 11-6-16
 */
@Entity
@Table(name = "S_FILTER")
public class Filter extends BObject {
    private String type;

}
