package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.CoordenadorExtensao;
import app.service.CoordenadorExtensaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coordenadorExtensao")
@CrossOrigin("*")
@Validated
public class CoodenadorExtensaoController {

	@Autowired
	private CoordenadorExtensaoService coordenadorExtensaoService;

	@PostMapping("/save")
	public ResponseEntity<CoordenadorExtensao> save(@Valid @RequestBody CoordenadorExtensao coordenadorExtensao) {
		try {
			return new ResponseEntity<CoordenadorExtensao>(this.coordenadorExtensaoService.save(coordenadorExtensao), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CoordenadorExtensao> update(@Valid @RequestBody CoordenadorExtensao coordenadorExtensao, @PathVariable int id) {
		try {
			return new ResponseEntity<CoordenadorExtensao>(this.coordenadorExtensaoService.update(id, coordenadorExtensao), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<CoordenadorExtensao>> findAll() {
		try {
			List<CoordenadorExtensao> lista = this.coordenadorExtensaoService.findAll();
			return new ResponseEntity<List<CoordenadorExtensao>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<CoordenadorExtensao> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<CoordenadorExtensao>(this.coordenadorExtensaoService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<CoordenadorExtensao> deleteById(@PathVariable long id) {
		try {
			return new ResponseEntity<CoordenadorExtensao>(this.coordenadorExtensaoService.deleteById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
