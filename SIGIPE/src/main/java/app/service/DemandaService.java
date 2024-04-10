package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Demanda;
import app.repository.DemandaRepository;

@Service
public class DemandaService {
	@Autowired
	DemandaRepository demandaRepository;

	public List<Demanda> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Demanda findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
