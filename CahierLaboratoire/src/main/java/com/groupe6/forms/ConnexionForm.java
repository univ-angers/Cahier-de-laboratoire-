package com.groupe6.forms;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.groupe6.beans.*;
import com.groupe6.dao.*;

public final class ConnexionForm {
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "motdepasse";

	private static final String ALGO_CHIFFREMENT = "SHA-256";
	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private UtilisateurDao utilisateurDao;

	public ConnexionForm(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateur connecterUtilisateur(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String motDePasse = getValeurChamp(request, CHAMP_PASS);
		
		System.out.println("email recupere: "+email);
		System.out.println("Mot de passe recupere: "+motDePasse);
		
		Utilisateur utilisateur = new Utilisateur();

		Utilisateur utilisateur_bdd = utilisateurDao.trouver(email);
		
		/* Validation du champ email. */
		try {
			validationEmail(email);
			utilisateur.setEmail(email);
			if(utilisateur_bdd == null) {
				throw new Exception("Utilisateur non enregistré (suprime celle la si utilisateur non null)");
			}
			if(!utilisateur_bdd.getEmail().equals(utilisateur.getEmail())){
				throw new Exception("Utilisateur non enregistré (suprime celle la si utilisateur est null)");
			}
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}

		/* Validation du champ mot de passe. */
		try {
			validationMotDePasse(motDePasse);
			ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
			passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
			passwordEncryptor.setPlainDigest(true);
			String motDePasseChiffre = passwordEncryptor.encryptPassword(motDePasse);
			System.out.println("Chiffrement: " +motDePasseChiffre);
			utilisateur.setMotDePasse(motDePasseChiffre);
			if(!utilisateur_bdd.getMotDePasse().equals(utilisateur.getMotDePasse())){
				throw new Exception("Mauvais mot de passe");
			}
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
		}

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()) {
			resultat = "Succès de la connexion.";
			utilisateur.setIsAdmin(utilisateur_bdd.getIsAdmin());
			utilisateur.setNom(utilisateur_bdd.getNom());
			utilisateur.setPrenom(utilisateur_bdd.getPrenom());
		} else {
			
			resultat = "Échec de la connexion.";
		}

		return utilisateur;
	}

	/**
	 * Valide l'adresse email saisie.
	 */
	private void validationEmail(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		}
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	private void validationMotDePasse(String motDePasse) throws Exception {
		if (motDePasse != null) {
			if (motDePasse.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}