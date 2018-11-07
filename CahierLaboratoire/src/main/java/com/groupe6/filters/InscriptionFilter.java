package com.groupe6.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupe6.beans.Utilisateur;

public class InscriptionFilter implements Filter {
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String REDIRECTION_PP  = "/restreint/pagePrincipale.jsp";

    public InscriptionFilter() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		 /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        
        
        Utilisateur utilisateur = (Utilisateur) (session.getAttribute("sessionUtilisateur"));
        
        if(utilisateur != null && utilisateur.getIsAdmin() == 0) {
 
            /* Redirection vers la page publique */
            request.getRequestDispatcher( REDIRECTION_PP ).forward( request, response );
        } else {
            /* Affichage de la page restreinte */
        	//request.getRequestDispatcher( ACCES_PAGEPRINCIPALE ).forward( request, response );
            chain.doFilter( request, response );
            
        }
        
        
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}




       





