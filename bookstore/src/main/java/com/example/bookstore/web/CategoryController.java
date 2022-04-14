package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping("/categorylist")
	public String categorylist(Model model) {
		model.addAttribute("categories", crepository.findAll());
		return "categorylist";
	}
	// Palauttaa selaimeen tyhjän kirjalomakkeen
	@RequestMapping(value = "/addcategory", method = RequestMethod.GET)
	public String addcategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	// Tallentaa lomakkeelta lähetetyt kirjatiedot tietokantaan.
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String savecategory(Category category) {
		crepository.save(category);
		return "redirect:/categorylist";
	}
}
