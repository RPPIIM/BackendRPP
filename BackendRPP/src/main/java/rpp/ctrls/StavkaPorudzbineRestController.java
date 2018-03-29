package rpp.ctrls;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rpp.jpa.Porudzbina;
import rpp.jpa.StavkaPorudzbine;
import rpp.reps.PorudzbinaRepository;
import rpp.reps.StavkaPorudzbineRepository;

@RestController
@Api(tags = {"Stavka porudžbine CRUD operacije"})
public class StavkaPorudzbineRestController {

	@Autowired
	private StavkaPorudzbineRepository stavkaPorudzbineRepository;
	
	@Autowired
	private PorudzbinaRepository porudzbinaRepository;
	
	@GetMapping("stavkaPorudzbine")
	@ApiOperation(value = "Vraća kolekciju svih stavki porudžbina iz baze podataka")
	public Collection<StavkaPorudzbine> getStavkePorudzbine() {
		return stavkaPorudzbineRepository.findAll();
	}
	
	@GetMapping(value = "stavkaPorudzbine/{id}")
	@ApiOperation(value = "Vraća stavku porudžbine iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	public ResponseEntity<StavkaPorudzbine> getStavkaPorudzbine(@PathVariable ("id") Integer id) {
		StavkaPorudzbine stavkaPorudzbine = stavkaPorudzbineRepository.getOne(id);
		return new ResponseEntity<StavkaPorudzbine> (stavkaPorudzbine, HttpStatus.OK);
	}
	
	@GetMapping(value = "stavkeZaPorudzbinuId/{id}")
	@ApiOperation(value = "Vraća sve stavke porudžbine iz baze podataka vezane za porudžbinu čiji je id vrednost prosleđena kao path varijabla")
	public Collection<StavkaPorudzbine> stavkaPoPorudzbiniId(@PathVariable("id") Integer id){
		Porudzbina p = porudzbinaRepository.getOne(id);
		return stavkaPorudzbineRepository.findByPorudzbina(p);
	}
	
	@GetMapping (value="stavkaPorudzbineCena/{cena}")
	public Collection<StavkaPorudzbine> getStavkaPorudzbineCena(@PathVariable("cena") BigDecimal cena){
		return stavkaPorudzbineRepository.findByCenaLessThanOrderById(cena);
	}
	
	@DeleteMapping (value="stavkaPorudzbine/{id}")
	@ApiOperation(value = "Briše stavku porudžbine iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	public ResponseEntity<StavkaPorudzbine> deleteStavkaPorudzbine (@PathVariable("id") Integer id){
		if(!stavkaPorudzbineRepository.existsById(id))
			return new ResponseEntity<StavkaPorudzbine> (HttpStatus.NO_CONTENT);
		stavkaPorudzbineRepository.deleteById(id);
		return new ResponseEntity<StavkaPorudzbine> (HttpStatus.OK);
	}
	
	// insert
	@PostMapping (value="stavkaPorudzbine")
	@ApiOperation(value = "Upisuje stavku porudžbine u bazu podataka")
	public ResponseEntity<Void> insertStavkaPorudzbine (@RequestBody StavkaPorudzbine stavkaPorudzbine){
		if(stavkaPorudzbineRepository.existsById(stavkaPorudzbine.getId()))
			return new ResponseEntity<Void> (HttpStatus.CONFLICT);
		stavkaPorudzbine.setRedniBroj(stavkaPorudzbineRepository.nextRBr(stavkaPorudzbine.getPorudzbina().getId()));
		stavkaPorudzbineRepository.save(stavkaPorudzbine);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	// update
	@PutMapping (value = "stavkaPorudzbine")
	@ApiOperation(value = "Modifikuje postojeću stavku porudžbine u bazi podataka")
	public ResponseEntity<Void> updateStavkaPorudzbine (@RequestBody StavkaPorudzbine stavkaPorudzbine){
		if(!stavkaPorudzbineRepository.existsById(stavkaPorudzbine.getId()))
			return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
		stavkaPorudzbineRepository.save(stavkaPorudzbine);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
}
	