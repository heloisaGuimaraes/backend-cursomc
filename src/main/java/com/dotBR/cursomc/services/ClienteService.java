package com.dotBR.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
//classe com a ação do REST
import org.springframework.stereotype.Service;

import com.dotBR.cursomc.domain.Cliente;
import com.dotBR.cursomc.dto.ClienteDTO;
import com.dotBR.cursomc.repositories.ClienteRepository;
import com.dotBR.cursomc.services.exceptions.DataIntegrityException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new com.dotBR.cursomc.services.exceptions.ObjectNotFoundException(
				"Pessoa não encontrada! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	public List<ClienteDTO> findAll() {
		List<Cliente> list = repo.findAll();
		List<ClienteDTO> finalList = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return finalList;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma pessoa com pedidos cadastrados");
		}
	}

	public Page<ClienteDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Cliente> list = repo.findAll(pageRequest);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return listDTO;
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);

	}

}
