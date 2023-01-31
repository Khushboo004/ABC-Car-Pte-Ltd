package com.car.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.car.category.repository.ContactRepository;
import com.car.category.repository.YearReprsitory;
import com.car.category.services.CountriesService;
import com.car.category.services.FuelService;
import com.car.category.services.MakeService;
import com.car.category.services.TypeService;
import com.car.category.services.YearService;
import com.car.entites.Car;
import com.car.entites.Contact;
import com.car.entites.User;
import com.car.helper.Message;
import com.car.repository.CarRepository;
import com.car.repository.RoleRepository;
import com.car.repository.UserReopsitory;
import com.car.service.CarService;
import com.car.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserReopsitory userReopsitory;
	@Autowired
	UserService userService;
	@Autowired
	UserReopsitory userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	MakeService makeService;
	@Autowired
	YearService yearService;
	@Autowired
	YearReprsitory yearReprsitory;
	@Autowired
	FuelService fuelService;
	@Autowired
	TypeService typeService;
	@Autowired
	CountriesService countriesService;
	@Autowired
	CarRepository carRepository;
	@Autowired
	CarService carService;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("UserName " + userName);
		User user = this.userReopsitory.getUserByUsername(userName);
		System.out.println("USer " + user);

		model.addAttribute("user", user);
	}
	
	// After login the admin will appear on the admin dashboard page
	@RequestMapping("/admin-dashboard")
	public String signUp(Model model) {
		List<Car> car =  carRepository.findAll();
		List<Car> cars = new ArrayList<>();
		for (Car userCar : car) {
			if (userCar.hasRole("ACTIVATE")) {
				cars.add(userCar);
			}else {
				carRepository.findAll();
			}
		}
		model.addAttribute("title", "ABC Cars Pte Ltd");
		model.addAttribute("makers", makeService.findAll());
		model.addAttribute("year", yearService.findAll());
		model.addAttribute("fuel", fuelService.findAll());
		model.addAttribute("type", typeService.findAll());
		model.addAttribute("countries", countriesService.findAll());
		model.addAttribute("cars", cars);
		return "admin/admin-dashboard";
	}
	
	//show contacts handler
	//per page = 5[n]
	//current page = 0 [page]
	@GetMapping("/all-user-list/{page}")
	public String showContacts(@PathVariable ("page") Integer page, Model m) {
		m.addAttribute("title","Show Contacts");

		Page<User> users = this.userService.findPage(page);
		m.addAttribute("users", users);
		m.addAttribute("currentPage", page);
		m.addAttribute("totalPages", users.getTotalPages());
		return "admin/all-user-list";
	}
	

	// handler for admin about Page
	@GetMapping("/aboutUs")
	public String userAboutUs(Model model) {
		model.addAttribute("title", "About Us Page - ABC Cars Pte Ltd");
		return "admin/about-us";

	}

	
	// handler for admin contact Page
	@RequestMapping("/contactUs")
	public String contactUsUser(Model model) {
		model.addAttribute("title","Contact Us - ABC Cars Pte Ltd’");
		model.addAttribute("contact", new Contact());
		return "admin/contact-us";
	}

	// Post handler for admin about Page
	@PostMapping("/contact_us")
	//this handler for registering
	public String contacts(@Valid @ModelAttribute ("contact") Contact contact,BindingResult results,Principal principal,
			Model model, HttpSession session ) {
		model.addAttribute("title","Register Successfull - ABC Cars Pte Ltd’");
		String userName = principal.getName();
		System.out.println("UserName " + userName);
		User user = this.userRepository.getUserByUsername(userName);


		model.addAttribute("user", user);
		if(results.hasErrors())
		{
			System.out.println("ERROR"+results+toString());
			model.addAttribute("contact", contact);
			return "admin/contact-us";
		}

		try {
		
			contact.setUser(user);
			this.contactRepository.save(contact);

			System.out.println("USER" + contact);

			model.addAttribute("contact", new Contact());
			session.setAttribute("message", new Message("Successfully sent the message!!!", "alert-success"));
			return "admin/contact-us";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("contact",contact);
			session.setAttribute("message", new Message("Something went wrong!!! "+e.getMessage(), "alert-danger"));
			return "admin/contact-us";
		}
	}
	


	
	// Admin can update user
	// Admin will appear on the user update page
	@GetMapping("/update-user/{uid}")
	public String updateForn(@PathVariable ("uid") Long uid, Model model) {
		model.addAttribute("title","Upadate Contact");
		User user= this.userReopsitory.findById(uid).get();
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("user",user);
		return "admin/update-user";
		
	}
	
	

	// Admin will appear on the car maker page
	@GetMapping("/home/make/{id}")
	public String getByMaker(@PathVariable("id") int id, Model model) {
		model.addAttribute("title", "Search by Maker - ABC Cars Pte Ltd");
		
		model.addAttribute("makers", makeService.findAll());
		model.addAttribute("cars", carService.getAllCarByMake_id(id));
		return "admin/admin-dashboard";

	}

	// Admin will appear on the countries page
	@GetMapping("/home/countries/{id}")
	public String getByCountries(@PathVariable("id") int id, Model model) {

		model.addAttribute("title", "Search by Countries - ABC Cars Pte Ltd");
		model.addAttribute("countries", countriesService.findAll());
		model.addAttribute("cars", carService.getAllCarByCountries_id(id));
		return "admin/admin-dashboard";

	}
	
	
	// Admin will appear on the fuel page
	@GetMapping("/home/fuel/{id}")
	public String getByFuel(@PathVariable("id") int id, Model model) {
		model.addAttribute("title", "Search by Fuel - ABC Cars Pte Ltd");
		model.addAttribute("fuel", fuelService.findAll());
		model.addAttribute("cars", carService.getAllCarByFuel_id(id));
		return "admin/admin-dashboard";
	}

	// Admin will appear on the type page
	@GetMapping("/home/type/{id}")
	public String getByType(@PathVariable("id") int id, Principal principal, Model model) {
		String name = principal.getName();
		System.out.println("UserName " + name);
		User user = this.userRepository.getUserByUsername(name);
		model.addAttribute("user", user);
		model.addAttribute("title", "Search by Type - ABC Cars Pte Ltd");
		model.addAttribute("type", typeService.findAll());
		model.addAttribute("cars", carService.getAllCarByType_id(id));
		return "admin/admin-dashboard";

	}
	
	
	// Admin can see car details
	@GetMapping("/home/viewCar/{id}")
	public String viewCar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("title", "ABC Cars Pte Ltd");
		model.addAttribute("car", carService.updateCar(id).get());
		return "admin/car-details";
	}
	
	// Admin can search make, model, price, registration
	@GetMapping("/search")
	public ModelAndView search(@RequestParam String keyword, HttpSession session, Model model) {

		List<Car> result = carService.search(keyword);
		model.addAttribute("title", "Search Store - Know Your Neighborhood");
		model.addAttribute("makers", makeService.findAll());
		ModelAndView mav = new ModelAndView("admin/admin-dashboard");
		if (!result.isEmpty()) {
			mav.addObject("cars", result);
		} else {
			session.setAttribute("err", "Sorry!! Not Found");
		}
		return mav;
	}
	
	
	
	
	
	
	
	// showing particular user details
	@GetMapping("/user-profile/{id}")
	public String showCarDetails(@PathVariable("id") Long id, Model model, Principal principal) {
		User users = this.userService.getById(id);
		List<Car> cars = this.carRepository.findCarByUser(users);
		model.addAttribute("cars", cars);
	
		return "user/my-profile";

	}
	
	
	
	// showing particular car details
	@GetMapping("/user-home/viewCar/{id}")
	public String viewCAr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("title", "ABC Cars Pte Ltd");
		model.addAttribute("car", carService.updateCar(id).get());
		return "user/car-details";

	}
	
	
	
	
	
	
	
	
	
	


}
