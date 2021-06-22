package com.dotBR.cursomc.resources;
//Classe com as definições REST

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dotBR.cursomc.domain.Categoria;

@RestController
@RequestMapping (value = "/categorias")//Endpoint que ela irá responder
public class CategoriaResource {
	
	
	@RequestMapping (method = RequestMethod.GET)//Cada operação deve estar ligada a um verbo do http
	public List<Categoria> todasAsCategorias() {
		Categoria c1 = new Categoria (1, "A");
		Categoria c2 = new Categoria (2, "B");
		
		List<Categoria> cats = new ArrayList<>();
		
		cats.add(c1);
		cats.add(c2);
		
		return cats;
	}

}
