package com.livro.capitulo3.midia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.livro.capitulo3.filme.Filme;

@Entity
@Table(name="midia")
public class Midia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4227181019874930690L;
	
	@Id
	@GeneratedValue
	@Column(name="cod_midia")
	private Integer midia;
	
	@ManyToOne
	@JoinColumn(name="cod_filme")
	private Filme filme;
	
	@Column(name="inutilizada")
	private boolean inutilizada;

	
	public Integer getMidia() {
		return midia;
	}

	public void setMidia(Integer midia) {
		this.midia = midia;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public boolean isInutilizada() {
		return inutilizada;
	}

	public void setInutilizada(boolean inutilizada) {
		this.inutilizada = inutilizada;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filme == null) ? 0 : filme.hashCode());
		result = prime * result + (inutilizada ? 1231 : 1237);
		result = prime * result + ((midia == null) ? 0 : midia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Midia)) {
			return false;
		}
		Midia other = (Midia) obj;
		if (filme == null) {
			if (other.filme != null) {
				return false;
			}
		} else if (!filme.equals(other.filme)) {
			return false;
		}
		if (inutilizada != other.inutilizada) {
			return false;
		}
		if (midia == null) {
			if (other.midia != null) {
				return false;
			}
		} else if (!midia.equals(other.midia)) {
			return false;
		}
		return true;
	}

	
	
}
