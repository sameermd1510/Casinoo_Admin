package com.mysam.controller;

import java.io.File;
import java.util.List;

import javax.servlet.*;//http.HttpServletRequest;
import javax.servlet.*;//http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysam.entity.User;
import com.mysam.service.AdminService;
/**
 * Admin Controller class
 * @author mohammadirshad
 *
 */
@Controller
public class AdminController {

	@Autowired
	AdminService adminService;
	
	/**
	 * Admin Registration Controller
	 * @param custName
	 * @param custDob
	 * @param custContact
	 * @param custEmail
	 * @param file
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("registerCustomer")
	public ModelAndView registerCustomer(@RequestParam("name") String custName, @RequestParam("dob") String custDob,
										 @RequestParam("contact") String custContact, @RequestParam("email") String custEmail,
										 @RequestParam("file") File file, HttpServletRequest request, HttpSession session) throws Exception {

		ModelAndView mv = new ModelAndView();
		System.out.println("In register customer controller!! ");

		String fileName = file.getName();

		adminService.register(custName, custDob, custContact, custEmail, fileName);

		mv.setViewName("index");

		return mv;
	}
/**
 * UserList controller
 * @return
 */
	@RequestMapping("userlist")
	public ModelAndView showList() {
		ModelAndView mv = new ModelAndView();
		List<User> userList = adminService.get();
		mv.setViewName("userlist");
		mv.addObject("usersList", userList);

		return mv;
	}
	
	/**
	 * Search Controller
	 * @param searchName
	 * @param searchContact
	 * @param searchEmail
	 * @return
	 */

	@RequestMapping("search")
	public ModelAndView searchCustomer(@RequestParam("name") String searchName,
			@RequestParam("contact") String searchContact, @RequestParam("email") String searchEmail) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("searchName", searchName);
		mv.addObject("searchContact", searchContact);
		mv.addObject("searchEmail", searchEmail);
		List<User> userList = adminService.search(searchName, searchEmail, searchContact);
		mv.setViewName("userlist");
		mv.addObject("usersList", userList);

		System.out.println();

		return mv;
	}
	/**
	 * Recharge Balance Conroller
	 * @param amountToAdd
	 * @param key
	 * @return
	 */

	@RequestMapping("rechargeBalance")
	public String rechargeCustomerBalance(@RequestParam("amount") int amountToAdd,
			@RequestParam("secretKey") String key) {
		System.out.println("Unique Id: " + key);
		System.out.println("Amount: " + String.valueOf(amountToAdd));
		adminService.recharge(key, amountToAdd);
		return "redirect:/userlist";

	}
	
	

}
