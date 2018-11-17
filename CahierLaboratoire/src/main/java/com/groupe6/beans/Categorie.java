package com.groupe6.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categorie")
public class Categorie{
	private Long idC;
	private String nomCategorie;
	
	public Categorie() {}
	
	public Categorie(Long idC, String nomCategorie){
		this.idC = idC;
		this.nomCategorie = nomCategorie;
	}

	@Id
	@Column(name="id_c")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdC() {
		return idC;
	}

	public void setIdC(Long idC) {
		this.idC = idC;
	}

	@Column(name="nom_categorie")
	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	
	@Override
	public String toString() {
		String description = "Categorie " + getIdC() + "\n";
		description += "Nom : " + getNomCategorie() + "\n";
		return description;
	}
}