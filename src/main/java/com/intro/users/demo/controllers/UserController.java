package com.intro.users.demo.controllers;

import com.intro.users.demo.facades.UserFacade;
import com.intro.users.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private static final String EDIT_STRING = "edit";
    private static final String USER_STRING = "user";
    private static final String SLASH_STRING = "/";
    private static final String EDIT_USER_VIEW = "edit_user";
    private static final String INDEX_VIEW = "index";
    private static final String REDIRECT = "redirect:";
    
    @Autowired
    private UserFacade userFacade;

    @Autowired
    @Qualifier("userValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping("/new")
    public ModelAndView newUser() {
        User user = new User();
        return getModelAndView(user);
    }
    
    @RequestMapping(path = "/edit/{userId}")
    public ModelAndView editUser(@PathVariable("userId") int id) {
        User user = userFacade.getUserById(id);
        return getModelAndView(user);
    }

    private ModelAndView getModelAndView(User user) {
        ModelAndView modelAndView = new ModelAndView(EDIT_USER_VIEW);
        modelAndView.addObject(USER_STRING, user);
        return modelAndView;
    }

    @PostMapping(path = "/save")
    public ModelAndView updateUser(@ModelAttribute(USER_STRING) @Validated User user, BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView(REDIRECT + SLASH_STRING, model.asMap());
        User existUser = userFacade.getUserBySecureNumber(user.getSecureNumber());
        if (existUser != null && !existUser.getId().equals(user.getId())) {
            bindingResult.rejectValue("secureNumber", "error.secureNumber", null,
                    "There is already a user registered with the Secure Number provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(EDIT_USER_VIEW);
        } else {
            userFacade.createUser(user);
        }
        return modelAndView;
    }

    @RequestMapping(path = "/delete/{userId}")
    public View deleteUser(@PathVariable("userId") int id) {
        userFacade.deleteUser(id);
        return new RedirectView(SLASH_STRING);
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<User> result = userFacade.search(keyword);
        ModelAndView modelAndView = new ModelAndView(INDEX_VIEW);
        modelAndView.addObject("users", result);
        return modelAndView;
    }
}
