package com.groupe6.controller;



import java.util.ArrayList;

import java.util.List;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groupe6.beans.Categorie;
import com.groupe6.beans.Manager;
import com.groupe6.beans.Tag;





@Controller()
@RequestMapping("/tag")
public class TagController {
	

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
        	//System.out.println(  lt.get(i).getNomTag().toString()    );
        	temps.add(lt.get(i).getNomTag().toString()  );
        	c = manager.selectCategoryByID(lt.get(i).getIdC());
        	temps.add( c.getNomCategorie().toString());
        	alo.add(temps);
        	
        }
        
        
        //o = manager.selectCategoryByID(id)
        manager.exit();
        return  alo;
    }
    
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> saveTag(@RequestParam(value = "categorie", required = true) String categorie,
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

    	Long idC = c.getIdC(); 
    
        String nom = nomTag; 
    	//System.out.println("IDC: " +idC);
        Tag tag = new Tag(idC,nom);
        tag.setIdC(idC);
    	

        //System.out.println("TAG DANS LE CONTROLLER AVANT CREATE");
        //System.out.println(tag);
        manager.createTag(tag);

        manager.exit();
         return new ResponseEntity<String>(HttpStatus.OK);
         
    }
    

    
    
    

}