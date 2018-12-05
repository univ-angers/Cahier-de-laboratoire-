package com.groupe6.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.groupe6.beans.Utilisateur;
import com.groupe6.dao.DAOFactory;
import com.groupe6.dao.UtilisateurDao;
import com.groupe6.forms.ConnexionForm;

@WebServlet( urlPatterns = "/connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_INTERVALLE_CONNEXIONS = "intervalleConnexions";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE = "/WEB-INF/connexion.jsp";
	public static final String ACCUEIL = "/accueil";

	private UtilisateurDao utilisateurDao;
	
	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		/* Affichage de la page de connexion */

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ConnexionForm form = new ConnexionForm(utilisateurDao);
		/* Traitement de la requête et récupération du bean en résultant */
		Utilisateur utilisateur = form.connecterUtilisateur(request);
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();
		
		
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.reset(); 
		/*
		 * Si aucune erreur de validation n'a eu lieu, alors ajout du bean Utilisateur à
		 * la session, sinon suppression du bean de la session.
		 */
		if (form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, utilisateur);
			response.sendRedirect(request.getContextPath() +ACCUEIL);
			return; 
			
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
		}


		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

}