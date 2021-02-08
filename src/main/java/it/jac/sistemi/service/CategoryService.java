package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.CategoriesRepository;
import it.jac.sistemi.dto.CategoryDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.Category;

@Service
public class CategoryService {

	private static Logger log = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	private CategoriesRepository categoriesRepository;

	public Response<Category> createCategory(Category category) {

		Response<Category> response = new Response<Category>();

		try {

			response.setResult(this.categoriesRepository.save(category));

		} catch (Exception e) {

			response.setError("Category non creata");

		}

		return response;

	}


	public Response<String> deleteCategory(Category category) {

		Response<String> response = new Response<String>();

		try {

			this.categoriesRepository.delete(category);			

			response.setResult("Category eliminata.");

		} catch (Exception e) {

			response.setError("Category non eliminata.");

		}

		return response;

	}


	public Response<List<CategoryDTO>> findAllCategories() {

		Response<List<CategoryDTO>> response = new Response<List<CategoryDTO>>();

		List<CategoryDTO> result = new ArrayList<>();

		try {

			Iterator<Category> iterator = this.categoriesRepository.findAll().iterator();

			while(iterator.hasNext()) {

				Category category = iterator.next();
				result.add(CategoryDTO.build(category));

			}

			response.setResult(result);

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

}
