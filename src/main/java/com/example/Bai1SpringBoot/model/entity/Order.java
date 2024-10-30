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
public class Order {
    public int oid;
    public User user;
    public Product product;
    public int quantity;
    public double totalprice;
    // private int setTotalPrice(Product product, quantity){
    //     return product.getPrice() * quantity;
    // }
}
