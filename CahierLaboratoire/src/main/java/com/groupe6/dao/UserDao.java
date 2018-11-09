package com.groupe6.dao;

import java.util.List;

import com.groupe6.beans.*;

public interface UserDao {
	
	public List<Utilisateur> listUserByName(String name);

}
