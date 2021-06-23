package com.dotBR.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dotBR.cursomc.domain.Cliente;
import com.dotBR.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Cliente obj = clienteService.findById(id);
		return ResponseEntity.ok(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> allClientes() {
		List<Cliente> list = clienteService.allClientes();
		return ResponseEntity.ok(list);
	}

}
