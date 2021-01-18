package com.kgisl.twofa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgisl.twofa.captcha.CaptchaUtils;
import com.kgisl.twofa.model.User;
import com.kgisl.twofa.service.IUserService;

import cn.apiclub.captcha.Captcha;

/**
 * The Class UserController.
 */
@Controller
@RequestMapping("/user")
public class UserController {

	/** The service. */
	@Autowired
	private IUserService service;
	
	/**
	 * Captcha generation.
	 *
	 * @param user the new up captcha
	 */
	private void setupCaptcha(User user) {
		Captcha captcha= CaptchaUtils.createCaptcha(200, 50);		
		user.setHidden(captcha.getAnswer());
		user.setCaptcha("");
		user.setImage(CaptchaUtils.encodeBase64(captcha));
	}
	
	/**
	 * Show reg.
	 *
	 * @param model the model
	 * @return the string
	 */
	//1. show Register page
	@GetMapping("/register")
	public String showReg(Model model) {
	User user=new User();
		setupCaptcha(user);
		model.addAttribute("user",user );
		return "UserRegister";
	}
	
	/**
	 * Method to Save.
	 *
	 * @param user the user
	 * @param model the model
	 * @return the string
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	@PostMapping("/save")
	public String save(@ModelAttribute("user") User user, Model model) throws JsonMappingException, JsonProcessingException {
		String page=null;
//		ObjectMapper mapper = new ObjectMapper();
		//Convert JSON to POJO
//		User emp = mapper.readValue(encryptedData, User.class);
		if(user.getCaptcha().equals(user.getHidden())) {
			service.createUser(user);
			model.addAttribute("message", "User has been created!!");
			
			 model.addAttribute("user", new User()); 
			 setupCaptcha(user);
			 page="redirect:all";
			return page;
		}else {
			model.addAttribute("message", "User has not been created!!");
			setupCaptcha(user);
			page="UserRegister";
			return page;
		 
		}
	
	}
	
	/**
	 * Method to Fetch all users.
	 *
	 * @param model the model
	 * @return the string
	 */
	//3.Get all users
	@GetMapping("/all")
	public String fetchAllUsers(Model model) {
		model.addAttribute("list", service.getAllUsers());
		return "UserData";
	}
	
	/**
	 * Method to Remove user details
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/delete/{id}")
	public String remove(@PathVariable Integer id,Model model) {
		Optional<User>opt = service.getOneUser(id);
		if(opt.isPresent()){
			service.deleteUser(id);
			model.addAttribute("message","User with '"+id+"' has deleted");
		}else {
			model.addAttribute("message","User with '"+id+"' has not deleted");
		}
		// model.addAttribute("list", service.getAllUsers());
		User user=new User();
		setupCaptcha(user);
		model.addAttribute("user",user );
		return "UserRegister";
	}
	
	/**
	 * Method to Edit user details
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/edit/{id}")
	public String edit( @PathVariable Integer id,Model model) {
		Optional<User>opt = service.getOneUser(id);
		if(opt.isPresent()){
			User u= opt.get();
			model.addAttribute("user",u);
			setupCaptcha(u);
			return "UserEdit";
		}else {
			model.addAttribute("message","User with '"+id+"' has not updated");
			model.addAttribute("list", service.getAllUsers());
			return "UserData";
		}
		
	}
	
	/**
	 * Method to Update user details.
	 *
	 * @param user the user
	 * @param model the model
	 * @return the string
	 */
	//5.update user
	@PostMapping("/update")
	public String update(@ModelAttribute User user, Model model) {
		String page=null;
		if(user.getCaptcha().equals(user.getHidden())) {
			service.updateUser(user);
			model.addAttribute("message","User with '"+user.getId()+"' has  updated");
			page="redirect:all";
			
			return page;
		}else {
			model.addAttribute("message", "User has not been updated!!");
			setupCaptcha(user);
			page="UserEdit";
			return page;
		 
		}
	}
}
