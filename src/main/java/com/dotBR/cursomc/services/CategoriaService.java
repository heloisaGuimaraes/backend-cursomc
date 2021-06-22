package com.dotBR.cursomc.services;
//Classe para fornecer as respostas para o REST

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotBR.cursomc.domain.Categoria;
import com.dotBR.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired // injeção de dependencia//informar ao Spring que se trata de uma conexao
				// importante
	private CategoriaRepository repo;// Nossa ponte com o bd

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}

}