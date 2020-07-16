package edu.hunau.springboot.service;


import edu.hunau.springboot.entity.User;

public interface UserService {
	
	User userLogin(String userName, String password);

}
