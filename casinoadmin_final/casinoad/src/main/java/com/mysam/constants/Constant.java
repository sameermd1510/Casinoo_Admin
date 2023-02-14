package com.mysam.constants;
/**
 * Constants class
 * @author mohammadSameer
 *
 */
public class Constant {
	
	public static String SELECT_ALL="select * from CUSTOMER_TABLE";
	public static String SEARCH_QUERY="select * from CUSTOMER_TABLE where CUSTOMER_NAME=:name"
						+ " and CUSTOMER_CONTACT=:contact and CUSTOMER_EMAIL=:email";
	public static String ADMIN_GET_API="http://localhost:8081/casinoad/api/admin/get/";
	
	public static String ADMIN_UPDATE_AMOUNT_API="http://localhost:8081/casinoad/api/admin/updateAmount/";

}
