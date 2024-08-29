package com.example.Bai1SpringBoot.model.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import org.springframework.stereotype.Repository;

import com.example.Bai1SpringBoot.model.entity.Product;
import com.example.Bai1SpringBoot.model.entity.TypeProduct;

@Repository
public class productRepo {
     public typeRepo tRepo = new typeRepo();

     public ArrayList<Product> getAllProduct()throws Exception{
        ArrayList<Product> proList = new ArrayList<>();
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username, Baseconnection.password);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from product");
        while (rs.next()) {
            int id = rs.getInt("pid");       
            String name = rs.getString("pName");
            int typeid = rs.getInt("tid");
            TypeProduct tp = tRepo.getTypeById(typeid);
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            String img = rs.getString("img");
            Product product = new Product(id, name, tp, price, quantity, img);
            proList.add(product);      
        }
        return proList;
    }

    public Product getProductById(int id)throws Exception{
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username, Baseconnection.password);
        PreparedStatement ps = con.prepareStatement("select * from product where pid = ?");
        ps.setInt(1, id);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        rs.next();
        int pid = rs.getInt("pid");
        String pname = rs.getString("pName");
        int typeid = rs.getInt("tid");
        TypeProduct tp = tRepo.getTypeById(typeid);
        double price = rs.getDouble("price");
        int quantity = rs.getInt("quantity");
        String img = rs.getString("img");
        Product product = new Product(pid, pname, tp, price, quantity, img);
        return product;
    }

    public void addNewProduct(Product product)throws Exception{
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username, Baseconnection.password);
        PreparedStatement ps = con.prepareStatement("insert into product(pName, pType, price, quantity) values (?,?,?,?)");
        ps.setString(1, product.getPName());
        ps.setInt(2, product.getTypeProduct().getTid());
        ps.setDouble(3, product.getPrice());
        ps.setInt(4, product.getQuantity());
        ps.setString(5, product.getImg());
        ps.executeUpdate();
        ps.close();
    }

    public void updateProduct(Product product)throws Exception{
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username, Baseconnection.password);
        PreparedStatement ps = con.prepareStatement("update product set pName = ? and tid = ? and price = ? and quantity = ? and img = ? where pid = ?");
        ps.setString(1, product.getPName());
        ps.setInt(2, product.getTypeProduct().getTid());
        ps.setDouble(3, product.getPrice());
        ps.setInt(4, product.getQuantity());
        ps.setString(5, product.getImg());
        ps.setInt(6, product.getPid());
        ps.executeUpdate();
        ps.close();       
    }
}
