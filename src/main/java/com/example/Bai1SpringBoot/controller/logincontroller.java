package com.example.Bai1SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Bai1SpringBoot.model.entity.User;
import com.example.Bai1SpringBoot.model.repo.LoginRepo;
import com.example.Bai1SpringBoot.model.repo.userRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class logincontroller {
    @Autowired
    LoginRepo lr;
    @Autowired
    userRepo uRepo;

    @GetMapping("/login")
    public String Login() throws Exception {
        return "public/login";
    }

    @PostMapping("/checklogin")
    public String Checklogin(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpSession session) throws Exception {
        User user = lr.checkLogin(username, password);
        if (user == null) {
            return "public/login";
        } else {
            session.setAttribute("userafterlogin", user);
            return "redirect:/";
        }

    }

    @GetMapping("/logout")
    public String Logout(HttpSession session) {
        session.removeAttribute("userafterlogin");
        return "redirect:/";
    }

    @GetMapping("/showsignup")
    public String showsignup() throws Exception {
        return "public/signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("name") String name, @RequestParam("age") int age,
            @RequestParam("phone") String phone, @RequestParam("email") String email, @RequestParam("cccd") String cccd,
            @RequestParam("address") String address, @RequestParam("username") String username,
            @RequestParam("password") String password, @RequestParam("cpassword") String confirmpass,
            @RequestParam("role") String role) throws Exception {

        if (password.equals(confirmpass)) {
            User user = new User(0, name, age, phone, email, cccd, address, username, password, role);
            uRepo.addNewUser(user);
            return "redirect:/login";
        } else {
            return "public/signup";
        }
    }
}
