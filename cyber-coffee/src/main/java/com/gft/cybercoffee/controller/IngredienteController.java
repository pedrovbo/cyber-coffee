package com.gft.cybercoffee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.cybercoffee.config.SecurityConfig;
import com.gft.cybercoffee.model.Ingrediente;
import com.gft.cybercoffee.service.IngredienteService;

@Controller
@RequestMapping("ingrediente")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public ModelAndView listarIngredientes(String nome) {

        ModelAndView mv = new ModelAndView("ingrediente/listar.html");

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        mv.addObject("lista", ingredienteService.listarIngrediente(nome));

        mv.addObject("nome", nome);

        return mv;

    }

    @GetMapping("/editar")
    public ModelAndView editarIngrediente(@RequestParam(required = false) Long id) {

        ModelAndView mv = new ModelAndView("ingrediente/form.html");

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        Ingrediente ingrediente;

        if (id == null) {
            ingrediente = new Ingrediente();
        } else {
            try {
                ingrediente = ingredienteService.obterIngrediente(id);
            } catch (Exception e) {
                ingrediente = new Ingrediente();
                mv.addObject("mensagem", e.getMessage());
            }
        }

        mv.addObject("ingrediente", ingrediente);
        mv.addObject("lista", ingredienteService.listarTodosIngredientes());

        return mv;
    }

    @PostMapping("/editar")
    public ModelAndView salvarIngrediente(@Valid Ingrediente ingrediente, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("ingrediente/form.html");

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        boolean novo = true;

        if (ingrediente.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("ingrediente", ingrediente);
            return mv;
        }

        ingredienteService.salvarIngrediente(ingrediente);

        if (novo) {
            mv.addObject("ingrediente", new Ingrediente());
        } else {
            mv.addObject("ingrediente", ingrediente);
        }

        mv.addObject("mensagem", "Ingrediente salvo com sucesso");
        mv.addObject("lista", ingredienteService.listarTodosIngredientes());

        return mv;
    }

    // TODO trazer mensagem para o usuário da impossibilidade de exclusão caso a
    // unidade de medida esteja sendo usada em algum receita
    @RequestMapping("/excluir")
    public ModelAndView excluirIngrediente(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("redirect:/ingrediente");

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        
        try {
            ingredienteService.excluirIngrediente(id);
            redirectAttributes.addFlashAttribute("mensagem", "Ingrediente excluído com sucesso.");
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao excluir Ingrediente" + e.getMessage());
        }

        return mv;
    }

}