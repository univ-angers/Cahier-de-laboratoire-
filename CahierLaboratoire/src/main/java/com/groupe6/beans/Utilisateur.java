package com.groupe6.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements TableBD {
	private Long id;
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private int isAdmin;
    
    public Utilisateur() {
    	
    }

	public Utilisateur(String email, String motDePasse, String nom, String prenom) {
    	this.email = email;
    	this.motDePasse = motDePasse;
    	this.nom = nom;
    	this.prenom = prenom;
    }
 
    public void setEmail(String email) {
    	this.email = email;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
		return id;
	}
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    @Column(name = "email")
    public String getEmail() {
	return email;
    }

    public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
    }
    
    @Column(name = "motDePasse")
    public String getMotDePasse() {
	return motDePasse;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    @Column(name = "nom")
    public String getNom() {
	return nom;
    }

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Column(name = "prenom")
	public String getPrenom() {
		return prenom;
	}
	
	@Column(name = "isadmin")
	public int getIsAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	@Override
	public String toString() {
		String description = "Utilisateur " + getId() + "\n";
		description += "Nom : " + getNom() + ", Prénom : " + getPrenom() + "\n";
		description += "Email : " + getEmail() + ", MDP : " + getMotDePasse() + ", Admin " + getIsAdmin() + "\n";
		return description;
	}
}
//Modifier le fichier hibernate.cfg.xml pour la connexion à la base de données
