package com.sprk.security_demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String roleName;

}

//When Role table created -> If We don't found ROLE_USER in DB
// automatically it should insert ROLE_USER