package com.digilink.crud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

//DB TABLE
@Table(name="tbl_customers")
@Entity
@Setter
@Getter
@ToString

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)

    //
    private Long id;
    //
    @Column(name = "name")
    private String name;
    //
    @Column(name = "age")
    private Long age;
    //
    @Column(name = "email")
    private String email;
    //
    @Column(name = "password")
    private String password;

    //Auto Timestamp
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    //UpdateTimestamp
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
