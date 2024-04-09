package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.repository.DemandaRepository;

@Service
public class DemandaService {
	@Autowired
	DemandaRepository demandaRepository;
}
