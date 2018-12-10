package com.groupe6.controller;

import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.groupe6.beans.Manager;
import com.groupe6.beans.Utilisateur;


@Controller()
public class UtilisateursController {
	
	//@Autowired
    Manager manager; 
    protected final Log logger = LogFactory.getLog(getClass());
    @RequestMapping(value = "/utilisateurs", method = RequestMethod.GET)
    public ModelAndView listProducts() {
    	manager = new Manager(); 
        logger.info("List of utilisateurs");
        Collection<Utilisateur> utilisateurs = manager.selectAllUsers();
        manager.exit();
        return new ModelAndView("utilisateurList", "utilisateurs", utilisateurs);
    }
}