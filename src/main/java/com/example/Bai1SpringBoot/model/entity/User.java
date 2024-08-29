package com.example.Bai1SpringBoot.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public int uid;
    public String name;
    public int age;
    public String phone;
    public String email;
    public String cccd;
    public String address;
    public String username;
    public String password;
    public String role;
}
