package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.ItemsRepository;
import it.jac.sistemi.dto.ItemDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.Item;

@Service
public class ItemService {

	private static Logger log = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	
	public Response<Item> createItem(Item item) {

		Response<Item> response = new Response<Item>();

		try {

			response.setResult(this.itemsRepository.save(item));

		} catch (Exception e) {

			response.setError("Item non creato");

		}

		return response;

	}


	public Response<String> deleteItem(Item item) {

		Response<String> response = new Response<String>();
		
		/*List<CandidateAnswer> candidateAnswers = new ArrayList<CandidateAnswer>();
		
		List<CandidateSkill> candidateSkills = new ArrayList<CandidateSkill>();*/

		try {

			/*candidateAnswers = this.candidateAnswerRepository.findByIdCandidate(id);
			if (candidateAnswers != null) {
				
				for (CandidateAnswer ca: candidateAnswers) {
					
					this.candidateAnswerRepository.delete(ca);
					
				}
				
			}
			
			candidateSkills = this.candidateSkillRepository.findByIdCandidate(id);
			if (candidateSkills != null) {
				
				for (CandidateSkill cs: candidateSkills) {
					
					this.candidateSkillRepository.delete(cs);
					
				}
				
			}*/
			
			this.itemsRepository.delete(item);			

			response.setResult("item eliminato.");

		} catch (Exception e) {

			response.setError("Item non eliminato.");

		}

		return response;

	}


	public Response<List<ItemDTO>> findAllItems() {

		Response<List<ItemDTO>> response = new Response<List<ItemDTO>>();

		List<ItemDTO> result = new ArrayList<>();

		try {

			Iterator<Item> iterator = this.itemsRepository.findAll().iterator();

			while(iterator.hasNext()) {

				Item item = iterator.next();
				result.add(ItemDTO.build(item));

			}

			response.setResult(result);

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}
	
}
