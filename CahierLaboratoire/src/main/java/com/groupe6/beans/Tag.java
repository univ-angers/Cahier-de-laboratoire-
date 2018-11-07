package com.groupe6.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tag")
public class Tag implements TableBD {
	private Long idT;
	private Long idC;
	private String nomTag;
	
	public Tag() {}
	
	public Tag(Long idT, Long idC, String nomTag) {
		this.idT = idT;
		this.idC = idC;
		this.nomTag = nomTag;
	}

	@Id
	@Column(name="id_t")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdT() {
		return idT;
	}

	public void setIdT(Long idT) {
		this.idT = idT;
	}
	
	@Column(name="id_c")
	public Long getIdC() {
		return idC;
	}

	public void setIdC(Long idC) {
		this.idC = idC;
	}

	@Column(name="nom_tag")
	public String getNomTag() {
		return nomTag;
	}

	public void setNomTag(String nomTag) {
		this.nomTag = nomTag;
	}

	@Override
	public String toString() {
		String description = "Tag : " + getIdT() + "\n";
		description += "Catégorie : " + getIdC() + "\n";
		description += "Nom de la catégorie : " + getNomTag() + "\n";
		return description;
	}
	
}
