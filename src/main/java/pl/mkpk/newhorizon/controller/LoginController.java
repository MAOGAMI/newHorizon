package pl.mkpk.newhorizon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mkpk.newhorizon.model.User;
import pl.mkpk.newhorizon.service.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView postLogin(Authentication auth){
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        userDetails.getAuthorities();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        if (userDetails.getAuthorities().toString().contains("USER")){
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        user.setActive(1);
        userService.registerUser(user);
        return "redirect:login";
    }
}
