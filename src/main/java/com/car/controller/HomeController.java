package com.car.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.car.category.repository.YearReprsitory;
import com.car.category.services.CountriesService;
import com.car.category.services.FuelService;
import com.car.category.services.MakeService;
import com.car.category.services.TypeService;
import com.car.category.services.YearService;
import com.car.entites.Car;
import com.car.entites.CarBidding;
import com.car.entites.User;
import com.car.helper.Message;
import com.car.repository.CarBiddingRepository;
import com.car.repository.CarRepository;
import com.car.repository.RoleRepository;
import com.car.repository.UserReopsitory;
import com.car.service.CarService;
import com.car.service.UserService;
@Controller
public class HomeController {
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
	UserService userService;
	@Autowired
	CarService carService;
	@Autowired
	UserReopsitory userRepository;
	@Autowired
	RoleRepository roleRepository;


	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/carImages";



	@GetMapping(value= {"/home","/"})
	public String userHome(Model model) {
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
		return "home";

	}


	@GetMapping("/home/make/{id}")
	public String getByMaker(@PathVariable("id") int id, Model model) {
		model.addAttribute("title", "Search by Maker - ABC Cars Pte Ltd");
		
		model.addAttribute("makers", makeService.findAll());
		model.addAttribute("cars", carService.getAllCarByMake_id(id));
		return "home";

	}

	@GetMapping("/home/countries/{id}")
	public String getByCountries(@PathVariable("id") int id, Model model) {

		model.addAttribute("title", "Search by Countries - ABC Cars Pte Ltd");
		model.addAttribute("countries", countriesService.findAll());
		model.addAttribute("cars", carService.getAllCarByCountries_id(id));
		return "home";

	}

	@GetMapping("/home/fuel/{id}")
	public String getByFuel(@PathVariable("id") int id, Model model) {
		model.addAttribute("title", "Search by Fuel - ABC Cars Pte Ltd");
		model.addAttribute("fuel", fuelService.findAll());
		model.addAttribute("cars", carService.getAllCarByFuel_id(id));
		return "home";

	}

	@GetMapping("/home/type/{id}")
	public String getByType(@PathVariable("id") int id, Principal principal, Model model) {

		model.addAttribute("title", "Search by Type - ABC Cars Pte Ltd");
		model.addAttribute("type", typeService.findAll());
		model.addAttribute("cars", carService.getAllCarByType_id(id));
		return "home";

	}

	@GetMapping("/home/viewCar/{id}")
	public String viewproduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("title", "ABC Cars Pte Ltd");
		model.addAttribute("car", carService.updateCar(id).get());
		return "car-details";

	}
	

	@GetMapping("/search")
	public ModelAndView search(@RequestParam String keyword, HttpSession session, Model model) {

		List<Car> result = carService.search(keyword);
		model.addAttribute("title", "Search Store - Know Your Neighborhood");
		model.addAttribute("makers", makeService.findAll());
		ModelAndView mav = new ModelAndView("home");
		if (!result.isEmpty()) {
			mav.addObject("cars", result);
		} else {
			session.setAttribute("err", "Sorry!! Not Found");
		}
		return mav;
	}
	
	
	
	
	
	
	@Autowired
	CarBiddingRepository carBiddingRepository;
	
	
	//delete contact handler
	@GetMapping("/admin/delete/{uid}")
	public String deleteContact(@PathVariable ("uid") Long uid, Model model,Principal principal,HttpSession session,@ModelAttribute Car car,@ModelAttribute  CarBidding carBidding) {
		User users= this.userRepository.findById(uid).get();
		System.out.println("user"+users.getId());
//		users.setRoles(null);
//		List<Car> cars = this.carRepository.findCarByUser(users.getId());
//		List<CarBidding> carBiddings = carBiddingRepository.findAll();
//		List<CarBidding> userCarBidding = new ArrayList<>();
//		for (Car carss : cars) {
//			for (CarBidding carBiddingss : carBiddings) {
//				if (carBiddingss.getCar().getId() == carss.getId()) {
//					carBiddingss.setResponses(null);
//					carss.setStatus(null);
//					userCarBidding.add(carBiddingss);
//				}else {
//					this.userRepository.delete(userRepository.findById(uid).get());
//
//				}
////				carBiddingss.setResponses(null);
////				
////				carss.setStatus(null);
//			}
//		}

		
		this.userRepository.delete(userRepository.findById(uid).get());
		session.setAttribute("message", new Message("Content deleted Successfully......", "alert-success"));
		
		return "redirect:/admin/all-user-list/0";
		
		
	}
	
	


	// update contact handler
	@PostMapping("/admin/update-user")
	public String view(@ModelAttribute User user,
			@RequestParam ("profileImage") MultipartFile file,Model model,
			Principal principal,
			HttpSession session) throws IOException {
		
		//old contact details
		User oldUser= this.userRepository.findById(user.getId()).get();


		
		
		if(!file.isEmpty()) {
			
			
			 // delete old photo
			File deleteFile = new ClassPathResource("static/image").getFile();
			File file2 = new File(deleteFile,oldUser.getImageUrl());
			file2.delete();
			//updte new photo
			File saveFile = new ClassPathResource("static/image").getFile();
			Path path= Paths.get(saveFile.getAbsolutePath()+File.separatorChar+file.getOriginalFilename());
			
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Image is uploaded");
			user.setImageUrl(file.getOriginalFilename());
			session.setAttribute("message", new Message("Your content is updated", "alert-success"));
			
			
			
			
		}else {
			user.setImageUrl(oldUser.getImageUrl());
	
		}
		user.setEnable(true);
		List<Car> cars = this.carRepository.findCarByUser(user.getId());
		user.setCars(cars);
	
		 this.userRepository.save(user);
		System.out.println("User:  "+user.getId());
		return "redirect:/admin/all-user-list/0";
	}
	

	

	
	
	
	




}
