package com.intro.users.demo.controllers;

import com.intro.users.demo.facades.UserFacade;
import com.intro.users.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class MainController {
    @Autowired
    private UserFacade userFacade;

    @GetMapping(path = "/")
    public ModelAndView getAllUsers() {
        List<User> users = userFacade.getAllUser();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
