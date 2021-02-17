package it.jac.sistemi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.Category;
import it.jac.sistemi.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	private static Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public Response<?> createCategory(
			@RequestBody Category category
			) {
		
		log.info("Richiesta di create Category.");

		return categoryService.createCategory(category);

	}
	
	@GetMapping(path="/findAll")
	public Response<?> findAllCategories() {
		
		log.info("Richiesta di find all Categories.");
		
		return categoryService.findAllCategories();
		
	}
	
	@PostMapping(path = "/delete")
	public Response<?> deleteCategory(
			@RequestBody Category category
			) {

		log.info("Richiesta di delete Category.");

		return categoryService.deleteCategory(category);
		
	}

}
