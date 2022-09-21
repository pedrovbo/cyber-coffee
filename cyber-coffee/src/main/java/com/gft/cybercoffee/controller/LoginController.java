package com.gft.cybercoffee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gft.cybercoffee.config.SecurityConfig;
import com.gft.cybercoffee.model.CyberCoffeeUser;
import com.gft.cybercoffee.service.CyberCoffeeUserDetailsService;

@Controller
public class LoginController {

    @Autowired
    private CyberCoffeeUserDetailsService cyberCoffeeUserDetailsService;

    @GetMapping("/login")
    public ModelAndView login() {

        ModelAndView mv = new ModelAndView("login.html");

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {

        ModelAndView mv = new ModelAndView("logout.html");
        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        return mv;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/register")
    public ModelAndView register(@RequestParam(required = false) Long id) {

        ModelAndView mv = new ModelAndView("register.html");

        mv.addObject("user", new CyberCoffeeUser());
        mv.addObject("register", "register");
        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "register")
    public ModelAndView salvarUsuario(@Valid CyberCoffeeUser cyberCoffeeUser, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("register.html");
        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        if (bindingResult.hasErrors()) {
            mv = new ModelAndView("redirect:/login");
            mv.addObject("user", cyberCoffeeUser);
        }

        CyberCoffeeUser cyberCoffeeUserNovo = cyberCoffeeUserDetailsService.salvarUsuario(cyberCoffeeUser);

        if (cyberCoffeeUser.getId() != null) {
            mv.addObject("user", new CyberCoffeeUser());
        } else {
            mv.addObject("user", cyberCoffeeUserNovo);
        }

        mv.addObject("mensagem", "Sucesso!");

        return mv;
    }

}
