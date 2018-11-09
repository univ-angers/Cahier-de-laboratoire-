package com.groupe6.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.groupe6.beans.*;
import com.groupe6.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/")
	public ModelAndView searchForm() {
		ModelAndView model = new ModelAndView("user/search");
		return model;
	}
	
	@RequestMapping(value="/search")
	public @ResponseBody List<Utilisateur> ajaxSearch(HttpServletRequest req ,  HttpServletResponse res){
		List<Utilisateur> list = userService.listUserByName(req.getParameter("name"));
		return list;
	}
}
