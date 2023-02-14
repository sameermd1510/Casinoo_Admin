package com.mysam.service;

import java.util.List;

import com.mysam.entity.User;
/**
 * AdminService Interface
 * @author mohammadirshad
 *
 */

public interface AdminService {
	
	public void register(String custName, String custDob, String custContact, String custEmail, 
			String custFile);
	
	public void recharge(String custKey, int amountToAdd);
	
	public List<User> search(String name,String email,String contact);
	
	public void updateUserAmt(String id,String amt);
	
	public List<User> get();
	
	public User getUserToRoulette(String id);
	
	public User updateUserBlockedAmt(String id,String bamt);
	
	

}
