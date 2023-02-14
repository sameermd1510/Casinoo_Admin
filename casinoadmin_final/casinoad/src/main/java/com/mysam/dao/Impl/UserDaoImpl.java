package com.mysam.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysam.configuration.HibernateConfig;
import com.mysam.constants.Constant;
import com.mysam.dao.UserDao;
import com.mysam.entity.User;


@Component
public class UserDaoImpl implements UserDao {

	// register impl
	@Autowired
	HibernateConfig hc;
	@Autowired
	User user;

	public void addUser(User user) {
		Session session = hc.getSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();

	}

	public List<User> getAllUser() {

		Session session = hc.getSession();
		@SuppressWarnings("unchecked")
		List<User> result = session.createSQLQuery(Constant.SELECT_ALL).addEntity(User.class).list();
		System.out.println("inside user dao impl" + result);
		return result;

	}

	public void addAmount(String key, int amountToAdd) {
		Session session = hc.getSession();
		Transaction tx = session.beginTransaction();
		user = session.get(User.class, (key));
		user.setCustAccountBalance(user.getCustAccountBalance() + amountToAdd);
		session.update(user);
		tx.commit();

	}

	public List<User> getUserByNEC(String name, String email, String contact) {
		Session session = hc.getSession();
		@SuppressWarnings("unchecked")
		List<User> result = session.createSQLQuery(Constant.SEARCH_QUERY).addEntity(User.class)
				.setParameter("name", name).setParameter("contact", contact).setParameter("email", email).list();
		return result;

	}

	public User getById(String id) {
		Session session = hc.getSession();
		user = session.get(User.class, (id));
		System.out.println("inside getById");
		System.out.println(user);
		return user;

	}

	public void updateAmount(String key, String updateAmount) {
		Session session = hc.getSession();
		Transaction tx = session.beginTransaction();
		user = session.get(User.class, (key));
		user.setCustAccountBalance(Integer.parseInt(updateAmount));
		user.setCustBlockedAmt(0);
		session.update(user);
		tx.commit();

	}
	
	public User getBlocked(String id,String bamt){
		Session session=hc.getSession();
		Transaction tx = session.beginTransaction();
		user = session.get(User.class, (id));
		user.setCustBlockedAmt(Integer.parseInt(bamt));
		user.setCustAccountBalance(user.getCustAccountBalance()-Integer.parseInt(bamt));
		session.update(user);
		tx.commit();
		return session.get(User.class, (id));
	}

}
