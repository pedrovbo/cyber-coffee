package com.gft.cybercoffee.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.cybercoffee.model.Ingrediente;
import com.gft.cybercoffee.repository.IngredienteRepository;

@Service
public class IngredienteService {
    
    @Autowired
    private IngredienteRepository ingredienteRepository;

	@Transactional
    public Ingrediente salvarIngrediente(Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}

	@Transactional
	public List<Ingrediente> salvarTodosIngredientes(List<Ingrediente> ingredientes){
		return ingredienteRepository.saveAll(ingredientes);
	}

	public List<Ingrediente> listarIngrediente(String nome) {
		if (nome != null) {
			return listarIngredientesPorNome(nome);
		}

		return listarTodosIngredientes();
	}

	public List<Ingrediente> listarTodosIngredientes() {
		return ingredienteRepository.findAll();
	}

	public Ingrediente obterIngrediente(Long id) throws Exception {

		Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);

		if (ingrediente.isEmpty()) {
			throw new Exception("Ingrediente não encontrado.");
		}

		return ingrediente.get();
	}

	@Transactional
	public void excluirIngrediente(Long id) {
		ingredienteRepository.deleteById(id);
	}

	public List<Ingrediente> listarIngredientesPorNome(String nome) {
		return ingredienteRepository.findByNomeContains(nome);
	}

	@Transactional
	public void excluirTodosIngredientes(){
		ingredienteRepository.deleteAll();
	}

	
}
