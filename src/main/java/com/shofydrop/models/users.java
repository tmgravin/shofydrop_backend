package com.shofydrop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(columnDefinition = "varchar(14")
    private String phone;
    private String address;
    private String image;
    private String status;
    private String type;
    private String role;
    private String created_at;
    private String updated_at;
}
