package com.gft.cybercoffee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.cybercoffee.model.ElementoReceita;
import com.gft.cybercoffee.model.Ingrediente;
import com.gft.cybercoffee.model.Receita;
import com.gft.cybercoffee.repository.ElementoReceitaRepository;
import com.gft.cybercoffee.repository.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private ElementoReceitaRepository elementoReceitaRepository;

	@Transactional
	public Receita salvarReceita(Receita receita) {
		return receitaRepository.save(receita);
	}

	public List<Receita> listarReceitas(String nome) {
		if (nome != null) {
			return listarReceitasPorNome(nome);
		}

		return listarTodasReceitas();
	}

	public List<Receita> listarTodasReceitas() {
		return receitaRepository.findAll();
	}

	public Receita obterReceita(Long id) throws Exception {

		Optional<Receita> receita = receitaRepository.findById(id);

		if (receita.isEmpty()) {
			throw new Exception("Receita não encontrada.");
		}

		return receita.get();
	}

	@Transactional
	public void excluirReceita(Long id) {
		receitaRepository.deleteById(id);
	}

	public List<Receita> listarReceitasPorNome(String nome) {
		return receitaRepository.findByNomeContains(nome);
	}

	@Transactional
	public void excluirTodasReceitas() {
		receitaRepository.deleteAll();
	}

	// TODO: passar id pra puxar ingredientes da receita
	public List<Ingrediente> listarIngredientesReceita() {
		List<Ingrediente> ingredientes = new ArrayList<>();
		elementoReceitaRepository.findAll().forEach(i -> ingredientes.add(i.getIngrediente()));
		return ingredientes;

	}

	// TODO: fazer metodo pra retornar elementos receita por id da receita
	public List<ElementoReceita> listarElementosReceita() {
		return elementoReceitaRepository.findAll();
	}

	public List<String> imprimirListaElementosReceita() {
		List<ElementoReceita> elementos = listarElementosReceita();
		List<String> elementosImpressos = new ArrayList<>();

		for (ElementoReceita i : elementos) {
			elementosImpressos
					.add(i.getQuantidade() + " " + i.getUnidadeMedida().getNome() + " " + i.getIngrediente().getNome());
		}

		return elementosImpressos;
	}

	@Transactional
	public void salvarTodasReceitas(List<Receita> receitas) {
		receitaRepository.saveAll(receitas);
	}

}
