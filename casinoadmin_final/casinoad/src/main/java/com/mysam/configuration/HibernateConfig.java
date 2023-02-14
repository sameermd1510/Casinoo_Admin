package com.mysam.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
/**
 * Hibernate comnfiguraton class
 * @author mohammadirshad
 *
 */
@Component
public class HibernateConfig {

	public SessionFactory getSessionFactory() {
		Configuration con = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = con.buildSessionFactory();
		return sf;
	}

	public Session getSession() {
		return getSessionFactory().openSession();
	}

}
