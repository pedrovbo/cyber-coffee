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
import com.gft.cybercoffee.model.UnidadeMedida;
import com.gft.cybercoffee.service.UnidadeMedidaService;

@Controller
@RequestMapping("unidademedida")
public class UnidadeMedidaController {

	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@GetMapping("/editar")
	public ModelAndView editarUnidadeMedida(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("unidade_medida/form.html");

		mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

		UnidadeMedida unidadeMedida;

		if (id == null) {
			unidadeMedida = new UnidadeMedida();
		} else {
			try {
				unidadeMedida = unidadeMedidaService.obterUnidadeMedida(id);
			} catch (Exception e) {
				unidadeMedida = new UnidadeMedida();
				mv.addObject("mensagem", e.getMessage());
			}
		}

		mv.addObject("unidadeMedida", unidadeMedida);
		mv.addObject("lista", unidadeMedidaService.listarTodasUnidadeMedidas());

		return mv;
	}

	@PostMapping(path = "editar")
	public ModelAndView salvarUnidadeMedida(@Valid UnidadeMedida unidadeMedida, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("unidade_medida/form.html");

		mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

		boolean novo = true;

		if (unidadeMedida.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("unidadeMedida", unidadeMedida);
			return mv;
		}

		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida);

		if (novo) {
			mv.addObject("unidadeMedida", new UnidadeMedida());
		} else {
			mv.addObject("unidadeMedida", unidadeMedida);
		}

		mv.addObject("mensagem", "Unidade de Medida salva com sucesso");
		mv.addObject("lista", unidadeMedidaService.listarTodasUnidadeMedidas());

		return mv;
	}

	@RequestMapping
	public ModelAndView listarUnidadeMedida(String nome) {

		ModelAndView mv = new ModelAndView("unidade_medida/listar.html");

		mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

		mv.addObject("lista", unidadeMedidaService.listarUnidadeMedidas(nome));

		mv.addObject("nome", nome);

		return mv;

	}

	// TODO trazer mensagem para o usuário da impossibilidade de exclusão caso a
	// unidade de medida esteja sendo usada em algum receita
	@RequestMapping("/excluir")
	public ModelAndView excluirUnidadeMedida(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/unidademedida");

		mv.addObject("verifyAuthority", SecurityConfig.verifyAuthority());

		try {
			unidadeMedidaService.excluirUnidadeMedida(id);
			redirectAttributes.addFlashAttribute("mensagem", "Unidade de Medida excluída com sucesso.");
		} catch (Exception e) {
			mv.addObject("mensagem", "Erro ao excluir Unidade de Medida" + e.getMessage());
		}

		return mv;
	}

}
