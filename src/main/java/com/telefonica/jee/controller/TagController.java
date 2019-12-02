package com.telefonica.jee.controller;

import java.util.ArrayList;
import java.util.List;
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

import com.telefonica.jee.model.Product;
import com.telefonica.jee.model.Tag;
import com.telefonica.jee.repository.ProductRepository;
import com.telefonica.jee.repository.TagRepository;

@Controller
public class TagController {

	private final Logger log = LoggerFactory.getLogger(TagController.class);

	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private TagRepository tagRepository;

	@GetMapping("/tags")
	public ModelAndView getAllTags() {
		log.debug("request to get Tags");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tag-list");
		mav.addObject("tags", tagRepository.findAll());
		return mav;
	}

	@GetMapping("/tags/empty")
	public ModelAndView createTag() {
		log.debug("request to empty tags form");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tag-edit");
		mav.addObject("tag", new Tag());
		mav.addObject("products", productRepository.findAll());
		return mav;
	}
	
	@GetMapping("/tags/{id}")
	public ModelAndView getTag(@PathVariable Long id) {
		log.debug("request to get Manufacturer : {}", id);
		Optional<Tag> tag = tagRepository.findById(id);

		ModelAndView mav = new ModelAndView();
		if (tag.isPresent()) {
			mav.setViewName("tag-edit");
			mav.addObject("tag", tag.get());
			mav.addObject("products", productRepository.findAll());
		} else {
			mav.setViewName("tag-list");
			mav.addObject("message", "tag not found");
		}
		return mav;
	}

	@PostMapping("/tags")
	public String saveTag(@ModelAttribute("tag") Tag tag) {
		log.debug("request to save tag : {}", tag);

		if (tag.getId() == null) {
			tagRepository.save(tag);
			return "redirect:/tags";
		}

		Optional<Tag> existingTagWrap = tagRepository.findById(tag.getId());
		if (existingTagWrap.isPresent()) {
			Tag existingTag = existingTagWrap.get();
			existingTag.setName(tag.getName());
			tagRepository.save(existingTag);
		}
		return "redirect:/tags";
	}


	@GetMapping("/tags/{id}/delete")
	public String deleteTag(@PathVariable Long id) {
		log.debug("request to delete tags : {}", id);
		tagRepository.deleteById(id);
		return "redirect:/tags" ;
	}
	

}
