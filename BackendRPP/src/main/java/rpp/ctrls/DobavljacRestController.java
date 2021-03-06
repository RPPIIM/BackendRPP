package rpp.ctrls;

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
import rpp.jpa.Dobavljac;
import rpp.reps.DobavljacRepository;

@RestController
@Api(tags = {"Dobavljač CRUD operacije"})
public class DobavljacRestController {
	@Autowired
	private DobavljacRepository dobavljacRepository;

	@GetMapping("dobavljac")
	@ApiOperation(value = "Vraća kolekciju svih dobavljača iz baze podataka")
	public Collection<Dobavljac> getDobavljaci(){
		return dobavljacRepository.findAll();
	}
	
	@GetMapping("dobavljac/{id}")
	@ApiOperation(value = "Vraća dobavljača iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	public Dobavljac getDobavljac(@PathVariable("id") Integer id) {
		return dobavljacRepository.getOne(id);
	}
	
	@GetMapping("dobavljacNaziv/{naziv}")
	@ApiOperation(value = "Vraća kolekciju dobavljača iz baze podataka koji u nazivu sadrže string prosleđen kao path varijabla")
	public Collection<Dobavljac> getDobavljacByNaziv(@PathVariable("naziv") String naziv){
		return dobavljacRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping("dobavljac/{id}")
	@ApiOperation(value = "Briše dobavljača iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	public ResponseEntity<Dobavljac> deleteDobavljac(@PathVariable ("id") Integer id){
		if(!dobavljacRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		dobavljacRepository.deleteById(id);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	// insert
	@PostMapping("dobavljac")
	@ApiOperation(value = "Upisuje dobavljača u bazu podataka")
	public ResponseEntity<Dobavljac> insertDobavljac(@RequestBody Dobavljac dobavljac){
		if(dobavljacRepository.existsById(dobavljac.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		dobavljacRepository.save(dobavljac);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// update
	@PutMapping("dobavljac")
	@ApiOperation(value = "Modifikuje postojećeg dobavljača u bazi podataka")
	public ResponseEntity<Dobavljac> updateDobavljac(@RequestBody Dobavljac dobavljac){
		if(dobavljacRepository.existsById(dobavljac.getId())) {
			dobavljacRepository.save(dobavljac);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
}
