package rpp.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp.jpa.Dobavljac; 

public interface DobavljacRepository extends JpaRepository<Dobavljac, Integer> {

	Collection<Dobavljac> findByNazivContainingIgnoreCase(String naziv);	
	
}
