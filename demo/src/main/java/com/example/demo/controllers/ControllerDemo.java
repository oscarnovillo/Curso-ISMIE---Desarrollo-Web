package com.example.demo.controllers;


import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerDemo {



    @GetMapping("/demo")
    @ResponseBody
    public String index() {
        return "<html ><body><h1>index</h1></body></html>";
    }


    @GetMapping("/prueba")

    public String prueba(Model model) {
        model.addAttribute("name", "World");
        return "template";
    }
}
