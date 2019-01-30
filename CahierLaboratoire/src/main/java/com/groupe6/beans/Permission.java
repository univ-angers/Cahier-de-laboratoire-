package com.groupe6.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission {

	private Long id;
	private String name;
	private Long idBillet;
	private Long idUser;
	
	
	public Permission() {

	}

	public Permission(String name, Long idBillet, Long idUser) {
		super();
		this.name = name;
		this.idBillet = idBillet;
		this.idUser = idUser;
	}
		
	@Id
	@Column (name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "id_billet")
	public Long getIdBillet() {
		return idBillet;
	}
	public void setIdBillet(Long idBillet) {
		this.idBillet = idBillet;
	}
	
	@Column(name = "id_user")
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
}
