package com.hpf.DAO;

import org.springframework.stereotype.Component;

import com.hpf.model.CreateUserModel;

@Component
public interface CreateUserDAO {

	public void createUser(CreateUserModel createUserModel);

}
