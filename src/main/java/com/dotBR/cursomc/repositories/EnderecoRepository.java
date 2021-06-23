package com.dotBR.cursomc.repositories;
//Inteface capaz de se comunicar com o banco (CRUD)

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dotBR.cursomc.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
