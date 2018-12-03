package com.groupe6.controller;



import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.groupe6.beans.Billet;
import com.groupe6.beans.Categorie;
import com.groupe6.beans.Manager;
import com.groupe6.beans.Tag;





@Controller()
@RequestMapping("/billetTag")
public class BilletTagController {
	
	//@Autowired
    //Manager manager;
    

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public  @ResponseBody List<Tag> listTagBillets(@RequestParam(value = "idB", required = true) Long idB) { 
    	System.out.println("DANS LE CONTROLLER GET TAG, idB= "+ idB);
    	Manager manager = new Manager(); 
        List<Tag> lt;

        lt = manager.selectTagsByBilletId(idB);
        for (Tag t : lt) {

        	System.out.println(t);
        }
        manager.exit();
        return  lt;
    }
    
    

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> saveBilletTag(/*@ModelAttribute Tag t, BindingResult result,*/
    														@RequestParam(value = "idB", required = true) long idB,
    														@RequestParam(value = "idT", required = true) long idT) 
    {
    	
    	
    	Manager manager = new Manager(); 
    	

    	
    	//System.out.println(c.toString());
    	//System.out.println("nomTag: " +nomTag);
    	//System.out.println("Catégorie: " +categorie);
    
        //String nom = nomTag; 
    	//System.out.println("IDC: " +idC);
        //Tag tag = new Tag(idC,nom);
        //tag.setIdC(idC);
    	

        //System.out.println("TAG DANS LE CONTROLLER AVANT CREATE");

    	Tag tag = manager.selectTagByID(idT);
    	Billet billet = manager.selectBilletByID(idB);
    	System.out.println("Je crée un Tag Billet");
        System.out.println(tag);
        System.out.println(billet);
        manager.createBilletTag(billet, tag);
        //return "redirect:list";
        manager.exit();
        return new ResponseEntity<String>(HttpStatus.OK);
         
    }
    
    
    

}