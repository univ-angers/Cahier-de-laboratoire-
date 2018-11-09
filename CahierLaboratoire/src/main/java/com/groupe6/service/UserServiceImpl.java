package com.groupe6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupe6.dao.UserDao;
import com.groupe6.beans.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	public List<Utilisateur> listUserByName(String name) {
		return userDao.listUserByName(name);
	}

}
