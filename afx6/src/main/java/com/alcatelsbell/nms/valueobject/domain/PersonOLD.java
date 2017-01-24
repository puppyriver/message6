package com.alcatelsbell.nms.valueobject.domain;

import javax.persistence.*;

/**
 * User: Change Via
 * Date: 12-10-29
 * Time: 下午11:01
 */
@Entity
@Table(name = "person_table")
@SecondaryTable(name = "person_detail",pkJoinColumns=@PrimaryKeyJoinColumn(name = "person_id"))

public class PersonOLD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "person_name", length = 50)
    private String name;

    @Column(table = "person_detail", name = "email")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(table = "person_detail", name = "phone")
    private String phone;
}
