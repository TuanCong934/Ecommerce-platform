package com.example.Bai1SpringBoot.model.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Baseconnection {
    static String url = "jdbc:mysql://localhost:3307/banhangmvc";

    static String username = "root";
    static String password = "tuancong04";
    static String nameClass = "com.mysql.cj.jdbc.Driver";

    // public static void main(String[] args) {
    // String url = "jdbc:mysql://localhost:3307/quanlythuvien";
    // String username = "root";
    // String password = "tuancong04";
    // try {
    // Class.forName("com.mysql.cj.jdbc.Driver");
    // try (Connection connection = DriverManager.getConnection(url, username,
    // password)) {
    // if (connection != null) {
    // System.out.println("Kết nối thành công!");
    // } else {
    // System.out.println("Kết nối thất bại!");
    // }
    // }
    // } catch (ClassNotFoundException e) {
    // System.out.println("Driver không tìm thấy: " + e.getMessage());
    // } catch (SQLException e) {
    // System.out.println("Lỗi kết nối: " + e.getMessage());
    // }
    // }
}
