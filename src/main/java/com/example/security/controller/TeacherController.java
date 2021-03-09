package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @RequestMapping("/home")
    public String showHome(){
        return "teacher/teacher-index";
    }
}
