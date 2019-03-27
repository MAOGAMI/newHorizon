package pl.mkpk.newhorizon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mkpk.newhorizon.model.User;
import pl.mkpk.newhorizon.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /***********/
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else if(user.getPassword().equals(user.getMatchingPassword())==false) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "passwords don't match");
        }else{
            userService.saveUser(user);
            System.out.println(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("register");

        }
        return modelAndView;
    }

    /**/

     @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
     public ModelAndView forgotPassword(/*@Valid User user, BindingResult bindingResult*/) {
         ModelAndView modelAndView = new ModelAndView();
         modelAndView.setViewName("forgot-password");
         return modelAndView;

         /*ModelAndView modelAndView = new ModelAndView();
         User userExists = userService.findUserByEmail(user.getEmail());
         if (userExists != null) {
             bindingResult
                     .rejectValue("email", "error.user",
                             "There is already a user registered with the email provided");
         }

         if (bindingResult.hasErrors()) {
             modelAndView.setViewName("forgot-password");
         } else {
             userService.saveUser(user);
             System.out.println(user);
             modelAndView.addObject("successMessage", "User has been registered successfully");
             modelAndView.addObject("user", new User());
             modelAndView.setViewName("forgot-password");

         }
         return modelAndView;*/
     }



    @RequestMapping(value = "/templates", method = RequestMethod.GET)
    public ModelAndView home(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getAuthorities());

        ModelAndView modelAndView = new ModelAndView();

        if(userDetails.getAuthorities().toString().contains("ADMIN")){
            //return "redirect:/templates/admin/home";
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());

            modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
            modelAndView.setViewName("admin/home");
            return modelAndView;

        }else if(userDetails.getAuthorities().toString().contains("USER")){
            //return "redirect:/templates/user/home";
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            modelAndView.addObject("userMessage", "Content Available Only for Users with User Role");
            modelAndView.setViewName("user/home");
            return modelAndView;
        }else{
            //return "redirect:/templates/dietetican/home";
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            modelAndView.addObject("dieteticanMessage", "Content Available Only for Users with Dietetican Role");
            modelAndView.setViewName("dietetican/home");
            return modelAndView;
        }
    }
}
