package com.groupe6.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groupe6.beans.Billet;
import com.groupe6.beans.Manager;
import com.groupe6.beans.Tag;

@Controller()
@RequestMapping("/search")
public class SearchEngine {
	
	//On récupère une liste billets depuis un nom de tag dans la BDD. 
	
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public  @ResponseBody List<Billet > searchBillets(@RequestParam(value = "nomTag", required = true) String nomTag) { 
    	Manager manager = new Manager(); 
        List<Billet> lb = new ArrayList<Billet>();
		List<Tag> lt = manager.selectAllTagsLike(nomTag);
		

		for (Tag t : lt) {
			try {
				lb.addAll( manager.selectBilletsByTag(t));
			}
			catch (NullPointerException e) {	
			}
		}
        manager.exit();
    	return  lb;
    }
    
    // selectAllBilletsTags()
 
    @RequestMapping(value = "/getBillets", method = RequestMethod.GET)
    public  @ResponseBody List<Billet > searchBilletsByTags(@RequestParam(value = "nomTag", required = true) String nomTag) { 

    	Manager manager = new Manager(); 
        List<Billet> lb = new ArrayList<Billet>();
    	String names[] = nomTag.split(",");
    	
    	
    	for(String name : names)
    	{
    		
    		List<Tag> lt = manager.selectAllTagsLike(name);
    		
    		for (Tag t : lt) {
    			try {
    	
    				lb.addAll( manager.selectBilletsByTag(t));
    			
    			}
    			catch (NullPointerException e) {	
    			}
    		}
    		
    	}
      
        manager.exit();
    	return  lb;
    }
}
