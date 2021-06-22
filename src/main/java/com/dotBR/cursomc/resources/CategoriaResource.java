package com.dotBR.cursomc.resources;
//Classe com as definições REST

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/categorias")//Endpoint que ela irá responder
public class CategoriaResource {
	
	
	@RequestMapping (method = RequestMethod.GET)//Cada operação deve estar ligada a um verbo do http
	public String todasAsCategorias() {
		return "REST funciona!";
	}

}
