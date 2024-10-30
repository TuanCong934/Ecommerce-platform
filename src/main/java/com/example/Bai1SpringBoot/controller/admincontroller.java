package com.example.Bai1SpringBoot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bai1SpringBoot.model.entity.Product;
import com.example.Bai1SpringBoot.model.repo.productRepo;
import com.example.Bai1SpringBoot.model.repo.typeRepo;

import jakarta.websocket.server.PathParam;

@Controller
public class admincontroller {
    @Autowired
    productRepo pRepo;
    @Autowired
    typeRepo tRepo;

    @GetMapping("/showduyetproduct")
    public String showduyetProduct(Model model) throws Exception {
        ArrayList<Product> unapprovedlist = pRepo.getAllProductNotApproved();
        model.addAttribute("unapprovedlist", unapprovedlist);
        return "public/duyetproduct";
    }

    @GetMapping("/duyetproduct/{id}")
    public String duyetProduct(@PathVariable("id") int id) throws Exception {
        pRepo.updateStatus(id);
        return "public/duyetproduct";
    }
}
