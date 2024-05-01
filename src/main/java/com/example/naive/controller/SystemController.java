package com.example.naive.controller;

import com.example.naive.annotation.MyResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 29002
 */
@CrossOrigin
@RestController
@RequestMapping("/system")
@MyResponseBody
public class SystemController {


    @GetMapping("/ping")
    public Object ping() {
        return "pong";
    }
}
