package com.livro.capitulo3.locacao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.util.HibernateUtil;

public class LocacaoDAO {
	private Session sessao;
	private Transaction transacao;
	
	public void salvar(Locacao locacao) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(locacao);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir a locacao. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public void atualizar(Locacao locacao) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(locacao);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a locacao. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Locacao locacao) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(locacao);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a locacao. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public Locacao buscaLocacao(Integer codigo) {
		Locacao locacao = null;
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Locacao.class);
			filtro.add(Restrictions.eq("locacao", codigo));
			locacao = (Locacao)filtro.uniqueResult();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive())
				this.transacao.rollback();
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de busca. Mensagem: " + e.getMessage());
			}
		}
		return locacao;
	}
	
	public List<Locacao> listar() {
		List<Locacao> locacaos = null;
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Locacao.class);
			locacaos = filtro.list();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive())
				this.transacao.rollback();
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de listagem. Mensagem: " + e.getMessage());
			}
		}
		return locacaos;
	}
}
