package com.car.controller;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.car.category.repository.YearReprsitory;
import com.car.category.services.CountriesService;
import com.car.category.services.FuelService;
import com.car.category.services.MakeService;
import com.car.category.services.TypeService;
import com.car.category.services.YearService;
import com.car.entites.Countries;
import com.car.entites.Fuel;
import com.car.entites.Make;
import com.car.entites.Type;
import com.car.entites.User;
import com.car.entites.Year;
import com.car.helper.Message;
import com.car.repository.UserReopsitory;
import com.car.service.CarService;

@Controller
@RequestMapping("/admin")
public class CategoryController {

	@Autowired
	MakeService makeService;
	@Autowired
	YearService yearService;
	@Autowired
	YearReprsitory yearReprsitory;
	@Autowired
	CountriesService countriesService;
	@Autowired
	CarService carService;
	@Autowired
	FuelService fuelService;
	@Autowired
	TypeService typeService;
	@Autowired
	UserReopsitory userRepository;

	// method for adding commom data response
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("UserName " + userName);
		User user = this.userRepository.getUserByUsername(userName);
		System.out.println("USer " + user);

		model.addAttribute("user", user);
	}

	// ===============Make, Year, Countries Show Page================

	@GetMapping("/cars/maker/{page}")
	public String getAll(@PathVariable("page") Integer page, Model model) {

		Page<Year> years = this.yearService.findPage(page);
		Page<Countries> countries = this.countriesService.findPage(page);
		Page<Make> makers = this.makeService.findPage(page);
		Page<Fuel> fuels = this.fuelService.findPage(page);
		Page<Type> types = this.typeService.findPage(page);
		model.addAttribute("makers", makers);
		model.addAttribute("countries", countries);
		model.addAttribute("years", years);
		model.addAttribute("fuels", fuels);
		model.addAttribute("types", types);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", makers.getTotalPages());

		return "admin/category";

	}

	// ===============Maker Add Page ================
	@GetMapping("/cars/maker/add")
	public String addMake(Model model) {

		model.addAttribute("make", new Make());

		return "admin/makeAdd";

	}

	// ===============Maker Add ================
	@PostMapping("/cars/maker/add")
	public String postAddMaker(@ModelAttribute("make") Make make, HttpSession session)
			throws SQLIntegrityConstraintViolationException {

		makeService.save(make);
		return "redirect:/admin/cars/maker/0";

	}

	// ===============Maker Delete ================
	@GetMapping("/cars/maker/delete/{id}")
	public String deleteMaker(@PathVariable("id") int id, HttpSession session) {
		try {
			makeService.removeMakeById(id);

			session.setAttribute("message", new Message("Maker Successfully Deleted!!! ID NO: " + id, "alert-success"));

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/cars/maker/0";
		}
		return "redirect:/admin/cars/maker/0";

	}

	// ===============Maker Update ================
	@GetMapping("/cars/maker/update/{id}")
	public String updateMaker(@PathVariable("id") int id, Model model) {
		Optional<Make> maker = makeService.getMakeById(id);
		if (maker.isPresent()) {
			model.addAttribute("make", maker.get());
			return "admin/makeAdd";

		} else {
			return "404";
		}
	}

	// ===============Year Add Page================
	@GetMapping("/cars/year/add")
	public String addYear(Model model) {

		model.addAttribute("year", new Year());
		return "admin/yearAdd";

	}

	// ===============Year Add ================
	@PostMapping("/cars/year/add")
	public String postAddYear(@ModelAttribute("year") Year year) {
		yearService.save(year);
		return "redirect:/admin/cars/maker/0";

	}

// ===============Year Delete ================	
	@GetMapping("/cars/year/delete/{id}")
	public String deleteYear(@PathVariable("id") int id, HttpSession session) {
		try {
			yearService.removeYearById(id);

			session.setAttribute("message", new Message("Year Successfully Deleted!!! ID NO: " + id, "alert-success"));

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/cars/maker/0";
		}
		return "redirect:/admin/cars/maker/0";

	}

