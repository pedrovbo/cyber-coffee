package com.gft.cybercoffee.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.gft.cybercoffee.model.ElementoReceita;
import com.gft.cybercoffee.model.Receita;
import com.gft.cybercoffee.service.ElementoReceitaService;
import com.gft.cybercoffee.service.IngredienteService;
import com.gft.cybercoffee.service.ReceitaService;
import com.gft.cybercoffee.service.UnidadeMedidaService;

@Controller
@RequestMapping("receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private ElementoReceitaService elementoReceitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private UnidadeMedidaService unidadeMedidaService;

    @GetMapping
    public ModelAndView listarReceitas(String nome) {

        ModelAndView mv = new ModelAndView("receita/listar.html");

        mv.addObject("lista", receitaService.listarReceitas(nome));
        mv.addObject("nome", nome);

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        return mv;

    }

    @GetMapping(path = "/editar")
    public ModelAndView editarReceita(@RequestParam(required = false) Long id) {

        ModelAndView mv = new ModelAndView("receita/form.html");

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        List<ElementoReceita> elementosReceita = new ArrayList<>();
        Receita receita;

        if (id == null) {
            receita = new Receita();
        } else {
            try {
                receita = receitaService.obterReceita(id);
                elementosReceita = receitaService.obterReceita(receita.getId()).getElementosReceita();
            } catch (Exception e) {
                receita = new Receita();
                mv.addObject("mensagem", "Erro ao editar receita");
            }
        }

        mv.addObject("elementosreceita", elementosReceita);
        mv.addObject("receita", receita);
        mv.addObject("lista", receitaService.listarTodasReceitas());

        return mv;
    }

    @PostMapping("/editar")
    public ModelAndView salvarReceita(@Valid Receita receita, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("receita/form.html");

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        boolean novo = true;

        if (receita.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("receita", receita);
            return mv;
        }

        receitaService.salvarReceita(receita);

        if (novo) {
            mv.addObject("receita", new Receita());
        } else {
            mv.addObject("receita", receita);
        }

        mv.addObject("mensagem", "Receita salva com sucesso");
        mv.addObject("lista", receitaService.listarTodasReceitas());

        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirReceita(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("redirect:/receita");

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        try {
            receitaService.excluirReceita(id);
            redirectAttributes.addFlashAttribute("mensagem", "Receita excluída com sucesso.");
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao excluir Receita" + e.getMessage());
        }

        return mv;
    }

}
