package com.alcatelsbell.nms.valueobject;

import javax.persistence.*;

/**
 * User: Change Via
 * Date: 12-10-29
 * Time: 下午11:27
 */
@Entity
@Table(name = "person_detail")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personid;

    @Column(name = "name")
    private String name;

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

