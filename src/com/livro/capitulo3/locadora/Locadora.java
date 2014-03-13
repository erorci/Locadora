package com.livro.capitulo3.locadora;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.livro.capitulo3.categoria.Categoria;
import com.livro.capitulo3.categoria.CategoriaDAO;
import com.livro.capitulo3.cliente.Cliente;
import com.livro.capitulo3.cliente.ClienteDAO;
import com.livro.capitulo3.endereco.Endereco;
import com.livro.capitulo3.endereco.EnderecoDAO;
import com.livro.capitulo3.filme.Filme;
import com.livro.capitulo3.filme.FilmeDAO;
import com.livro.capitulo3.locacao.Locacao;
import com.livro.capitulo3.locacao.LocacaoDAO;
import com.livro.capitulo3.midia.Midia;
import com.livro.capitulo3.midia.MidiaDAO;
import com.livro.capitulo3.util.HibernateUtil;

public class Locadora {

	public static void main(String[] args) {

		HibernateUtil.getSessionFactory().openSession();
		Locadora locadora = new Locadora();

		locadora.cadastraCategorias();
		locadora.cadastraFilmes();
		locadora.cadastraMidias();
		
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = new Endereco();
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
		cliente.setCelular("(12) 1111-2222");
		cliente.setEmail("teste@teste.com.br");
		cliente.setNome("Fulano Solaris");
		cliente.setTelefone("(12) 1111-3333");
		cliente.setEndereco(endereco);
		endereco.setBairro("Centro");
		endereco.setCep("18999-000");
		endereco.setCidade("São Paulo");
		endereco.setComplemento("casa");
		endereco.setNumero(new Integer(200));
		endereco.setRua("Av. Marginal");
		endereco.setUf("SP");
		endereco.setCliente(cliente);
		clienteDAO.salvar(cliente);
		enderecoDAO.salvar(endereco);
		
		LocacaoDAO dao = new LocacaoDAO();
		Locacao locacao = new Locacao();
		locacao.setDataDevolucao(new Date(System.currentTimeMillis()));
		locacao.setDataEmprestimo(new Date(System.currentTimeMillis()));
		locacao.setObservacao("Devolução final de semana");
		locacao.setHoraEmprestimo(new Time(System.currentTimeMillis()));
		
		locacao.setCliente(cliente);
		
		MidiaDAO midiaDAO = new MidiaDAO();
		Midia midia = (Midia) midiaDAO.buscaCategoria(new Integer(1));
		locacao.setMidia(midia);
		dao.salvar(locacao);
		System.out.println("Locação efetuada com sucesso");
		
		
		
	}

	private void cadastraMidias() {

		Midia midia = null;
		Filme filme = null;
		MidiaDAO midiaDAO = new MidiaDAO();
		FilmeDAO filmeDAO = new FilmeDAO();
		List<Filme> resultado = filmeDAO.listar();
		for (int i = 0; i < 3; i++) {
			midia = new Midia();
			filme = (Filme)resultado.get(i);
			midia.setFilme(filme);
			midia.setInutilizada(false);
			midiaDAO.salvar(midia);
		}
	}

	private void cadastraFilmes() {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		String[] filmesDescricao = { "Senhor dos Anéis", "Transformers",
				"Ghostbusters" };
		Date[] filmesAnoProducao = { new Date(2001 - 1900, 11, 19),
				new Date(2007 - 1900, 6, 20), new Date(1985 - 1900, 1, 1) };
		FilmeDAO filmeDAO = new FilmeDAO();
		Filme filme = null;
		for (int i = 0; i < 3; i++) {
			filme = new Filme();
			filme.setDescricao(filmesDescricao[i]);
			filme.setAno(filmesAnoProducao[i]);
			filme.setCategoria(categoriaDAO.buscaCategoria(i + 1));
			filmeDAO.salvar(filme);
		}
	}

	private void cadastraCategorias() {

		String categorias[] = { "Aventura", "Ação", "Comédia" };
		Categoria categoria = null;
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		for (int i = 0; i < 3; i++) {
			categoria = new Categoria();
			categoria.setDescricao(categorias[i]);
			categoriaDAO.salvar(categoria);
		}
	}
}
