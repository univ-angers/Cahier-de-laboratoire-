package com.groupe6.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;

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

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public  @ResponseBody ArrayList<ArrayList<String> > getTags() { 
    	Manager manager = new Manager(); 
        List<Tag> lt;
        lt = manager.selectAllTags();
        ArrayList<ArrayList<String> > alo = new ArrayList<ArrayList<String>>(); 
        Categorie c;
        for (int i =0; i<lt.size(); i++) {
        	ArrayList<String> temps = new ArrayList<String>();
        	temps.add(lt.get(i).getNomTag().toString());
        	c = manager.selectCategoryByID(lt.get(i).getIdC());
        	temps.add( c.getNomCategorie().toString());
        	temps.add(lt.get(i).getIdT().toString());
        	alo.add(temps);
        }
        manager.exit();
        return  alo;
    }   
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody Map saveTag(@RequestParam(value = "categorie", required = true) String categorie,@RequestParam(value = "nomTag", required = true) String nomTag) 
    {
    	Manager manager = new Manager(); 
    	Categorie c;
    	
    	//On controle si la catégorie existe déjà. Dans le cas contraire on en crée une nouvelle. 
    	
    	if (manager.selectCategory(categorie) == null) {
    		c= new Categorie(categorie);
    		
            try {
            	manager.createCategory(c);
    		}
    		catch (Exception e) { 
    			System.out.println("Erreur lors de la création de la catégorie");
    			return Collections.singletonMap("flag", false);
    		}	
    	}
    	else {
    		c =  manager.selectCategory(categorie);
    	}
    	Long idC = c.getIdC(); 
        String nom = nomTag; 
        Tag tag = new Tag(idC,nom);
        tag.setIdC(idC);
        
        //On crée le tag et on retourne vrai si tout s'est bien passé. 
        
        try {
        	
    		if (manager.createTag(tag)==0) {
    			return Collections.singletonMap("flag", false);
    		}
		}
		catch (Exception e) { 
			System.out.println("Erreur lors de la création du tag");
		}
        manager.exit();
    	return Collections.singletonMap("flag", true);     
    }

	@RequestMapping(value = "/remove", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean tagRemove(@RequestBody String data) {
		System.out.println("Remove called");
		Manager manager = new Manager(); 
		JsonFactory factory = new JsonFactory();
		Long idTag=(long) 0;
		
		try {
			JsonParser j= factory.createJsonParser(data);
			while(!j.isClosed()){
				JsonToken jsonToken = j.nextToken();

				if(JsonToken.FIELD_NAME.equals(jsonToken)){
					String fieldName = j.getCurrentName();
					System.out.println(fieldName);

					jsonToken = j.nextToken();

					if("idTag".equals(fieldName)){
						idTag= j.getValueAsLong();
					}
				}
			}
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		manager = new Manager(); 
		boolean result=false;
		Tag t= manager.selectTagByID(idTag);
		result=manager.deleteTag(t);
		manager.exit();
		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean tagUpdate(@RequestBody String data) {
		
		
		Manager manager = new Manager(); 
		
		JsonFactory factory = new JsonFactory();
		Long idTag=(long) 0;
		String content="False";
		try {
			JsonParser j= factory.createJsonParser(data);
			while(!j.isClosed()){
				JsonToken jsonToken = j.nextToken();

				if(JsonToken.FIELD_NAME.equals(jsonToken)){
					String fieldName = j.getCurrentName();
					System.out.println(fieldName);

					jsonToken = j.nextToken();

					if("idTag".equals(fieldName)){
						idTag= j.getValueAsLong();
					}
					else if("content".equals(fieldName)){
						content= j.getText();
					}
				}
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		manager = new Manager(); 
		boolean result=false;

		Tag t= manager.selectTagByID(idTag);
		
		Tag newTag= new Tag(t.getIdC(),content);
		
		result=manager.updateTag(t, newTag);
		manager.exit();
		return result;
	}

}