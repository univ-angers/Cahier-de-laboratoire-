package com.groupe6.controller;

import java.util.Collections;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groupe6.beans.Billet;
import com.groupe6.beans.Categorie;
import com.groupe6.beans.Manager;
import com.groupe6.beans.Tag;

@Controller()
@RequestMapping("/billetTag")
public class BilletTagController {
    protected final Log logger = LogFactory.getLog(getClass());
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public  @ResponseBody List<Tag> listTagBillets(@RequestParam(value = "idB", required = true) Long idB) { 
    	Manager manager = new Manager(); 
        List<Tag> lt;
        lt = manager.selectTagsByBilletId(idB);
        manager.exit();
        return  lt;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody Map saveBilletTag(/*@ModelAttribute Tag t, BindingResult result,*/
    														@RequestParam(value = "idB", required = true) long idB,
    														@RequestParam(value = "nomTag", required = true) String nomTag, 
    														@RequestParam(value = "nomCategorie", required = true) String nomCategorie) 
    {
    	Manager manager = new Manager(); 
    	try{
    		Categorie c = manager.selectCategory(nomCategorie);
    		Tag tag = manager.selectTag(c.getIdC(), nomTag);
    		Billet billet = manager.selectBilletByID(idB);
			if (manager.createBilletTag(billet, tag)==0) {
				return Collections.singletonMap("flag", false);
			}
    	}
    	catch (Exception e) { 
    	}
    	manager.exit();
    	return Collections.singletonMap("flag", true);    
    }
}