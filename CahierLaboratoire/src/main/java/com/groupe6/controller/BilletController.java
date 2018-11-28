package com.groupe6.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groupe6.beans.Billet;
import com.groupe6.beans.Manager;

@Controller
@RequestMapping(value="/billet")
public class BilletController {

	//@Autowired
    Manager manager; 
    

    protected final Log logger = LogFactory.getLog(getClass());



	@RequestMapping(value = "/billetUpdate", method = RequestMethod.GET, headers = "Accept=application/json")
	
    public @ResponseBody boolean billetUpdate(@RequestParam("idBillet") long id, @RequestParam("content") String content) {

		Billet newBillet= new Billet(content);
		System.out.println("test");
    	manager = new Manager(); 
    	boolean result=false;
        logger.info("Update du billet "+id);
        result=manager.updateBillet(manager.selectBilletByID(id),newBillet);
        manager.exit();
        
        return result;
    }
    
	@RequestMapping(value = "/billetCreation", method = RequestMethod.POST, headers = "Accept=application/json")
	
    public @ResponseBody long billetidRequest() {
		System.out.println("test");
    	manager = new Manager(); 
    	long result=0;
        logger.info("Recupération d'un id lors de la création d'un billet");
        result=manager.createBillet(new Billet());
        manager.exit();
        
        return result;
    }
    
}

//@RequestMapping(value = "/add", method = RequestMethod.POST)
//public  ResponseEntity<String> saveProduct(@ModelAttribute Tag t, BindingResult result) {
//	manager = new Manager(); 
//    if (result.hasErrors()) {
//       // return "utilisateurList";
//    	manager.exit();
//    	return new ResponseEntity<String>(HttpStatus.FORBIDDEN);  
//    }
//    manager.createTag(t);
//    //return "redirect:list";
//    manager.exit();
//     return new ResponseEntity<String>(HttpStatus.OK);
//     
//}