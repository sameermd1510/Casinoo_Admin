package com.mysam.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysam.dao.UserDao;
import com.mysam.entity.User;
/**
 * Adminservice Implementation
 * @author mohammadSameer
 *
 */
@Component
public class AdminServiceImpl implements AdminService {

	@Autowired
	User user;
	@Autowired
	UserDao userDao;

	public void register(String custName, String custDob, String custContact, String custEmail, String custFile) {
		user.setCustName(custName);
		user.setCustDob(custDob);
		user.setCustContact(custContact);
		user.setCustEmail(custEmail);
		user.setCustIdProof(custFile.toString());
		user.setCustBlockedAmt(0);
		user.setUniqueId(getSaltString());
		userDao.addUser(user);

	}

	public void recharge(String key, int amountToAdd) {

		userDao.addAmount(key, amountToAdd);

	}

	public List<User> search(String name, String email, String contact) {

		return userDao.getUserByNEC(name, email, contact);
	}

	public List<User> get() {

		return userDao.getAllUser();

	}
	
	public User getUserToRoulette(String id){
		return userDao.getById(id);
	}
	
	public void updateUserAmt(String id,String amt){
		userDao.updateAmount(id, amt);
	}
	
	public User updateUserBlockedAmt(String id,String bamt){
		return userDao.getBlocked(id,bamt);
	}
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
