package com.gft.cybercoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.cybercoffee.model.ElementoReceita;

@Repository
public interface ElementoReceitaRepository extends JpaRepository<ElementoReceita, Long>{
}
