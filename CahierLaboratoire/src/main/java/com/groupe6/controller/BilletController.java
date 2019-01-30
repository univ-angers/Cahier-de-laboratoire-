package com.groupe6.controller;


import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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


@Controller
@RequestMapping(value="/billet")
public class BilletController {

	//@Autowired
	Manager manager; 


	protected final Log logger = LogFactory.getLog(getClass());


	@RequestMapping(value = "/billetUpdate", method = RequestMethod.POST, headers = "Accept=application/json")

	public @ResponseBody boolean billetUpdate(@RequestBody String data) {
		JsonFactory factory = new JsonFactory();
		Long idBillet=(long) 0;
		String content="";
		//parsing en json du content recu
		try {
			JsonParser j= factory.createJsonParser(data);
			


			while(!j.isClosed()){
				JsonToken jsonToken = j.nextToken();


				if(JsonToken.FIELD_NAME.equals(jsonToken)){
					String fieldName = j.getCurrentName();
					

					jsonToken = j.nextToken();

					if("idBillet".equals(fieldName)){
						idBillet= j.getValueAsLong();
					} else if ("content".equals(fieldName)){
						content = j.getText();
					}
				}
			}
			System.out.println("idBillet : "+idBillet+" content : "+content);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Billet newBillet= new Billet(content);
		
		manager = new Manager(); 
		boolean result=false;
		logger.info("Update du billet "+idBillet);
		result=manager.updateBillet(manager.selectBilletByID(idBillet),newBillet);
		System.out.println(result);
		manager.exit();

		return result;
	}

	@RequestMapping(value = "/billetRemove", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean billetRemove(@RequestBody String data) {
		JsonFactory factory = new JsonFactory();
		Long idBillet=(long) 0;
		try {
			JsonParser j= factory.createJsonParser(data);
			while(!j.isClosed()){
				JsonToken jsonToken = j.nextToken();

				if(JsonToken.FIELD_NAME.equals(jsonToken)){
					String fieldName = j.getCurrentName();
					System.out.println(fieldName);

					jsonToken = j.nextToken();

					if("idBillet".equals(fieldName)){
						idBillet= j.getValueAsLong();
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
		Billet b= manager.selectBilletByID(idBillet);
		logger.info("Remove du billet "+idBillet);
		result=manager.deleteBillet(b);
		manager.exit();
		return result;
	}

	@RequestMapping(value = "/billetCreation", method = RequestMethod.POST, headers = "Accept=application/json")

	public @ResponseBody long billetidRequest() {
		manager = new Manager(); 
		long result=0;
		logger.info("Recupération d'un id lors de la création d'un billet");
		result=manager.createBillet(new Billet());
		manager.exit();

		return result;
	}

	@RequestMapping(value = "/lastBillets", method = RequestMethod.POST, headers = "Accept=application/json")

	public @ResponseBody List<Billet> lastBillets() {
		manager = new Manager();
		List<Billet> result=null;
		logger.info("Recupération");
		result=manager.selectAllBillets();
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