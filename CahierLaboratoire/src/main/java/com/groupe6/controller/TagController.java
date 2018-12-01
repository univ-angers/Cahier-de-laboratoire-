package com.groupe6.controller;



import java.util.ArrayList;
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

import com.groupe6.beans.Categorie;
import com.groupe6.beans.Manager;
import com.groupe6.beans.Tag;





@Controller()
@RequestMapping("/tag")
public class TagController {
	
	//@Autowired
    //Manager manager;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public  @ResponseBody ArrayList<ArrayList<String> > getTags() { 
    
    	Manager manager = new Manager(); 
        List<Tag> lt;
        lt = manager.selectAllTags();
        ArrayList<ArrayList<String> > alo = new ArrayList<ArrayList<String>>(); 
        Categorie c; 
        
        for (int i =0; i<lt.size(); i++) {
        	ArrayList<String> temps = new ArrayList<String>();
        	System.out.println(  lt.get(i).getNomTag().toString()    );
        	temps.add(lt.get(i).getNomTag().toString()  );
        	c = manager.selectCategoryByID(lt.get(i).getIdC());
        	temps.add( c.getNomCategorie().toString());
        	alo.add(temps);
        	
        }
        
        
        //o = manager.selectCategoryByID(id)
        manager.exit();
        return  alo;
    }
    
    /*
    //@ModelAttribute
    public Tag newTag(@RequestParam(value = "categorie", required = true) String categorie,
    				 @RequestParam(value = "nomTag", required = true) String nomTag) {   
    	
    	
    	Manager manager = new Manager(); 
    	
    	Categorie c =  manager.selectCategory(categorie);

    	System.out.println(c.toString());
    	System.out.println("nomTag: " +nomTag);
    	System.out.println("Catégorie: " +categorie);
        //long idC = categorie; 
    	Long idC = c.getIdC(); 
    
        String nom = nomTag; 
    	System.out.println("nom: " +nom);
        Tag tag = new Tag(idC,nom);
        logger.info("Nouveau TAG ");
        manager.exit();
        return tag; 
    }
    */
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> saveProduct(/*@ModelAttribute Tag t, BindingResult result,*/
    														@RequestParam(value = "categorie", required = true) String categorie,
    														@RequestParam(value = "nomTag", required = true) String nomTag) 
    {
    	
    	
    	Manager manager = new Manager(); 
    	
    	Categorie c;
    	
    	if (manager.selectCategory(categorie) == null) {
    		c= new Categorie(categorie);
    		manager.createCategory(c);
    	}
    	else {
    		c =  manager.selectCategory(categorie);
    	}

    	
    	System.out.println(c.toString());
    	System.out.println("nomTag: " +nomTag);
    	System.out.println("Catégorie: " +categorie);
    	Long idC = c.getIdC(); 
    
        String nom = nomTag; 
    	System.out.println("IDC: " +idC);
        Tag tag = new Tag(idC,nom);
        tag.setIdC(idC);
    	

        System.out.println("TAG DANS LE CONTROLLER AVANT CREATE");
        System.out.println(tag);
        manager.createTag(tag);
        //return "redirect:list";
        manager.exit();
         return new ResponseEntity<String>(HttpStatus.OK);
         
    }
    
    
    

}