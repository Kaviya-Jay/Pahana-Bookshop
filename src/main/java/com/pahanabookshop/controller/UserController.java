package com.pahanabookshop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pahanabookshop.model.Cart;
import com.pahanabookshop.model.Category;
import com.pahanabookshop.model.UserDtls;
import com.pahanabookshop.service.CartService;
import com.pahanabookshop.service.CategoryService;
import com.pahanabookshop.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String home() {
        return "user/home";
    }

    @ModelAttribute
    public void getUserDetails(Principal b, Model m) {
        if (b != null) {
            String email = b.getName();
            UserDtls userDtls = userService.getUserByEmail(email);
            m.addAttribute("user", userDtls);
        }

        List<Category> allActiveCategory = categoryService.getAllActiveCategory();
        m.addAttribute("categorys", allActiveCategory);
    }

    @GetMapping("/addCart")
    public String addToCart(@RequestParam Integer bid, @RequestParam Integer uid,HttpSession session) {
        Cart saveCart = cartService.saveCart(bid, uid);

        if (ObjectUtils.isEmpty(saveCart)) {
            session.setAttribute("errorMsg", "Book add to cart failed");
        }else {
            session.setAttribute("succMsg", "Book added to cart");
        }
        return "redirect:/book/" + bid;
    }



}
