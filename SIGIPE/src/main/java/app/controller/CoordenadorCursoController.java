package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.CoordenadorCurso;
import app.service.CoordenadorCursoService;

@RestController
@RequestMapping("/api/coordenadorCurso")
@CrossOrigin("*")
public class CoordenadorCursoController {

	@Autowired
	private CoordenadorCursoService coordenadorCursoService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody CoordenadorCurso coordenadorCurso) {

		try {

			String mensagem = this.coordenadorCursoService.save(coordenadorCurso);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody CoordenadorCurso coordenadorCurso, @PathVariable int id) {

		try {

			String mensagem = this.coordenadorCursoService.update(id, coordenadorCurso);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/listAll")
	public ResponseEntity<List<CoordenadorCurso>> listAll() {

		try {

			List<CoordenadorCurso> lista = this.coordenadorCursoService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/findById/{idCoordenadorCurso}")
	public ResponseEntity<CoordenadorCurso> findById(@PathVariable long idCoordenadorCurso) {

		try {

			CoordenadorCurso coordenadorCurso = this.coordenadorCursoService.findById(idCoordenadorCurso);
			return new ResponseEntity<>(coordenadorCurso, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/delete/{idCoordenadorCurso}")
	public ResponseEntity<String> delete(@PathVariable long idCoordenadorCurso) {

		try {

			String mensagem = this.coordenadorCursoService.delete(idCoordenadorCurso);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
}
