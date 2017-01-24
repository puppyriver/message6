package com.alcatelsbell.nms.valueobject;

import javax.persistence.*;

/**
 * User: Change Via
 * Date: 12-10-29
 * Time: 下午11:19
 */
@Entity
@Table(name = "address_table")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressid;

    @Column(name = "detail",length = 50)
    private String detail;

    @ManyToOne(optional = false,cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,targetEntity = Person.class)
    @JoinColumn(name = "person_id",nullable = false,updatable = false)
    private Person person;
    //targetEntityClass 可以不知道，默认会field的反射得到

    public Address() {
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
