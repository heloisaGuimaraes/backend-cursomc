package com.dotBR.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//classe com a ação do REST
import org.springframework.stereotype.Service;

import com.dotBR.cursomc.domain.Cliente;
import com.dotBR.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new com.dotBR.cursomc.services.exceptions.ObjectNotFoundException(
				"Pessoa não encontrada! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	public List<Cliente> allClientes() {
		List<Cliente> clientes = repo.findAll();
		return clientes;
	}

}
