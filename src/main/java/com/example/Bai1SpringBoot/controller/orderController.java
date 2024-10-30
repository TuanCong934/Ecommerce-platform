package com.example.Bai1SpringBoot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Bai1SpringBoot.model.entity.Order;
import com.example.Bai1SpringBoot.model.entity.Product;
import com.example.Bai1SpringBoot.model.entity.User;
import com.example.Bai1SpringBoot.model.repo.OrderRepo;
import com.example.Bai1SpringBoot.model.repo.productRepo;
import com.example.Bai1SpringBoot.model.repo.userRepo;

import jakarta.servlet.http.HttpSession;


@Controller
public class orderController {
    @Autowired
    userRepo uRepo;
    @Autowired
    productRepo pRepo;
    @Autowired
    OrderRepo oRepo;

    ArrayList<Product> cartList = new ArrayList<>();

    @GetMapping("/showOrder/{id}")
    public String ShowOrder(@PathVariable("id")int id, Model model) throws Exception{
        Product product = pRepo.getProductById(id);
        model.addAttribute("product", product);
        return "public/order";
    }

    @PostMapping("/Order")
    public String Order(@RequestParam("id") int id, @RequestParam("quantity")int quantity, HttpSession httpsession)throws Exception{
        User user = (User) httpsession.getAttribute("userafterlogin");
        Product product = pRepo.getProductById(id);
        double totalprice = product.getPrice() * quantity;
        int newQuantity = product.getQuantity() - quantity;
        Order order = new Order(0, user, product, quantity, totalprice);
        oRepo.addNewOrder(order);
        pRepo.updateQuantity(id, newQuantity);
        return "redirect:/";
    }

    @GetMapping("/showUserAllOrders")
    public String showUserAllOders(HttpSession httpSession, Model model)throws Exception{
        User user = (User) httpSession.getAttribute("userafterlogin");
        ArrayList<Order> OrderArr = oRepo.getAllOrderByUserId(user.getUid());
        model.addAttribute("orderList", OrderArr);
        return "public/showalluserorders";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id")int id, HttpSession httpsession, @RequestParam("cartquantity")int quantity)throws Exception{
        Product product = pRepo.getProductById(id);
        for (Product p : cartList) {
            if(p.getPid() == product.pid){
                p.setQuantity(p.getQuantity() + (int)quantity);
                return"redirect:/";
            }
        }
        product.setQuantity(1);
        cartList.add(product);
        httpsession.setAttribute("cartlist", cartList);
        return"redirect:/";
    }

    @GetMapping("showCart")
    public String showCart(HttpSession httpSession, Model model)throws Exception{
        ArrayList<Product> cartlist = (ArrayList<Product>) httpSession.getAttribute("cartlist");
        model.addAttribute("cartlistmodel", cartlist);
        double totalpriceallproduct = 0;
        for (Product product : cartlist) {
            totalpriceallproduct = totalpriceallproduct + (product.getQuantity() * product.getPrice());
        }
        httpSession.setAttribute("totalpriceallproduct", totalpriceallproduct);
        return "public/showcart";
    }

    @GetMapping("increase/{id}")
    public String increase(@PathVariable("id")int id, HttpSession httpSession)throws Exception{
        ArrayList<Product> cartList = (ArrayList<Product>) httpSession.getAttribute("cartlist");
        for (Product product : cartList) {
            if(product.getPid() == id){
                product.setQuantity(product.getQuantity() + 1);
                return "redirect:/showCart";
            }
        }
        return "redirect:/showCart";
    }

    @GetMapping("decrease/{id}")
    public String decrease(@PathVariable("id")int id, HttpSession httpSession)throws Exception{
        ArrayList<Product> cartList = (ArrayList<Product>) httpSession.getAttribute("cartlist");
        for (Product product : cartList) {
            if(product.getPid() == id){
                if(product.getQuantity() == 1){
                    cartList.remove(product);
                    return "redirect:/showCart";
                }
                else{
                    product.setQuantity(product.getQuantity() - 1);
                    return "redirect:/showCart";
                }               
            }
        }
        return "redirect:/showCart";
    }

    @GetMapping("buyallproduct")
    public String buyall(HttpSession httpSession)throws Exception{
        ArrayList<Product> proList = (ArrayList<Product>) httpSession.getAttribute("cartlist");
        User user = (User) httpSession.getAttribute("userafterlogin");
        for (Product product : proList) {
            Product producttt = pRepo.getProductById(product.getPid());
            double totalprice = product.getPrice() * product.getQuantity();
            int newQuantity = producttt.getQuantity() - product.getQuantity();
            Order order = new Order(0, user, product, product.getQuantity(), totalprice);
            oRepo.addNewOrder(order);
            pRepo.updateQuantity(producttt.getPid(), newQuantity);
        }
        proList.clear();
        return "redirect:/";
    }
}
