package com.gft.cybercoffee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.cybercoffee.config.SecurityConfig;
import com.gft.cybercoffee.model.ElementoReceita;
import com.gft.cybercoffee.model.Ingrediente;
import com.gft.cybercoffee.model.UnidadeMedida;
import com.gft.cybercoffee.service.ElementoReceitaService;
import com.gft.cybercoffee.service.IngredienteService;
import com.gft.cybercoffee.service.ReceitaService;
import com.gft.cybercoffee.service.UnidadeMedidaService;

@Controller
@RequestMapping("elemento_receita")
public class ElementoReceitaController {

    @Autowired
    private ElementoReceitaService elementoReceitaService;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private UnidadeMedidaService unidadeMedidaService;

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/editar")
    public ModelAndView editarElementoReceita(@RequestParam(required = false) Long id) {

        ModelAndView mv = new ModelAndView("elemento_receita/form.html");
        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        ElementoReceita elementoReceita;

        if (id == null) {
            elementoReceita = new ElementoReceita();
        } else {
            try {
                elementoReceita = elementoReceitaService.obterElementoReceita(id);
            } catch (Exception e) {
                elementoReceita = new ElementoReceita();
                mv.addObject("mensagem", "Erro ao editar o ingrediente da receita");
            }
        }
        // TODO: verificar necessidade
        mv.addObject("lista_quantidade", elementoReceitaService.listarQuantidades());
        mv.addObject("lista_unidade_medida", unidadeMedidaService.listarTodasUnidadeMedidas());
        mv.addObject("lista_ingrediente", ingredienteService.listarTodosIngredientes());
        mv.addObject("elementoreceita", elementoReceita);
        mv.addObject("lista", elementoReceitaService.listarElementosReceita());

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarElementoReceitaById(@PathVariable Long id) {

        ModelAndView mv = new ModelAndView("elemento_receita/form.html");
        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        ElementoReceita elementoReceita = new ElementoReceita();
        Ingrediente ingrediente = new Ingrediente();
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        elementoReceita.setIngrediente(ingrediente);
        elementoReceita.setUnidadeMedida(unidadeMedida);

        mv.addObject("elementoreceita", elementoReceita);

        try {
            mv.addObject("receita", receitaService.obterReceita(id));
            mv.addObject("unidadeMedida", unidadeMedida);
            mv.addObject("ingrediente", ingrediente);
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao editar o ingrediente da receita");
        }

        // TODO: verificar necessidade
        mv.addObject("lista_quantidade", elementoReceitaService.listarQuantidades());
        mv.addObject("lista_unidade_medida", unidadeMedidaService.listarTodasUnidadeMedidas());
        mv.addObject("lista_ingrediente", ingredienteService.listarTodosIngredientes());
        mv.addObject("lista", elementoReceitaService.listarElementosReceita());

        return mv;
    }

    @PostMapping("/editar/{id}")
    public ModelAndView salvarElementoReceita(@PathVariable("id") Long receita_id,
            @Valid ElementoReceita elementoReceita, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("redirect:/elemento_receita/" + receita_id);

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());


        try {
            elementoReceita.setReceita(receitaService.obterReceita(receita_id));
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao editar o ingrediente da receita");
        }

        boolean novo = true;

        if (elementoReceita.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("elementoreceita", elementoReceita);
            return mv;
        }

        elementoReceitaService.salvarElementoReceita(elementoReceita);

        if (novo) {
            mv.addObject("elementoreceita", new ElementoReceita());

        } else {
            mv.addObject("elementoreceita", elementoReceita);
        }

        redirectAttributes.addFlashAttribute("mensagem", "Ingrediente adicionado com sucesso.");

        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView listarElementoReceita(@PathVariable Long id) {

        ModelAndView mv = new ModelAndView("elemento_receita/listar.html");
        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        try {
            mv.addObject("lista", receitaService.obterReceita(id).getElementosReceita());
            mv.addObject("receita", receitaService.obterReceita(id));
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao acessar a lista de elementos da receita.");
        }

        return mv;

    }

    @RequestMapping("/excluir")
    public ModelAndView excluirElementoReceita(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        Long elementoReceita = 0L;
        ModelAndView mv = new ModelAndView();

        mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

        try {
            elementoReceita = elementoReceitaService.obterElementoReceita(id).getReceita().getId();
        } catch (Exception e2) {
            mv.addObject("mensagem", "Erro ao excluir ingrediente da receita");
        }

        mv = new ModelAndView("redirect:/elemento_receita/" + elementoReceita);

        try {
            elementoReceitaService.excluirElementoReceita(id);
            redirectAttributes.addFlashAttribute("mensagem", "Item excluído com sucesso.");
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao excluir item");
        }

        return mv;
    }

}
