package com.blstream.ctf2.services;

import android.content.Context;

import com.blstream.ctf2.storage.dao.UserDAO;
import com.blstream.ctf2.storage.entity.User;

/**
 * 
 * @author Marcin Sareło
 *
 */
public class UserServices extends Services {
	private UserDAO uDAO;
	
	public UserServices(Context context) {
		this.uDAO = new UserDAO(context);
	}
	
	public User addNewPlayer(String name){
		
		User newUser = new User();
		newUser.setName(name);
		newUser=uDAO.createUser(newUser);	
		return newUser;
	}
	
	public User getUser() {
		
		return uDAO.getUsers().get(0);
	} 
	
	public User updateUser(User user) {

		return uDAO.update(user);
	}
	

}