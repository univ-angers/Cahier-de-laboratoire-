package com.groupe6.controller;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/search")
public class SearchEngine {
	
	
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public  @ResponseBody List<Billet > searchBillets(@RequestParam(value = "nomTag", required = true) String nomTag) { 
    
    	Manager manager = new Manager(); 
        List<Billet> lb = new ArrayList<Billet>();

		//List<Categorie> lc = manager.selectAllCategoriesLike(nomTag);
		List<Tag> lt = manager.selectAllTagsLike(nomTag);
		for (Tag t : lt) {
			//System.out.println(t);
			lb.addAll( manager.selectBilletsByTag(t)); 	
		}

        manager.exit();
    	return  lb;

    }
}
