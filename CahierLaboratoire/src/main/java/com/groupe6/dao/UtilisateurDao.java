package com.groupe6.dao;

import java.util.List;

import com.groupe6.beans.Utilisateur;

public interface UtilisateurDao {

	void creer(Utilisateur utilisateur) throws DAOException;

	Utilisateur trouver(String email) throws DAOException;
	

}