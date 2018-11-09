package com.groupe6.service;

import java.util.List;

import com.groupe6.beans.*;

public interface UserService {

	public List<Utilisateur> listUserByName(String name);
}
