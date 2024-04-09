package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import app.service.DemandaService;

@RestController
public class DemandaController {
	@Autowired
	DemandaService demandaService;
}
