package com.telefonica.jee.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.telefonica.jee.model.Manufacturer;
import com.telefonica.jee.repository.ManufacturerRepository;
import com.telefonica.jee.repository.ProductRepository;

@Controller
public class ManufacturerController {

	private final Logger log = LoggerFactory.getLogger(ManufacturerController.class);

	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	

	@GetMapping("/manufacturers")
	public ModelAndView getAllManufacturers() {
		log.debug("request to get Manufacturers");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manufacturer-list");
		mav.addObject("manufacturers", manufacturerRepository.findAll());
		return mav;
	}

	@GetMapping("/manufacturers/empty")
	public ModelAndView createManufacturer() {
		log.debug("request to empty manufacturer form");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manufacturer-edit");
		mav.addObject("manufacturer", new Manufacturer());
		mav.addObject("products", productRepository.findAll());
		return mav;
	}
	
	@GetMapping("/manufacturers/{id}")
	public ModelAndView getManufacturer(@PathVariable Long id) {
		log.debug("request to get Manufacturer : {}", id);
		Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);

		ModelAndView mav = new ModelAndView();
		if (manufacturer.isPresent()) {
			mav.setViewName("manufacturer-edit");
			mav.addObject("manufacturer", manufacturer.get());
		} else {
			mav.setViewName("manufacturer-list");
			mav.addObject("message", "manufacturer not found");
		}
		return mav;
	}

	@PostMapping("/manufacturers")
	public String saveManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
		log.debug("request to save manufacturer : {}", manufacturer);

		// El Manufacturer no existe y tenemos que crearlo
		if (manufacturer.getId() == null) {
			manufacturerRepository.save(manufacturer);
			return "redirect:/manufacturers";
		}

		// El Manufacturer ya existe y tenemos que actualizarlo
		Optional<Manufacturer> existingManufacturerWrap = manufacturerRepository.findById(manufacturer.getId());
		if (existingManufacturerWrap.isPresent()) {
			Manufacturer existingManufacturer = existingManufacturerWrap.get();
			existingManufacturer.setName(manufacturer.getName());
			existingManufacturer.setDepartment(manufacturer.getDepartment());
			existingManufacturer.setProducts(manufacturer.getProducts());
			manufacturerRepository.save(existingManufacturer);
		}
		return "redirect:/manufacturers";
	}

	@GetMapping("/manufacturers/{id}/delete")
	public String deleteManufacturer(@PathVariable Long id) {
		log.debug("request to delete manufacturers : {}", id);
		manufacturerRepository.deleteById(id);
		return "redirect:/manufacturers" ;
	}
	

}
