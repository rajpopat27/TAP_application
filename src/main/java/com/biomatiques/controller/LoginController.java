package com.biomatiques.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biomatiques.model.Login;
import com.biomatiques.services.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value= {"/loginForm"},method=RequestMethod.POST)
	public String loginForm(Login login) {
		if (loginService.checkLogin(login)) {
			Login.loggedin=true;
			return "redirect:/dashboard.html";	
		}
		return "redirect:/error1.html";
	}
	
	@RequestMapping(value= {"/registerForm"},method=RequestMethod.POST)
	public String registerForm(Login login) {
		loginService.addLogin(login);
		return "login.html";
	}
	@RequestMapping(value= {"/register.html"},method=RequestMethod.GET)
	public String registerPage(Login login) {
		return "register.html";
	}
	
	@RequestMapping(value= {"/","/login.html"},method=RequestMethod.GET)
	public String loginPage(Login login) {
		return "login.html";
	}
	
	@RequestMapping(value= {"/error1.html"},method=RequestMethod.GET)
	public String errorPage() {
		return "error1.html";
	}
	
	@RequestMapping(value= {"/logout"},method=RequestMethod.GET)
	public String logout(Login login) {
		Login.loggedin = false;
		return "redirect:/login.html";
	}
}
