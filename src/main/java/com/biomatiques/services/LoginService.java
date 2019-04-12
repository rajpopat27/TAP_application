package com.biomatiques.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomatiques.model.Login;
import com.biomatiques.repository.LoginRepository;

@Service
public class LoginService {

	 @Autowired
	    private LoginRepository loginRepository;
	     public boolean checkLogin(Login login) {
	    	 String userId = login.getUserId();
	    	 String password = login.getPassword();
	    	 List<Login> list = new ArrayList<>();
	    	 loginRepository.findAll().forEach(list::add);
	    	 for(Login i : list) {
	    		 String userId1 = i.getUserId();
		    	 String password1 = i.getPassword();
	    		 if(userId1.equals(userId) && password1.equals(password)) {
	    			 return true;
	    		 }
	    	 }
	    	 return false;
	     }
	     
	     public void addLogin(Login login) {
	    	 loginRepository.save(login);
	     }
}
