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

import app.entity.CoordenadorExtensao;
import app.service.CoordenadorExtensaoService;

@RestController
@RequestMapping("/api/coordenadorExtensao")
@CrossOrigin("*")
public class CoordenadorExtensaoController {

	@Autowired
	private CoordenadorExtensaoService coordenadorExtensaoService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody CoordenadorExtensao coordenadorExtensao) {

		try {

			String mensagem = this.coordenadorExtensaoService.save(coordenadorExtensao);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody CoordenadorExtensao coordenadorExtensao, @PathVariable int id) {

		try {

			String mensagem = this.coordenadorExtensaoService.update(id, coordenadorExtensao);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/listAll")
	public ResponseEntity<List<CoordenadorExtensao>> listAll() {

		try {

			List<CoordenadorExtensao> lista = this.coordenadorExtensaoService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/findById/{idCoordenadorExtensao}")
	public ResponseEntity<CoordenadorExtensao> findById(@PathVariable long idCoordenadorExtensao) {

		try {

			CoordenadorExtensao coordenadorExtensao = this.coordenadorExtensaoService.findById(idCoordenadorExtensao);
			return new ResponseEntity<>(coordenadorExtensao, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/delete/{idCoordenadorExtensao}")
	public ResponseEntity<String> delete(@PathVariable long idCoordenadorExtensao) {

		try {

			String mensagem = this.coordenadorExtensaoService.delete(idCoordenadorExtensao);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
