package com.gft.cybercoffee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.cybercoffee.model.ElementoReceita;
import com.gft.cybercoffee.repository.ElementoReceitaRepository;

@Service
public class ElementoReceitaService {
    
    @Autowired
    private ElementoReceitaRepository elementoReceitaRepository;

	@Transactional
    public ElementoReceita salvarElementoReceita(ElementoReceita elementoReceita) {
		return elementoReceitaRepository.save(elementoReceita);
	}

	public List<ElementoReceita> listarElementosReceita() {
		return elementoReceitaRepository.findAll();
	}

	//TODO: criar salvar elemento receita vinculado ao ID da receita - recebe como parametros elemento e id da receita

	public ElementoReceita obterElementoReceita(Long id) throws Exception {

		Optional<ElementoReceita> elementoReceita = elementoReceitaRepository.findById(id);

		if (elementoReceita.isEmpty()) {
			throw new Exception("Este item não foi encontrado.");
		}

		return elementoReceita.get();
	}

	@Transactional
	public void excluirElementoReceita(Long id) {
		elementoReceitaRepository.deleteById(id);
	}

	@Transactional
	public void excluirTodosElementosReceita(){
		elementoReceitaRepository.deleteAll();
	}

	public List<Double> listarQuantidades(){
		
		List<Double> quantidades = new ArrayList<>();
		List<ElementoReceita> elementos = elementoReceitaRepository.findAll();

		elementos.forEach(i -> quantidades.add(i.getQuantidade()));

		return quantidades;
	}

	
	@Transactional
    public void salvarTodosElementosReceita(List<ElementoReceita> listaElementos) {
		elementoReceitaRepository.saveAll(listaElementos);
    }

	public void salvarElementoReceitaId(@Valid ElementoReceita elementoReceita, Long receita_id) {
		
		elementoReceitaRepository.save(elementoReceita);
		return ;
	}

}
