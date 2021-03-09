package com.example.security.controller;

import com.example.security.Entity.Role;
import com.example.security.Entity.User;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/signup")
    public String signupPage(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("roles",roleRepository.findAll());
        return "signup";
    }

    @RequestMapping("/register")
    public String signupPage(@ModelAttribute User user){
        /*List<Role> roles=roleRepository.findAll();
        user.setRoleList(roles);
        userRepository.save(user);
*/
        userService.saveUser(user);

        return "redirect:/login";
    }

}
