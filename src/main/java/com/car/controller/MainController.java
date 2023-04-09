package com.car.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.car.category.repository.ContactRepository;
import com.car.entites.Contact;
import com.car.entites.Role;
import com.car.entites.User;
import com.car.helper.Message;
import com.car.repository.RoleRepository;
import com.car.service.UserService;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ContactRepository contactRepository;


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	
	@RequestMapping("/register")
	public String signUp(Model model) {
		model.addAttribute("title","Register - ABC Cars Pte Ltd’");
		model.addAttribute("user", new User());
		return "register";
	}
	
	
	@PostMapping("/do_register")
	//this handler for registering
	public String register(@Valid @ModelAttribute ("user") User user,BindingResult results,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement
			,Model model, HttpSession session ) {
		model.addAttribute("title","Register Successfull - ABC Cars Pte Ltd’");

		try {
			if (!agreement) {
				System.out.println("You Have not agreed the terms and conditions");
				throw new Exception("You Have not agreed the terms and conditions");
			}
			
			if(results.hasErrors())
			{
				System.out.println("ERROR"+results+toString());
				model.addAttribute("user", user);
				return "register";
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			Role roleUser = roleRepository.findByName("USER");
			user.addRole(roleUser);
			user.setEnable(true);
			user.setImageUrl("default.png");
			                                    
			
			
			this.userService.save(user);

			System.out.println("Agreement" + agreement);
			System.out.println("USER" + user);

			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully Registered!!!", "alert-success"));
			return "register";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong!!! "+e.getMessage(), "alert-danger"));
			return "register";
		}
	}
	

	// handeler for custom login
	@GetMapping("/login")
	public String customLogin(Model model) {

		model.addAttribute("title", "Login Page - ABC Cars Pte Ltd");
		return "login";

	}
	
	

	// handeler for custom login
	@GetMapping("/aboutUs")
	public String aboutUs(Model model) {

		model.addAttribute("title", "About Us Page - ABC Cars Pte Ltd");
		return "about-us";

	}
	
	
	
	@RequestMapping("/contactUs")
	public String contactUs(Model model) {
		model.addAttribute("title","Contact Us - ABC Cars Pte Ltd’");
		model.addAttribute("contact", new Contact());
		return "contact-us";
	}

	
	@PostMapping("/contact_us")
	//this handler for registering
	public String contact(@Valid @ModelAttribute ("contact") Contact contact,BindingResult results,
			Model model, HttpSession session ) {
		model.addAttribute("title","Register Successfull - ABC Cars Pte Ltd’");
		if(results.hasErrors())
		{
			System.out.println("ERROR"+results+toString());
			model.addAttribute("contact", contact);
			return "contact-us";
		}

		try {
		
			contact.getMessages();
			this.contactRepository.save(contact);

			System.out.println("USER" + contact);

			model.addAttribute("contact", new Contact());
			session.setAttribute("message", new Message("Successfully Registered!!!", "alert-success"));
			return "contact-us";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("contact",contact);
			session.setAttribute("message", new Message("Something went wrong!!! "+e.getMessage(), "alert-danger"));
			return "contact-us";
		}
	}


	


}
