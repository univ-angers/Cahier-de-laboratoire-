package com.groupe6.beans;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "billet")
public class Billet{
	private Long id;
    private String text;
    private Timestamp creation;
    private Date modification;
    
    public Billet() { }
    
    public Billet(String text) {
    	this.text = text;
    }
    
	@Id
	@Column(name = "id_b")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdB() {
		return id;
	}

	public void setIdB(Long id) {
		this.id = id;
	}

	@Column(name = "texte")
    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@CreationTimestamp
	@Column(name = "creation")
	public Timestamp getCreation() {
		return creation;
	}

	public void setCreation(Timestamp creation) {
		this.creation = creation;
	}

	@Column(name = "modification")
	public Date getModification() {
		return modification;
	}

	public void setModification(Date modification) {
		this.modification = modification;
	}
	
	@Override
	public String toString() {
		String description = "Billet " + getIdB() + "\n";
		description += "Texte : " + getText() + "\n";
		description += "Créé le : " + getCreation() + "\n";
		description += "Modifié le : " + getModification() + "\n";
		return description;
	}
}