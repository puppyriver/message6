package com.alcatelsbell.nms.valueobject.physical.config;

import com.alcatelsbell.nms.common.crud.annotation.BField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie
 * Date: 12-5-22
 * Time: 上午10:12
 */
@Entity
@Table(name = "T_SHELFTYPE")
public class ShelfType extends EntityType {

    @BField(description = "槽道数")
    private Integer slotNumber ;


    //存一个字符串 ，例如 "1:(10,100);2:(20,100);3:(20,100)"
    @Column(length = 2048)
    private String slotCoordinate;

    //槽道规格,存一个字符串 ，例如 "(10,100)" (如果所有槽规格都一样的话)
    @Column(length = 2048)
    private String slotSpec;



    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getSlotCoordinate() {
        return slotCoordinate;
    }

    public void setSlotCoordinate(String slotCoordinate) {
        this.slotCoordinate = slotCoordinate;
    }

    public String getSlotSpec() {
        return slotSpec;
    }

    public void setSlotSpec(String slotSpec) {
        this.slotSpec = slotSpec;
    }
}
