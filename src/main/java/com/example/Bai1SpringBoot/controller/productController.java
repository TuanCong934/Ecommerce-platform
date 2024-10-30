package com.example.Bai1SpringBoot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Bai1SpringBoot.model.entity.Product;
import com.example.Bai1SpringBoot.model.entity.TypeProduct;
import com.example.Bai1SpringBoot.model.entity.User;
import com.example.Bai1SpringBoot.model.repo.LoginRepo;
import com.example.Bai1SpringBoot.model.repo.productRepo;
import com.example.Bai1SpringBoot.model.repo.typeRepo;
import com.example.Bai1SpringBoot.model.repo.userRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class productController {
     @Autowired
     productRepo pRepo;
     @Autowired
     typeRepo tRepo;

     @GetMapping("/")
     public String ShowIndex(Model model) throws Exception {
          ArrayList<Product> proArr = pRepo.getAllProduct();
          model.addAttribute("productlist", proArr);
          ArrayList<TypeProduct> typeArr = tRepo.getAllType();
          model.addAttribute("typelist", typeArr);
          return "public/Trangchu";
     }

     @PostMapping("/filter")
     public String filter(Model model, @RequestParam("typeProduct") int typeProduct)throws Exception{
          ArrayList<Product> prolist = pRepo.getAllProductByType(typeProduct);
          model.addAttribute("productlist", prolist);
          ArrayList<TypeProduct> typeArr = tRepo.getAllType();
          model.addAttribute("typelist", typeArr);
          return "public/Trangchu";
     }

     @GetMapping("/productdetail/{id}")
     public String ShowDetail(@PathVariable("id") int id, Model model) throws Exception {
          Product pro = pRepo.getProductById(id);
          model.addAttribute("productdetail", pro);
          return "public/productdetail";
     }

     @GetMapping("/showaddproduct")
     public String AddProduct(Model model) throws Exception {
          ArrayList<TypeProduct> typeArr = tRepo.getAllType();
          model.addAttribute("typelist", typeArr);
          return "public/addproduct";
     }

     @PostMapping("/addproduct")
     public String AddProduct(@RequestParam("productname") String name, @RequestParam("type") int tid,
               @RequestParam("price") double price, @RequestParam("quantity") int quantity,
               @RequestParam("img") String img) throws Exception {
          TypeProduct type = tRepo.getTypeById(tid);
          Product product = new Product(0, name, type, price, quantity, img, 0);
          pRepo.addNewProduct(product);
          return "redirect:/";
     }

     @GetMapping("/showeditproduct/{id}")
     public String EditProduct(@PathVariable("id") int id, Model model) throws Exception {
          ArrayList<TypeProduct> typeArr = tRepo.getAllType();
          model.addAttribute("typelist", typeArr);
          Product product = pRepo.getProductById(id);
          model.addAttribute("product", product);
          return "public/editproduct";
     }

     @PostMapping("/editproduct")
     public String EditProduct(@RequestParam("pid") int id, @RequestParam("productname") String name,
               @RequestParam("type") int tid,
               @RequestParam("price") double price, @RequestParam("quantity") int quantity,
               @RequestParam("img") String img) throws Exception {

          TypeProduct tp = tRepo.getTypeById(tid);
          Product product = new Product(id, name, tp, price, quantity, img, 1);
          pRepo.updateProduct(product);
          return "redirect:/";
     }

     @PostMapping("/search")
     public String searchProduct(@RequestParam("search") String search, Model model)throws Exception{
          ArrayList<Product> proList = pRepo.getAllProduct();
          ArrayList<Product> searchList = new ArrayList<>();
          for (Product pro : proList) {
               if(search.toLowerCase().contains(pro.pName.toLowerCase())){
                    searchList.add(pro);
               }
          }
          model.addAttribute("productlist", searchList);
          return "public/Trangchu";
     }

     
}
