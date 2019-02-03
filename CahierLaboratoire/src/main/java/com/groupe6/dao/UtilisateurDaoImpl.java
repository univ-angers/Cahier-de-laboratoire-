package com.groupe6.dao;

import static com.groupe6.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.groupe6.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.groupe6.beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

	private static final String SQL_SELECT_PAR_EMAIL = "SELECT * FROM utilisateur WHERE email = ?";
	private static final String SQL_SELECT_PAR_Id = "SELECT * FROM utilisateur WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO utilisateur (nom , prenom , motdepasse , email , isadmin) VALUES (?, ?, ?, ?, ?)";

	private DAOFactory daoFactory;

	UtilisateurDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public Utilisateur trouver(String email) throws DAOException {
		return trouver(SQL_SELECT_PAR_EMAIL, email);
	}

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public void creer(Utilisateur utilisateur) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			System.out.println(connexion.toString());
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, utilisateur.getNom() , utilisateur.getPrenom(), 
					utilisateur.getMotDePasse(), utilisateur.getEmail(), utilisateur.getIsAdmin());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				utilisateur.setId(valeursAutoGenerees.getLong(1));
			} else {
				throw new DAOException("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	/*
	 * Méthode générique utilisée pour retourner un utilisateur depuis la base de
	 * données, correspondant à la requête SQL donnée prenant en paramètres les
	 * objets passés en argument.
	 */
	private Utilisateur trouver(String sql, Object... objets) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Utilisateur utilisateur = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments (ici,
			 * uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (resultSet.next()) {
				utilisateur = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return utilisateur;
	}

	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le mapping)
	 * entre une ligne issue de la table des utilisateurs (un ResultSet) et un bean
	 * Utilisateur.
	 */
	private static Utilisateur map(ResultSet resultSet) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(resultSet.getLong("id"));
		utilisateur.setNom(resultSet.getString("nom"));
		utilisateur.setPrenom(resultSet.getString("prenom"));
		utilisateur.setMotDePasse(resultSet.getString("motdepasse"));
		utilisateur.setEmail(resultSet.getString("email"));
		utilisateur.setIsAdmin((resultSet.getString("isadmin").equals("1") ? 1 : 0 ));
		return utilisateur;
	}
}