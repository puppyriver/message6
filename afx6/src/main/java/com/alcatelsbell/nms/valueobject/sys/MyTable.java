package com.alcatelsbell.nms.valueobject.sys;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Ronnie
 * Date: 11-11-15
 * Time: 下午4:43
 */
@Entity
@Table(name = "MyTable")
public class MyTable implements Serializable{
    @Id
   @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GEN3")
   @TableGenerator(name = "ID_GEN3", table = "ID_GEN3", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MYTABLE", initialValue = 2, allocationSize = 5)
   protected Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
