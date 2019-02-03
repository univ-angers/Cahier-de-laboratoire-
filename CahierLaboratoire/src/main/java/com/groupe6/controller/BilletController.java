package com.groupe6.controller;


import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.groupe6.beans.Billet;
import com.groupe6.beans.Manager;
import com.groupe6.beans.Permission;


@Controller
@RequestMapping(value="/billet")
public class BilletController {

	//@Autowired
	Manager manager; 


	protected final Log logger = LogFactory.getLog(getClass());


	@RequestMapping(value = "/billetUpdate", method = RequestMethod.POST, headers = "Accept=application/json")

	public @ResponseBody boolean billetUpdate(@RequestBody String data) throws JsonParseException, IOException {
		JsonFactory factory = new JsonFactory();
		Long idBillet=(long) 0;
		Long idUtilisateur=(long) 0;
		String content="";
		//parsing en json du content recu

		JsonParser j= factory.createJsonParser(data);
		while(!j.isClosed()){
			JsonToken jsonToken = j.nextToken();


			if(JsonToken.FIELD_NAME.equals(jsonToken)){
				String fieldName = j.getCurrentName();
				System.out.println(fieldName);

				jsonToken = j.nextToken();

				if("idBillet".equals(fieldName)){
					idBillet= j.getValueAsLong();
				} else if ("content".equals(fieldName)){
					content = j.getText();
				} else if ("idUtilisateur".equals(fieldName))
				{
					idUtilisateur=j.getValueAsLong();
				}
			}
		}
		System.out.println("idBillet : "+idBillet+"idUtilisateur : "+idUtilisateur+" content : "+content);


		Billet newBillet= new Billet(content);
		manager = new Manager(); 
		boolean result=false;
		logger.info("Update du billet "+idBillet);
		result=manager.updateBillet(manager.selectBilletByID(idBillet),newBillet,idUtilisateur);
		manager.exit();

		return result;
	}

	@RequestMapping(value = "/billetRemove", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean billetRemove(@RequestBody String data) throws JsonParseException, IOException {
		JsonFactory factory = new JsonFactory();
		Long idBillet=(long) 0;
		Long idUtilisateur=(long) 0;

		JsonParser j= factory.createJsonParser(data);
		while(!j.isClosed()){
			JsonToken jsonToken = j.nextToken();

			if(JsonToken.FIELD_NAME.equals(jsonToken)){
				String fieldName = j.getCurrentName();
				System.out.println(fieldName);

				jsonToken = j.nextToken();

				if("idBillet".equals(fieldName)){
					idBillet= j.getValueAsLong();
				} else if ("idUtilisateur".equals(fieldName))
				{
					idUtilisateur=j.getValueAsLong();
				}
			}
		}


		manager = new Manager(); 
		boolean result=false;
		Billet b= manager.selectBilletByID(idBillet);
		logger.info("Remove du billet "+idBillet);
		result=manager.deleteBillet(b,idUtilisateur);
		manager.exit();
		return result;
	}

	@RequestMapping(value = "/billetCreation", method = RequestMethod.POST, headers = "Accept=application/json")

	public @ResponseBody long billetidRequest(@RequestBody String data) throws JsonParseException, IOException{
		
		manager = new Manager(); 
		long result=0;
		long idUtilisateur=(long) 0;
		logger.info("Recupération d'un id lors de la création d'un billet");
		
		
		JsonFactory factory = new JsonFactory();
		JsonParser j= factory.createJsonParser(data);
		while(!j.isClosed()){
			JsonToken jsonToken = j.nextToken();

			if(JsonToken.FIELD_NAME.equals(jsonToken)){
				String fieldName = j.getCurrentName();
				System.out.println(fieldName);

				jsonToken = j.nextToken();
				idUtilisateur= j.getValueAsLong();
					
			}
		}
		result=manager.createBillet(new Billet(),idUtilisateur);
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
	

	@RequestMapping(value = "/controlP", method = RequestMethod.POST, headers = "Accept=application/json")

	public @ResponseBody List<String> controlP(@RequestBody String data) throws JsonParseException, IOException{


       
		manager = new Manager();
		List<String> result=new ArrayList<>();
		List<Billet> listBillet=null;
		List<Permission> listPermissions=null;
		Long idUser = (long)0;
		logger.info("Recupération");
		listBillet=manager.selectAllBillets();


		JsonFactory factory = new JsonFactory();
		JsonParser j= factory.createJsonParser(data);

		while(!j.isClosed()){
			JsonToken jsonToken = j.nextToken();

			String fieldName = j.getCurrentName();

			if ("idUtilisateur".equals(fieldName) && j.getValueAsLong() != (long)0)
			{
				idUser=j.getValueAsLong();

			}

		}

		Manager manager2 = new Manager();
		listPermissions=manager2.selectAllPermisions();

		for (Billet billet : listBillet) {
			if (!manager2.hasPermission(idUser, billet.getIdB(), listPermissions))
				result.add("false "+billet.getIdB());
			result.add("true "+ billet.getIdB());

		}
		manager2.exit();
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