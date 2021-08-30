package com.dotBR.cursomc.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotBR.cursomc.domain.ItemPedido;
import com.dotBR.cursomc.domain.PagamentoComBoleto;
import com.dotBR.cursomc.domain.Pedido;
import com.dotBR.cursomc.domain.enums.EstadoPagamento;
import com.dotBR.cursomc.repositories.ItemPedidoRepository;
import com.dotBR.cursomc.repositories.PagamentoRepository;
import com.dotBR.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

//GET
	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = repo.findById(id);
		return pedido.orElseThrow(() -> new com.dotBR.cursomc.services.exceptions.ObjectNotFoundException(
				"Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public List<Pedido> findAllPedidos() {
		List<Pedido> pedidos = repo.findAll();
		return pedidos;
	}

//POST
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.findById(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;

	}
}
