package com.car.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.car.category.repository.ResponseRepository;
import com.car.dto.CarBiddingDTO;
import com.car.dto.TestDriveDTO;
import com.car.entites.Car;
import com.car.entites.CarBidding;
import com.car.entites.Response;
import com.car.entites.TestDrive;
import com.car.entites.User;
import com.car.helper.Message;
import com.car.repository.CarBiddingRepository;
import com.car.repository.CarRepository;
import com.car.repository.TestDriveRepository;
import com.car.repository.UserReopsitory;
import com.car.service.CarBiddingService;
import com.car.service.CarService;
import com.car.service.TestDeiveService;

@Controller
@RequestMapping("/user")
public class CarController {
	@Autowired
	CarService carService;
	@Autowired
	UserReopsitory userRepository;
	@Autowired
	TestDeiveService testDeiveService;
	@Autowired
	TestDriveRepository testDriveRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	CarBiddingRepository carBiddingRepository;
	@Autowired
	ResponseRepository responseRepository;
	@Autowired
	CarBiddingService carBiddingService;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		
		String userName = principal.getName();
		System.out.println("UserName " + userName);
		User user = this.userRepository.getUserByUsername(userName);

		model.addAttribute("user", user);
	}


	
	@RequestMapping("/test_drive_request/{id}")
	public String testDriveRequest(@PathVariable Long id,Model model) {
		Car car = carService.updateCar(id).get();
		model.addAttribute("title","Test Drive Request - ABC Cars Pte Ltd’");
		model.addAttribute("cars", car);
		model.addAttribute("testDriveDTO", new TestDriveDTO());
		return "user/test-drive";
	}
	
	@PostMapping("/test_drive_request")
	//this handler for registering
	public String register(@Valid @ModelAttribute ("testDriveDTO") TestDriveDTO testDriveDTO,BindingResult results
			,Car car,Principal principle	,Model model, HttpSession session ) {
		model.addAttribute("title","Register Successfull - ABC Cars Pte Ltd’");
		if(results.hasErrors())
		{
			System.out.println("ERROR"+results+toString());
			model.addAttribute("testDriveDTO", new TestDrive());
			return "user/test-drive";
		}

		String name = principle.getName();
		System.out.println("UserName " + name);
		User user = this.userRepository.getUserByUsername(name);
		model.addAttribute("user", user);
		try {
			model.addAttribute("cars", carService.getAllCar());
			TestDrive testDrive = new TestDrive();
			testDrive.setId(testDriveDTO.getId());
			testDrive.setFirstName(testDriveDTO.getFirstName());
			testDrive.setLastName(testDriveDTO.getLastName());
			testDrive.setEmail(testDriveDTO.getEmail());
			testDrive.setPhone(testDriveDTO.getPhone());
			testDrive.setPostcode(testDriveDTO.getPostcode());
			testDrive.setPreferredDate(testDriveDTO.getPreferredDate());
			testDrive.setComments(testDriveDTO.getComments());
			testDrive.setUser(user);
			testDrive.setCar(carService.updateCar(testDriveDTO.getCarId()).get());
			
			
			testDeiveService.save(testDrive);			
			session.setAttribute("message", new Message("Test Drive Request sent Successfully", "alert-success"));

			return "user/test-drive";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong!!! "+e.getMessage(), "alert-danger"));
			return "user/test-drive";
		}
	}
	
	
	@GetMapping("/show-requests")
	public String showContactsuser( Model m, Principal principal) {
		m.addAttribute("title", "Show Contacts");
		String name = principal.getName();
		System.out.println("UserName " + name);
		User user = this.userRepository.getUserByUsername(name);
		m.addAttribute("user", user);
		List<Car> cars = this.carRepository.findCarByUser(user.getId());


		List<TestDrive> testDriven = testDriveRepository.findAll();
		List<TestDrive> userTestDrive = new ArrayList<>();
		for (Car car : cars) {
			for (TestDrive testDrivens : testDriven) {
				if (testDrivens.getCar().getId() == car.getId()) {
					userTestDrive.add(testDrivens);
				}
			}
		}
		m.addAttribute("testDriven", userTestDrive);

		return "user/car-test-driven-request";
	}
	
	@GetMapping("/show-requests/delete/{id}")
	public String deleteCar(@PathVariable Long id) {
		testDriveRepository.deleteById(id);
		return "redirect:/user/show-requests";
	}
	
	
	@RequestMapping("/car_bidding/{id}")
	public String carBidding(@PathVariable Long id,Model model,Principal principal) {
		Car car = carService.updateCar(id).get();

		String name = principal.getName();
		System.out.println("UserName " + name);
		User user = this.userRepository.getUserByUsername(name);
		model.addAttribute("user", user);


		
		model.addAttribute("title","Test Drive Request - ABC Cars Pte Ltd’");
		model.addAttribute("cars", car);
		model.addAttribute("carBiddingDTO", new CarBiddingDTO());
		List<CarBidding> carBiddings = carBiddingRepository.findAll();
		List<CarBidding> userCarBidding = new ArrayList<>();
			for (CarBidding carBiddingss : carBiddings) {
				if (carBiddingss.getUser().getId() == user.getId()) {
					userCarBidding.add(carBiddingss);
				}else {
					return "user/car-bidding";
				}
			}
		
		model.addAttribute("carBiddings", userCarBidding);
		return "user/car-bidding";
	}
	
	@PostMapping("/car_bidding")
	//this handler for registering
	public String car_bidding(@Valid @ModelAttribute ("carBiddingDTO") CarBiddingDTO carBiddingDTO,BindingResult results
			,Car car,Principal principle	,Model model, HttpSession session ) {
		model.addAttribute("title","Register Successfull - ABC Cars Pte Ltd’");
		if(results.hasErrors())
		{
			System.out.println("ERROR"+results+toString());
			model.addAttribute("carBiddingDTO", new CarBidding());
			return "user/car-bidding";
		}

		String name = principle.getName();
		System.out.println("UserName " + name);
		User user = this.userRepository.getUserByUsername(name);
		model.addAttribute("user", user);
		try {
			model.addAttribute("cars", carService.getAllCar());
			CarBidding carBidding = new CarBidding();
			carBidding.setId(carBiddingDTO.getId());
			carBidding.setBidPrice(carBiddingDTO.getBidPrice());

			carBidding.setUser(user);
			carBidding.setCar(carService.updateCar(carBiddingDTO.getCarId()).get());
			Response response = responseRepository.findByResponse("PENDING");
			carBidding.addResponse(response);
			
			carBiddingService.save(carBidding);			
			session.setAttribute("message", new Message("Test Drive Request sent Successfully", "alert-success"));

			return "redirect:/user/car_bidding/"+carBiddingDTO.getCarId();
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong!!! "+e.getMessage(), "alert-danger"));
			return "user/car-bidding";
		}
	}
	

	@GetMapping("/show-bidding")
	public String showbiddingr( Model m, Principal principal) {
		m.addAttribute("title", "Show Contacts");
		String name = principal.getName();
		System.out.println("UserName " + name);
		User user = this.userRepository.getUserByUsername(name);
		m.addAttribute("user", user);
		List<Car> cars = this.carRepository.findCarByUser(user.getId());


		List<CarBidding> carBiddings = carBiddingRepository.findAll();
		List<CarBidding> userCarBidding = new ArrayList<>();
		for (Car car : cars) {
			for (CarBidding carBiddingss : carBiddings) {
				if (carBiddingss.getCar().getId() == car.getId()) {
					userCarBidding.add(carBiddingss);
				}
			}
		}
		m.addAttribute("carBiddings", userCarBidding);

		return "user/all-bidding";
	}
	
	
	
	
	
	
	
	
	
	
	// open update form handler
	@GetMapping("/car/bidding/{cid}")
	public String updateForn(@PathVariable ("cid") Long cid, Model model) {
		model.addAttribute("title","Upadate Contact");
		CarBidding carBidding= this.carBiddingRepository.findById(cid).get();
		model.addAttribute("responses",responseRepository.findAll());
		model.addAttribute("carBidding",carBidding);
		return "user/bid-response";
		
	}

	// update contact handler
	@PostMapping("/car/bidding")
	public String view(@ModelAttribute("carBidding") CarBidding carBidding,Model model,
			Principal principal,
			HttpSession session) throws IOException {
		
		model.addAttribute("responses",responseRepository.findAll());
		this.carBiddingRepository.save(carBidding);
		System.out.println("User:  "+carBidding.getId());
		return "redirect:/user/show-bidding";
	}
	
	
	
	@GetMapping("/car_bidding/delete/{id}")
	public String car_bidding(@PathVariable Long id, @ModelAttribute("carBidding") CarBidding carBidding) {
		CarBidding carBidding1 =this.carBiddingRepository.findById(id).get();
		carBidding1.setResponses(null);
		carBiddingRepository.deleteById(id);
		return "redirect:/user/car_bidding/"+carBidding1.getCar().getId();
	}
	
	
	
	

	
}
