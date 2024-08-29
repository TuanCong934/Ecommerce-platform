package com.example.Bai1SpringBoot.model.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.Bai1SpringBoot.model.entity.TypeProduct;

@Repository
public class typeRepo {
    public ArrayList<TypeProduct> getAllType()throws Exception{
        ArrayList<TypeProduct> tList = new ArrayList<>();
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username, Baseconnection.password);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from TypeProduct");
        while (rs.next()) {
            int tid = rs.getInt("tid");
            String name = rs.getString("tName");
            TypeProduct tp = new TypeProduct(tid, name);
            tList.add(tp);
        }
        return tList;
    }

    public TypeProduct getTypeById(int id)throws Exception{
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username, Baseconnection.password);
        PreparedStatement ps = con.prepareStatement("select * from TypeProduct where tid = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int tid = rs.getInt("tid");
        String tName = rs.getString("tName");
        TypeProduct tp = new TypeProduct(tid, tName);
        return tp;
    }
}
