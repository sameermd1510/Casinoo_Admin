package com.mysam.dao;

import java.util.List;

import com.mysam.entity.User;


public interface UserDao {
	
	//register user
	public void addUser(User user);
	
	//get user
	public List<User> getAllUser();
	
	//recharge service
	public void addAmount(String custKey, int amountToAdd);
	
	//Search service
	public List<User> getUserByNEC(String name,String email,String contact);
	
	public User getById(String id);
	
	public User getBlocked(String id,String bamt);
	
	public void updateAmount(String key, String updateAmount);
	
	

}
