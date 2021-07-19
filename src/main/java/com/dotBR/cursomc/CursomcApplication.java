package com.dotBR.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dotBR.cursomc.domain.Categoria;
import com.dotBR.cursomc.domain.Cidade;
import com.dotBR.cursomc.domain.Cliente;
import com.dotBR.cursomc.domain.Endereco;
import com.dotBR.cursomc.domain.Estado;
import com.dotBR.cursomc.domain.ItemPedido;
import com.dotBR.cursomc.domain.Pagamento;
import com.dotBR.cursomc.domain.PagamentoComBoleto;
import com.dotBR.cursomc.domain.PagamentoComCartão;
import com.dotBR.cursomc.domain.Pedido;
import com.dotBR.cursomc.domain.Produto;
import com.dotBR.cursomc.domain.enums.EstadoPagamento;
import com.dotBR.cursomc.domain.enums.TipoCliente;
import com.dotBR.cursomc.repositories.CategoriaRepository;
import com.dotBR.cursomc.repositories.CidadeRepository;
import com.dotBR.cursomc.repositories.ClienteRepository;
import com.dotBR.cursomc.repositories.EnderecoRepository;
import com.dotBR.cursomc.repositories.EstadoRepository;
import com.dotBR.cursomc.repositories.ItemPedidoRepository;
import com.dotBR.cursomc.repositories.PagamentoRepository;
import com.dotBR.cursomc.repositories.PedidoRepository;
import com.dotBR.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		Categoria c3 = new Categoria(null, "Cama, mesa e banho");
		Categoria c4 = new Categoria(null, "Eletronicos");
		Categoria c5 = new Categoria(null, "Jardinagem");
		Categoria c6 = new Categoria(null, "Decoração");
		Categoria c7 = new Categoria(null, "Perfumaria");
	

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));

		categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlândia", e1);
		Cidade cid2 = new Cidade(null, "São Paulo", e2);
		Cidade cid3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(cid1));
		e2.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "123", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("4321", "814611"));

		Endereco end1 = new Endereco(null, "Rua das Flores", "300", "Apto 303", "Jardim", "789456", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "612515", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);

		Pagamento pg1 = new PagamentoComCartão(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pg1);
		Pagamento pg2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pg2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pg1, pg2));
		clienteRepository.saveAll(Arrays.asList(cli1));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip3));
		
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	}

}
