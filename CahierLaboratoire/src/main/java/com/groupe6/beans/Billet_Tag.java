package com.groupe6.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "billet_tag")
public class Billet_Tag implements TableBD {
	private Long idB;
	private Long idT;
	
	public Billet_Tag() {}
	
	public Billet_Tag(Long idB, Long idT) {
		this.idB = idB;
		this.idT = idT;
	}

	@Id
	@Column (name = "id_b")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdB() {
		return idB;
	}

	public void setIdB(Long idB) {
		this.idB = idB;
	}
	
	@Column(name = "id_t")
	public Long getIdT() {
		return idT;
	}

	public void setIdT(Long idT) {
		this.idT = idT;
	}
	
	@Override
	public String toString() {
		String description = "Billet : " + getIdB() + "\n";
		description += "Tag : " + getIdT() + "\n";
		return description;
	}
	
}
