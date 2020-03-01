package com.a528854302.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Data
@Table
public class Admin  implements Serializable {
    @Id
    private String id;
    private String username;
    private String password;
}
