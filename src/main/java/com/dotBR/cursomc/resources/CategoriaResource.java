package com.dotBR.cursomc.resources;
//Classe com as definições REST

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dotBR.cursomc.domain.Categoria;
import com.dotBR.cursomc.dto.CategoriaDTO;
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

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> allCategorias() {
		List<CategoriaDTO> list = serviceCategoria.findAll();
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = serviceCategoria.fromDTO(objDto);
		obj = serviceCategoria.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = serviceCategoria.fromDTO(objDto);
		obj.setId(id);// garantir a atualização
		serviceCategoria.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // Cada operação deve estar ligada a um verbo do
																	// http
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		serviceCategoria.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, // 24 é legal pq é multiplo
																								// de 1, 2, 3 e 4
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<CategoriaDTO> list = serviceCategoria.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
