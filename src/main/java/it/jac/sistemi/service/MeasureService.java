package it.jac.sistemi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.sistemi.dao.MeasuresRepository;
import it.jac.sistemi.dto.MeasureDTO;
import it.jac.sistemi.dto.Response;
import it.jac.sistemi.entity.Measure;

@Service
public class MeasureService {
	
	private static Logger log = LoggerFactory.getLogger(MeasureService.class);

	@Autowired
	private MeasuresRepository measuresRepository;

	public Response<Measure> createMeasure(Measure measure) {

		Response<Measure> response = new Response<Measure>();

		try {

			response.setResult(this.measuresRepository.save(measure));

		} catch (Exception e) {

			response.setError("Measure non creato");

		}

		return response;

	}


	public Response<String> deleteMeasure(Measure measure) {

		Response<String> response = new Response<String>();

		try {

			this.measuresRepository.delete(measure);			

			response.setResult("Measure eliminato.");

		} catch (Exception e) {

			response.setError("Measure non eliminato.");

		}

		return response;

	}


	public Response<List<MeasureDTO>> findAllMeasures() {

		Response<List<MeasureDTO>> response = new Response<List<MeasureDTO>>();

		List<MeasureDTO> result = new ArrayList<>();

		try {

			Iterator<Measure> iterator = this.measuresRepository.findAll().iterator();

			while(iterator.hasNext()) {

				Measure measure = iterator.next();
				result.add(MeasureDTO.build(measure));

			}

			response.setResult(result);

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

}
