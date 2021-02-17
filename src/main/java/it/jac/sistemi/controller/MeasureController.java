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
import it.jac.sistemi.entity.Measure;
import it.jac.sistemi.service.MeasureService;

@RestController
@RequestMapping("/measure")
public class MeasureController {
	
	private static Logger log = LoggerFactory.getLogger(MeasureController.class);

	@Autowired
	private MeasureService measureService;
	
	@PostMapping("/create")
	public Response<?> createMeasure(
			@RequestBody Measure measure
			) {
		
		log.info("Richiesta di create Measure.");

		return measureService.createMeasure(measure);

	}
	
	@GetMapping(path="/findAll")
	public Response<?> findAllMeasures() {
		
		log.info("Richiesta di find all Measures.");
		
		return measureService.findAllMeasures();
		
	}
	
	@PostMapping(path = "/delete")
	public Response<?> deleteMeasure(
			@RequestBody Measure measure
			) {

		log.info("Richiesta di delete Measure.");

		return measureService.deleteMeasure(measure);
		
	}


}
