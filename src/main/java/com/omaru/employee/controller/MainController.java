package com.omaru.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping( path = {"","/"})
    public String index(){
        return "Hello world";
    }
}
