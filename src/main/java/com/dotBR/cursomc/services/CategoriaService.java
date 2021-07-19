package com.dotBR.cursomc.services;
//Classe para fornecer as respostas para o REST

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dotBR.cursomc.domain.Categoria;
import com.dotBR.cursomc.dto.CategoriaDTO;
import com.dotBR.cursomc.repositories.CategoriaRepository;
import com.dotBR.cursomc.services.exceptions.DataIntegrityException;

@Service
public class CategoriaService {

	@Autowired // injeção de dependencia//informar ao Spring que se trata de uma conexao
				// importante
	private CategoriaRepository repo;// Nossa ponte com o bd

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.dotBR.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	// byMe
	public List<CategoriaDTO> findAll() {
		List<Categoria> list = repo.findAll();
		List<CategoriaDTO> finalList = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return finalList;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);// caso o id seja igual a um que já existe é atualização
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		this.findById(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos cadastrados");
		}
	}

	public Page<CategoriaDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Categoria> list = repo.findAll(pageRequest);
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
		return listDTO;
	}
}
