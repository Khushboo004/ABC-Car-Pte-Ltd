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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.car.category.repository.ContactRepository;
import com.car.category.repository.StatusRepository;
import com.car.category.repository.YearReprsitory;
import com.car.category.services.CountriesService;
import com.car.category.services.FuelService;
import com.car.category.services.MakeService;
import com.car.category.services.StatusService;
import com.car.category.services.TypeService;
import com.car.category.services.YearService;
import com.car.dto.CarDTO;
import com.car.entites.Car;
import com.car.entites.CarBidding;
import com.car.entites.Contact;
import com.car.entites.Role;
import com.car.entites.Status;
import com.car.entites.User;
import com.car.helper.Message;
import com.car.repository.CarBiddingRepository;
import com.car.repository.CarRepository;
import com.car.repository.RoleRepository;
import com.car.repository.UserReopsitory;
import com.car.service.CarService;
import com.car.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	MakeService makeService;
	@Autowired
	CarBiddingRepository carBiddingRepository;
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
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private StatusService statusService;



	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/carImages";



	@GetMapping("/user-home")
	public String userDashborad(Model model) {
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
	
		return "user/user-home";

	}

	// handeler for custom login
	@GetMapping("/aboutUs")
	public String userAboutUs(Model model) {
		model.addAttribute("title", "About Us Page - ABC Cars Pte Ltd");
		return "user/about-us";

	}

	@GetMapping("/user-home/make/{id}")
	public String getByMaker(@PathVariable("id") int id, Model model) {
		model.addAttribute("title", "Search by Maker - ABC Cars Pte Ltd");
		model.addAttribute("makers", makeService.findAll());
		model.addAttribute("cars", carService.getAllCarByMake_id(id));
		return "user/user-home";

	}

	@GetMapping("/user-home/countries/{id}")
	public String getByCountries(@PathVariable("id") int id, Model model) {

		model.addAttribute("title", "Search by Countries - ABC Cars Pte Ltd");
		model.addAttribute("countries", countriesService.findAll());
		model.addAttribute("cars", carService.getAllCarByCountries_id(id));
		return "user/user-home";

	}

	@GetMapping("/user-home/fuel/{id}")
	public String getByFuel(@PathVariable("id") int id, Model model) {
		model.addAttribute("title", "Search by Fuel - ABC Cars Pte Ltd");
		model.addAttribute("fuel", fuelService.findAll());
		model.addAttribute("cars", carService.getAllCarByFuel_id(id));
		return "user/user-home";

	}

	@GetMapping("/user-home/type/{id}")
	public String getByType(@PathVariable("id") int id, Principal principal, Model model) {
		String name = principal.getName();
		System.out.println("UserName " + name);
		User user = this.userRepository.getUserByUsername(name);
		model.addAttribute("user", user);
		model.addAttribute("title", "Search by Type - ABC Cars Pte Ltd");
		model.addAttribute("type", typeService.findAll());
		model.addAttribute("cars", carService.getAllCarByType_id(id));
		return "user/user-home";

	}

	@GetMapping("/user-home/viewCar/{id}")
	public String viewproduct(@PathVariable("id") Long id, Model model,
			Principal principal) {
		model.addAttribute("title", "ABC Cars Pte Ltd");
		model.addAttribute("car", carService.updateCar(id).get());
		
		
		
		return "user/car-details";

	}

	@GetMapping("/cars/add-car")
	public String addCar(Model model) {
		model.addAttribute("carDTO", new CarDTO());
		model.addAttribute("makers", makeService.findAll());
		model.addAttribute("year", yearService.findAll());
		model.addAttribute("fuels", fuelService.findAll());
		model.addAttribute("types", typeService.findAll());
		model.addAttribute("countries", countriesService.findAll());
		return "user/add-car";

	}
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("UserName " + userName);
		User user = this.userRepository.getUserByUsername(userName);


		model.addAttribute("user", user);
	}

	@PostMapping("/cars/add-car")
	public String postAddCAr(@ModelAttribute("carDTO") CarDTO carDTO, Model model, Principal principle,
			HttpSession session, @RequestParam("carImage") MultipartFile file, @RequestParam("imgShow") String imgShow,
			@RequestParam("carImage1") MultipartFile file1, @RequestParam("img1") String img1,
			@RequestParam("carImage2") MultipartFile file2, @RequestParam("img2") String img2,
			@RequestParam("carImage3") MultipartFile file3, @RequestParam("img3") String img3) throws IOException {

		try {

			String name = principle.getName();
			System.out.println("UserName " + name);
			User user = this.userRepository.getUserByUsername(name);
			model.addAttribute("user", user);

			Car car = new Car();
			car.setId(carDTO.getId());
			car.setMake(makeService.getMakeById(carDTO.getMakeId()).get());
			car.setModel(carDTO.getModel());
			car.setYear(yearService.getYearById(carDTO.getYearId()).get());
			car.setCountries(countriesService.getCountriesById(carDTO.getCountriesId()).get());
			car.setType(typeService.getTypeById(carDTO.getTypeId()).get());
			car.setFuel(fuelService.getFuelById(carDTO.getFuelId()).get());
			car.setPrice(carDTO.getPrice());
			car.setMileage(carDTO.getMileage());
			car.setCc(carDTO.getCc());
			car.setColor(carDTO.getColor());
			car.setRegYear(carDTO.getRegYear());
			car.setDescription(carDTO.getDescription());

			String imageUUID;
			String imageUUID1;
			String imageUUID2;
			String imageUUID3;

			if (!file.isEmpty()) {
				imageUUID = file.getOriginalFilename();
				Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
				Files.write(fileNameAndPath, file.getBytes());
				imageUUID1 = file1.getOriginalFilename();
				Path fileNameAndPath1 = Paths.get(uploadDir, imageUUID1);
				Files.write(fileNameAndPath1, file1.getBytes());
				imageUUID2 = file2.getOriginalFilename();
				Path fileNameAndPath2 = Paths.get(uploadDir, imageUUID2);
				Files.write(fileNameAndPath2, file2.getBytes());
				imageUUID3 = file3.getOriginalFilename();
				Path fileNameAndPath3 = Paths.get(uploadDir, imageUUID3);
				Files.write(fileNameAndPath3, file3.getBytes());

			} else {
				imageUUID = imgShow;
				imageUUID1 = img1;
				imageUUID2 = img2;
				imageUUID3 = img3;
			}

			Status carStatus = statusRepository.findByStatus("ACTIVATE");
			car.addStatus(carStatus);
			car.setImgShow(imageUUID);
			car.setImg1(imageUUID1);
			car.setImg2(imageUUID2);
			car.setImg3(imageUUID3);
			car.setUser(user);
			this.carService.save(car);


			session.setAttribute("message", new Message("Successfully car Details added ", "alert-success"));

			return "user/add-car";

		} catch (Exception e) {
			e.printStackTrace();

			session.setAttribute("message", new Message("Something went wrong!!! " + e.getMessage(), "alert-danger"));
			return "user/add-car";
		}

	}

	@GetMapping("/search")
	public ModelAndView search(@RequestParam String keyword, HttpSession session, Model model) {

		List<Car> result = carService.search(keyword);
		model.addAttribute("title", "Search Store - Know Your Neighborhood");
		model.addAttribute("makers", makeService.findAll());
		ModelAndView mav = new ModelAndView("user/user-home");
		if (!result.isEmpty()) {
			mav.addObject("cars", result);
		} else {
			session.setAttribute("err", "Sorry!! Not Found");
		}
		return mav;
	}

	@GetMapping("/show-cars/{page}")
	public String showContactsuser(@PathVariable("page") Integer page, Model m, Principal principal) {
		m.addAttribute("title", "Show Contacts");
		String name = principal.getName();
		System.out.println("UserName " + name);
		User user = this.userRepository.getUserByUsername(name);
		m.addAttribute("user", user);

		Pageable pageable = PageRequest.of(page, 5);

		Page<Car> cars = this.carRepository.findCarByUser(user.getId(), pageable);
		m.addAttribute("cars", cars);
		m.addAttribute("currentPage", page);
		m.addAttribute("totalPages", cars.getTotalPages());

		return "user/my-car-list";
	}

	// showing particular contact details
	@GetMapping("/profile/{id}")
	public String showContactDetails(@PathVariable("id") Long id, Model model, Principal principal) {
		System.out.println("COntact Id " + id);
		String name = principal.getName();
		System.out.println("UserName " + name);
		User user = this.userRepository.getUserByUsername(name);
		model.addAttribute("user", user);
		User users = this.userService.getById(id);
		List<Car> cars = this.carRepository.findCarByUser(user.getId());
		model.addAttribute("cars", cars);
		if (user.getId() == users.getId()) {
			model.addAttribute("user", users);
		}
		return "user/my-profile";

	}

	// open update form handler
	@GetMapping("/update-profile/{uid}")
	public String updateProfile(@PathVariable("uid") Long uid, Principal principal, Model model) {
		String name = principal.getName();
		System.out.println("UserName " + name);
		User userName = this.userRepository.getUserByUsername(name);
		model.addAttribute("title", userName.getName() + " - ABC Cars Pte Ltd ");

		model.addAttribute("user", userName);
		User user = this.userRepository.findById(uid).get();
		model.addAttribute("user", user);
		return "user/update-my-profile";

	}

	// update contact handler
	@PostMapping("/update-profile")
	public String viewProfile(@Valid @ModelAttribute User user,
			BindingResult results,
			@RequestParam("profileImage") MultipartFile file, 
			Model model, Principal principal, HttpSession session)
			throws IOException {
		String name = principal.getName();
		System.out.println("UserName " + name);
		User userName = this.userRepository.getUserByUsername(name);

		model.addAttribute("title", userName.getName() + " - ABC Cars Pte Ltd ");

		// old contact details
		User oldUser = this.userRepository.findById(user.getId()).get();

		if (results.hasErrors()) {
			System.out.println("ERROR" + results + toString());
			model.addAttribute("user", user);
			return "user/update-my-profile";
		}

		try {
			if (!file.isEmpty()) {

				// delete old photo
				File deleteFile = new ClassPathResource("static/image").getFile();
				File file2 = new File(deleteFile, oldUser.getImageUrl());
				file2.delete();
				// updte new photo
				File saveFile = new ClassPathResource("static/image").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separatorChar + file.getOriginalFilename());

				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Image is uploaded");
				user.setImageUrl(file.getOriginalFilename());
				session.setAttribute("message", new Message("Your content is updated", "alert-success"));

			} else {
				user.setImageUrl(oldUser.getImageUrl());

			}
			Role roleUser = roleRepository.findByName("USER");
			user.addRole(roleUser);
			user.setEnable(true);

			this.userRepository.save(user);
			session.setAttribute("message", new Message("Successfully Updated ", "alert-success"));

			System.out.println("Contecr jhjhs====  " + user.getId());
			return "redirect:/user/profile/" + user.getId();
		} catch (Exception e) {
			session.setAttribute("message", new Message("Something Went Wrong ", "alert-danger"));
			return "user/update-my-profile";
		}

	}
	@GetMapping("/cars/delete/{id}")
	public String deleteCar(@PathVariable ("id") Long id) {
		Car car =this.carRepository.findById(id).get();

		List<CarBidding> carBiddings = carBiddingRepository.findAll();
		List<CarBidding> userCarBidding = new ArrayList<>();
			for (CarBidding carBiddingss : carBiddings) {
				if (carBiddingss.getCar().getId() == car.getId()) {
					carBiddingss.setResponses(null);
					userCarBidding.add(carBiddingss);
				}
			}
		
		car.setStatus(null);
		this.carRepository.delete(carRepository.findById(id).get());
		return "redirect:/user/show-cars/0";
	}
	
	
	@GetMapping("/cars/update-car/{id}")
	public String upadtaeCarPagess(@PathVariable Long id, Model model) {

		Car car = carService.updateCar(id).get();
		CarDTO carDTO = new CarDTO();
		carDTO.setId(car.getId());
		carDTO.setModel(car.getModel());
		carDTO.setMakeId(car.getMake().getId());
		carDTO.setYearId(car.getYear().getId());
		carDTO.setCountriesId(car.getCountries().getId());
		carDTO.setTypeId(car.getType().getId());
		carDTO.setFuelId(car.getFuel().getId());
		carDTO.setPrice(car.getPrice());
		carDTO.setMileage(car.getMileage());
		carDTO.setCc(car.getCc());
		carDTO.setColor(car.getColor());
		carDTO.setRegYear(car.getRegYear());
		carDTO.setDescription(car.getDescription());


		model.addAttribute("makers", makeService.findAll());
		model.addAttribute("year", yearService.findAll());
		model.addAttribute("fuels", fuelService.findAll());
		model.addAttribute("types", typeService.findAll());
		model.addAttribute("countries", countriesService.findAll());
		model.addAttribute("status",statusService.findAll());
		carDTO.setImgShow(car.getImgShow());
		carDTO.setImg1(car.getImg1());
		carDTO.setImg2(car.getImg2());
		carDTO.setImg3(car.getImg3());

		model.addAttribute("carDTO", carDTO);

		return "user/add-car";

	}
	
	
	
	
	@RequestMapping("/contactUs")
	public String contactUsUser(Model model) {
		model.addAttribute("title","Contact Us - ABC Cars Pte Ltd’");
		model.addAttribute("contact", new Contact());
		return "user/contact-us";
	}

	
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
			return "user/contact-us";
		}

		try {
		
			contact.setUser(user);
			this.contactRepository.save(contact);

			System.out.println("USER" + contact);

			model.addAttribute("contact", new Contact());
			session.setAttribute("message", new Message("Successfully sent the message!!!", "alert-success"));
			return "user/contact-us";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("contact",contact);
			session.setAttribute("message", new Message("Something went wrong!!! "+e.getMessage(), "alert-danger"));
			return "user/contact-us";
		}
	}
	
	
	
	
	// open update form handler
	@GetMapping("/car/activation/{cid}")
	public String updateForn(@PathVariable ("cid") Long cid, Model model) {
		model.addAttribute("title","Upadate Contact");
		
		Car car= this.carRepository.findById(cid).get();
		model.addAttribute("status",statusRepository.findAll());
		model.addAttribute("makers", makeService.findAll());
		model.addAttribute("year", yearService.findAll());
		model.addAttribute("fuels", fuelService.findAll());
		model.addAttribute("type", typeService.findAll());
		model.addAttribute("countries", countriesService.findAll());
		model.addAttribute("car",car);
		return "user/activation";
		
	}

	// update contact handler
	@PostMapping("/car/activation")
	public String view(@ModelAttribute Car car,Model model,@ModelAttribute  CarBidding carBidding,
			Principal principal,
			HttpSession session) throws IOException {
		
		//old contact details
		User user = this.userRepository.getUserByUsername(principal.getName());
		car.setUser(user);
		model.addAttribute("makers", makeService.findAll());
		model.addAttribute("year", yearService.findAll());
		model.addAttribute("fuels", fuelService.findAll());
		model.addAttribute("type", typeService.findAll());
		model.addAttribute("countries", countriesService.findAll());
		model.addAttribute("status",statusService.findAll());
		
		this.carRepository.save(car);
		System.out.println("User:  "+car.getId());
		return "redirect:/user/show-cars/0";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
