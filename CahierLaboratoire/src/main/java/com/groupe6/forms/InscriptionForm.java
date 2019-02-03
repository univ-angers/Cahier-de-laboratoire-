package com.groupe6.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.groupe6.beans.*;
import com.groupe6.dao.*;


public final class InscriptionForm {
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_PASS = "motdepasse";
	private static final String CHAMP_CONF = "confirmation";
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_ADMIN = "admincheck";
	private static final String CHAMP_TAG = "tag";
	private static final String ALGO_CHIFFREMENT = "SHA-256";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private UtilisateurDao utilisateurDao;

	public InscriptionForm(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Utilisateur inscrireUtilisateur(HttpServletRequest request) {
		String nom = getValeurChamp(request, CHAMP_NOM);
		String prenom = getValeurChamp(request, CHAMP_PRENOM);
		String motDePasse = getValeurChamp(request, CHAMP_PASS);
		String confirmation = getValeurChamp(request, CHAMP_CONF);
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String admin = getValeurChamp(request,CHAMP_ADMIN);
		String nomTag = getValeurChamp(request, CHAMP_TAG); 
		int isadmin = 0;
		try {
			if(admin.equals("on")) {
				isadmin = 1;
			}
			else 
				isadmin = 0;

		} catch (Exception e1) {
		//e1.printStackTrace();
		}

		Utilisateur utilisateur = new Utilisateur();
		try {
			traiterNom(nom, utilisateur);
			traiterPrenom(prenom, utilisateur);
			traiterMotsDePasse(motDePasse, confirmation, utilisateur);
			traiterEmail(email, utilisateur);
			utilisateur.setIsAdmin(isadmin);
			
			if (erreurs.isEmpty()) {
				utilisateurDao.creer(utilisateur);
				resultat = "Succes";
				System.out.println(utilisateur.getId());
				Manager manager = new Manager(); 
		        try{
					Tag tag = new Tag(manager.selectCategory("Utilisateur").getIdC(), nomTag);
					tag.setIdU(utilisateur.getId());
					manager.createTag(tag);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				resultat = "Echec";
			}
		} catch (DAOException e) {
			resultat = "Echec de l'inscription : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
			e.printStackTrace();
		}
		

		return utilisateur;
	}
	

	/*
	 * Appel à la validation de l'adresse email reçue et initialisation de la
	 * propri�t� email du bean
	 */
	private void traiterEmail(String email, Utilisateur utilisateur) {
		try {
			validationEmail(email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		utilisateur.setEmail(email);
	}

	/*
	 * Appel à la validation des mots de passe reçus, chiffrement du mot de passe et
	 * initialisation de la propri�t� motDePasse du bean
	 */
	private void traiterMotsDePasse(String motDePasse, String confirmation, Utilisateur utilisateur) {
		try {
			validationMotsDePasse(motDePasse, confirmation);
		} catch (FormValidationException e) {
			setErreur(CHAMP_PASS, e.getMessage());
			setErreur(CHAMP_CONF, null);
		}

		/*
		 * Utilisation de la biblioth�que Jasypt pour chiffrer le mot de passe
		 * efficacement.
		 * 
		 * L'algorithme SHA-256 est ici utilis�, avec par d�faut un salage al�atoire et
		 * un grand nombre d'it�rations de la fonction de hashage.
		 * 
		 * La String retourn�e est de longueur 56 et contient le hash en Base64.
		 */
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
		passwordEncryptor.setPlainDigest(true);
		String motDePasseChiffre = passwordEncryptor.encryptPassword(motDePasse);
		System.out.println("INSCRIPTION "+motDePasseChiffre);
		utilisateur.setMotDePasse(motDePasseChiffre);
	}

	/*
	 * Appel à la validation du nom reçu et initialisation de la propri�t� nom du
	 * bean
	 */
	private void traiterNom(String nom, Utilisateur utilisateur) {
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		utilisateur.setNom(nom);
	}
	private void traiterPrenom(String prenom, Utilisateur utilisateur) {
		try {
			validationPrenom(prenom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_PRENOM, e.getMessage());
		}
		utilisateur.setPrenom(prenom);
	}

	/* Validation de l'adresse email */
	private void validationEmail(String email) throws FormValidationException {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new FormValidationException("Merci de saisir une adresse mail valide.");
			} else if (utilisateurDao.trouver(email) != null) {
				throw new FormValidationException(
						"Cette adresse email est d�jà utilis�e, merci d'en choisir une autre.");
			}
		} else {
			throw new FormValidationException("Merci de saisir une adresse mail.");
		}
	}

	/* Validation des mots de passe */
	private void validationMotsDePasse(String motDePasse, String confirmation) throws FormValidationException {
		if (motDePasse != null && confirmation != null) {
			if (!motDePasse.equals(confirmation)) {
				throw new FormValidationException(
						"Les mots de passe entr�s sont diff�rents, merci de les saisir à nouveau.");
			} else if (motDePasse.length() < 3) {
				throw new FormValidationException("Les mots de passe doivent contenir au moins 3 caract�res.");
			}
		} else {
			throw new FormValidationException("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	/* Validation du nom */
	private void validationNom(String nom) throws FormValidationException {
		if (nom != null && nom.length() < 3) {
			throw new FormValidationException("Le nom d'utilisateur doit contenir au moins 3 caract�res.");
		}
	}
	private void validationPrenom(String prenom) throws FormValidationException {
		if (prenom != null && prenom.length() < 3) {
			throw new FormValidationException("Le prenom d'utilisateur doit contenir au moins 3 caract�res.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ sp�cifi� à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	/*
	 * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
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