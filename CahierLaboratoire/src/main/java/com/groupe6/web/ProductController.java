package com.groupe6.web;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.groupe6.beans.Manager;
import com.groupe6.beans.Utilisateur;


@Controller()
@RequestMapping("/product")
public class ProductController {
	
	//@Autowired
    Manager manager; 
    

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listProducts() {
    	manager = new Manager(); 
        logger.info("List of products");
        Collection<Utilisateur> utilisateurs = manager.selectAllUsers();
        manager.exit();
        return new ModelAndView("utilisateurList", "utilisateurs", utilisateurs);
    }
    

}