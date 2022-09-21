package com.gft.cybercoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gft.cybercoffee.config.SecurityConfig;
import com.gft.cybercoffee.model.Receita;
import com.gft.cybercoffee.service.ReceitaService;

@Controller
@RequestMapping("imagens")
public class ImagemReceitaController {
    // TODO

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/")
    public ModelAndView logout(@RequestParam(required = false) Long id) {

        ModelAndView mv = new ModelAndView("receita_detalhes.html");
        Receita receita;
        try {
            receita = receitaService.obterReceita(id);
            mv.addObject("receita", receita);
        } catch (Exception e1) {
            mv.addObject("message", "Receita não encontrada.");
        }
        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        return mv;
    }

}
