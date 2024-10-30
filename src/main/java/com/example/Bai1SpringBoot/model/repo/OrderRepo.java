package com.example.Bai1SpringBoot.model.repo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.Bai1SpringBoot.model.entity.Order;
import com.example.Bai1SpringBoot.model.entity.Product;
import com.example.Bai1SpringBoot.model.entity.User;

@Repository
public class OrderRepo {
    @Autowired
    userRepo uRepo;
    @Autowired
    productRepo pRepo;

    public ArrayList<Order> getAllOrder() throws Exception {
        ArrayList<Order> oList = new ArrayList<>();
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username,
                Baseconnection.password);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from `order`");
        while (rs.next()) {
            int oid = rs.getInt("oid");
            int uid = rs.getInt("uid");
            int pid = rs.getInt("pid");
            int quantity = rs.getInt("quantity");
            double totalprice = rs.getDouble("totalprice");
            User user = uRepo.getUserById(uid);
            Product product = pRepo.getProductById(pid);
            Order od = new Order(oid, user, product, quantity, totalprice);
            oList.add(od);
        }
        return oList;
    }

    public ArrayList<Order> getAllOrderByUserId(int uid) throws Exception {
        ArrayList<Order> OrderList = new ArrayList<>();
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username,
                Baseconnection.password);
        PreparedStatement ps = con.prepareStatement("select * from `order` where uid = ?");
        ps.setInt(1, uid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int oid = rs.getInt("oid");
            int idUser = rs.getInt("uid");
            int idProduct = rs.getInt("pid");
            User user = uRepo.getUserById(idUser);
            Product product = pRepo.getProductById(idProduct);
            int quantity = rs.getInt("quantity");
            double totalprice = rs.getDouble("totalprice");
            Order order = new Order(oid, user, product, quantity, totalprice);
            OrderList.add(order);
        }
        return OrderList;
    }

    public void addNewOrder(Order order) throws Exception {
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username,
                Baseconnection.password);
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO `order` (uid, pid, quantity, totalprice) VALUES (?,?,?,?)");
        ps.setInt(1, order.getUser().getUid());
        ps.setInt(2, order.getProduct().getPid());
        ps.setInt(3, order.getQuantity());
        ps.setDouble(4, order.getTotalprice());
        ps.executeUpdate();
    }
}
