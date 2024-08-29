package com.example.Bai1SpringBoot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Bai1SpringBoot.model.entity.User;
import com.example.Bai1SpringBoot.model.repo.userRepo;

@Controller
public class productController {
    @Autowired
    userRepo uRepo;

    @GetMapping("/")
    public String ShowIndex(Model model) throws Exception {
        ArrayList<User> userArr = uRepo.getAllUser();
        model.addAttribute("userlist", userArr);
        return "public/banhangmvc";
    }
}
