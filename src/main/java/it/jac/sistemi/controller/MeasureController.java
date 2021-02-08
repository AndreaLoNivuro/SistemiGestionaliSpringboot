package it.jac.sistemi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.sistemi.service.MeasureService;

@RestController
@RequestMapping("/measure")
public class MeasureController {
	
	private static Logger log = LoggerFactory.getLogger(MeasureController.class);

	@Autowired
	private MeasureService measureService;

}
