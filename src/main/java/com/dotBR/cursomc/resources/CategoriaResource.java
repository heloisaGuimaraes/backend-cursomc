package com.dotBR.cursomc.resources;
//Classe com as definições REST

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dotBR.cursomc.domain.Categoria;
import com.dotBR.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias") // Endpoint que ela irá responder
public class CategoriaResource {

	@Autowired
	private CategoriaService serviceCategoria;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // Cada operação deve estar ligada a um verbo do http
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria obj = serviceCategoria.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// byMe
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> allCategorias() {
		List<Categoria> list = serviceCategoria.findAll();
		return ResponseEntity.ok(list);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = serviceCategoria.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria obj) {
		obj.setId(id);//garantir a atualização
		serviceCategoria.update(obj);
		return ResponseEntity.noContent().build();
	}
}
