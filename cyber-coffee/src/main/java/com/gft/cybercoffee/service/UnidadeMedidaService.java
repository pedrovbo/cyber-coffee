package com.gft.cybercoffee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.cybercoffee.model.UnidadeMedida;
import com.gft.cybercoffee.repository.UnidadeMedidaRepository;

@Service
public class UnidadeMedidaService {

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;

	public UnidadeMedida salvarUnidadeMedida(UnidadeMedida medida) {
		return unidadeMedidaRepository.save(medida);
	}

	public List<UnidadeMedida> listarUnidadeMedidas(String nome) {
		if (nome != null) {
			return listarUnidadeMedidaPorNome(nome);
		}

		return listarTodasUnidadeMedidas();
	}

	public List<UnidadeMedida> listarTodasUnidadeMedidas() {
		return unidadeMedidaRepository.findAll();
	}

	public UnidadeMedida obterUnidadeMedida(Long id) throws Exception {

		Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(id);

		if (unidadeMedida.isEmpty()) {
			throw new Exception("Unidade de Medida não encontrada.");
		}

		return unidadeMedida.get();
	}

	public void excluirUnidadeMedida(Long id) {
		unidadeMedidaRepository.deleteById(id);
	}

	public List<UnidadeMedida> listarUnidadeMedidaPorNome(String nome) {
		return unidadeMedidaRepository.findByNomeContains(nome);
	}

	public void excluirTodasUnidadesDeMedida(){
		unidadeMedidaRepository.deleteAll();
	}

    public void salvarTodasUnidadesMedida(List<UnidadeMedida> unidadesMedida) {
		unidadeMedidaRepository.saveAll(unidadesMedida);
    }
}
