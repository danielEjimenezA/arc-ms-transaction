package com.pichincha.springjparest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person
{
    @Id
    @Size(max = 13)
    @Column(
            name = "id",
            nullable = false,
            length = 13
    )
    private String id;

    @Size(max = 200)
    @Column(
            name = "name",
            length = 200
    )
    private String name;

    @Size(max = 20)
    @Column(
            name = "gender",
            length = 20
    )
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Size(max = 300)
    @Column(
            name = "address",
            length = 300
    )
    private String address;

    @Size(max = 20)
    @Column(
            name = "phone",
            length = 20
    )
    private String phone;

}