// ===============Year Update ================	
	@GetMapping("/cars/year/update/{id}")
	public String updateYear(@PathVariable("id") int id, Model model) {
		Optional<Year> year = yearService.getYearById(id);
		if (year.isPresent()) {
			model.addAttribute("year", year.get());
			return "admin/yearAdd";

		} else {
			return "404";
		}
	}

	// ===============Countries Add Page================
	@GetMapping("/cars/countries/add")
	public String addCountries(Model model) {

		model.addAttribute("country", new Countries());
		return "admin/countriesAdd";

	}

	// ===============Countries Add ================
	@PostMapping("/cars/countries/add")
	public String postAddCountries(@Valid @ModelAttribute("country") Countries countries, HttpSession session) {
		try {
			countriesService.save(countries);
			return "redirect:/admin/cars/maker/0";
		} catch (DuplicateKeyException e) {
			// TODO Auto-generated catch block
			session.setAttribute("message", new Message("Country Successfully Registered!!!", "alert-success"));
		}
		return "admin/countriesAdd";

	}

	// ===============Countries Delete ================
	@GetMapping("/cars/countries/delete/{id}")
	public String deleteCountries(@PathVariable("id") int id, HttpSession session) {
		try {
			yearService.removeYearById(id);

			session.setAttribute("message", new Message("Successfully Deleted!!! ID NO: " + id, "alert-success"));

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/cars/maker/0";
		}
		return "redirect:/admin/cars/maker/0";

	}

	// ===============Countries Update ================
	@GetMapping("/cars/countries/update/{id}")
	public String updateCountries(@PathVariable("id") int id, Model model) {
		Optional<Countries> countries = countriesService.getCountriesById(id);
		if (countries.isPresent()) {
			model.addAttribute("country", countries.get());
			return "admin/countriesAdd";

		} else {
			return "404";
		}
	}

	// ===============Fuel Add Page================
	@GetMapping("/cars/fuel/add")
	public String addFuel(Model model) {

		model.addAttribute("fuel", new Fuel());
		return "admin/fuelAdd";

	}

	// ===============Fuel Add ================
	@PostMapping("/cars/fuel/add")
	public String postAddFuel(@Valid @ModelAttribute("country") Fuel fuel, HttpSession session) {
		try {
			fuelService.save(fuel);
			return "redirect:/admin/cars/maker/0";
		} catch (DuplicateKeyException e) {
			// TODO Auto-generated catch block
			session.setAttribute("message", new Message("Fuel Successfully Registered!!!", "alert-success"));
		}
		return "admin/fuelAdd";

	}

	// ===============Fuel Delete ================
	@GetMapping("/cars/fuel/delete/{id}")
	public String deleteFuel(@PathVariable("id") int id, HttpSession session) {
		try {
			fuelService.removeFuelById(id);
			;

			session.setAttribute("message", new Message("Successfully Deleted!!! ID NO: " + id, "alert-success"));

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/cars/maker/0";
		}
		return "redirect:/admin/cars/maker/0";

	}

	// ===============Fuel Update ================
	@GetMapping("/cars/fuel/update/{id}")
	public String updateFuel(@PathVariable("id") int id, Model model) {
		Optional<Fuel> fuel = fuelService.getFuelById(id);
		if (fuel.isPresent()) {
			model.addAttribute("fuel", fuel.get());
			return "admin/fuelAdd";

		} else {
			return "404";
		}
	}

	// ===============type Add Page================
	@GetMapping("/cars/type/add")
	public String addType(Model model) {

		model.addAttribute("type", new Type());
		return "admin/typeAdd";

	}

	// ===============Fuel Add ================
	@PostMapping("/cars/type/add")
	public String postAddType(@Valid @ModelAttribute("type") Type type, HttpSession session) {
		try {
			typeService.save(type);
			return "redirect:/admin/cars/maker/0";
		} catch (DuplicateKeyException e) {
			// TODO Auto-generated catch block
			session.setAttribute("message", new Message("Fuel Successfully Registered!!!", "alert-success"));
		}
		return "admin/typeAdd";

	}

	// ===============Fuel Delete ================
	@GetMapping("/cars/type/delete/{id}")
	public String deleteType(@PathVariable("id") int id, HttpSession session) {
		try {
			typeService.removeTypeById(id);

			session.setAttribute("message", new Message("Successfully Deleted!!! ID NO: " + id, "alert-success"));

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/cars/maker/0";
		}
		return "redirect:/admin/cars/maker/0";

	}

	// ===============Fuel Update ================
	@GetMapping("/cars/type/update/{id}")
	public String updateType(@PathVariable("id") int id, Model model) {
		Optional<Type> type = typeService.getTypeById(id);
		if (type.isPresent()) {
			model.addAttribute("type", type.get());
			return "admin/typeAdd";

		} else {
			return "404";
		}
	}

}
