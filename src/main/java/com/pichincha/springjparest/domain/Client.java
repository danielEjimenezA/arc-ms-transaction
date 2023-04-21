package com.pichincha.springjparest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client
{
    @Id
    @Column(
            name = "client_id",
            nullable = false
    )
    private Long id;

    @Size(max = 20)
    @Column(
            name = "password",
            length = 20
    )
    private String password;

    @Size(max = 10)
    @Column(
            name = "status",
            length = 10
    )
    private Boolean status;

    @NotNull
    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "person_id",
            nullable = false
    )
    private Person person;

}