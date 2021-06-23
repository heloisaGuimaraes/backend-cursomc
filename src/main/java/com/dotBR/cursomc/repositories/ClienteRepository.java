package com.dotBR.cursomc.repositories;
//Inteface capaz de se comunicar com o banco (CRUD)

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dotBR.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
