package com.groupe6.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tag")
public class Tag {
	private Long idT;
	private Long idC;
	private Long idU;
	private String nomTag;
	
	public Tag() {}
	
	public Tag(Long idC, String nomTag) {
		
		this.idC = idC;
		this.nomTag = nomTag;
		System.out.println(this.idC);
	}
	
	
	@Id
	@Column(name="id_t")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdT() {
		return idT;
	}

	private void setIdT(Long idT) {
		this.idT = idT;
	}
	
	@Column(name="id_c")
	public Long getIdC() {
		return idC;
	}

	public void setIdC(Long idC) {
		this.idC = idC;
	}
	@Column(name="id_u")
	public Long getIdU() {
		return idU;
	}

	public void setIdU(Long idU) {
		this.idU = idU;
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
		String description = "Id Tag : " + getIdT() + "\n";
		description += " ID Catégorie : " + getIdC() + "\n";
		description += " ID User : " + getIdU() + "\n";
		description += "Nom du tag : " + getNomTag() + "\n";
		return description;
	}
}
