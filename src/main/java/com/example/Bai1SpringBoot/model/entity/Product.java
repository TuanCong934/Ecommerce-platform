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
public class Product {
    public int pid;
    public String pName;
    public TypeProduct typeProduct;
    public double price;
    public int quantity;
    public String img;
    public int status;
}
