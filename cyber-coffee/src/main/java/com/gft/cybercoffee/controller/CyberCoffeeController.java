package com.gft.cybercoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.cybercoffee.config.SecurityConfig;
import com.gft.cybercoffee.service.ReceitaService;

@RequestMapping
@Controller
public class CyberCoffeeController {

    @Autowired
    ReceitaService receitaService;

    @GetMapping("/")
    public ModelAndView home(String nome) {

        ModelAndView mv = new ModelAndView("home.html");
        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());
        mv.addObject("receitas", receitaService.listarReceitas(nome));
        return mv;

    }

    

    //TODO terminar método pra fazer o upload da imagem
    
}
