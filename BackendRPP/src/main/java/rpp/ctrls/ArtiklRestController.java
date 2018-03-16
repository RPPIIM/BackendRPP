package rpp.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rpp.jpa.Artikl;
import rpp.reps.ArtiklRepository;

@RestController
public class ArtiklRestController {
	@Autowired
	private ArtiklRepository artiklRepository;

	@GetMapping("artikl")
	public Collection<Artikl> getArtikli(){
		return artiklRepository.findAll();
	}
}
