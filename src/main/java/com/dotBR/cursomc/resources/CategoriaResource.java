package com.dotBR.cursomc.resources;
//Classe com as definições REST

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dotBR.cursomc.domain.Categoria;
import com.dotBR.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias") // Endpoint que ela irá responder
public class CategoriaResource {

	@Autowired
	private CategoriaService serviceCategoria;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // Cada operação deve estar ligada a um verbo do http
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Categoria obj = serviceCategoria.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
