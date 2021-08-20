package com.cheetah.istat.comuni.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetah.istat.comuni.model.Comune;
import com.cheetah.istat.comuni.repositories.ComuniRepository;

@RestController
@RequestMapping("/comuni")
public class ComuniController {
	
	@Autowired
	ComuniRepository repo;
	
	@GetMapping
	public ResponseEntity<List<Comune>> listAll() {
		
		List<Comune> comuni = repo.findAll(Sort.by(Direction.ASC, "comune"));
		if(comuni.size()==0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(comuni);

		
	}
	
	@GetMapping(path="/{comune}",produces = "application/json")
	public ResponseEntity<Comune> byName(@PathVariable("comune") String comune){
		
		Optional<Comune> opt = repo.findByComune(comune);
		if(!opt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(opt.get());
		
	}
	
	
	/**
	 * Find all comuni inside a regione
	 * @param regione
	 * @return
	 */
	@GetMapping(path="/byRegione/{regione}",produces = "application/json")
	public ResponseEntity<List<Comune>> comuniByRegione(@PathVariable("regione") String regione){
		
		List<Comune> comuni = repo.findByRegione(regione,Sort.by(Direction.ASC, "comune"));
		if(comuni == null || comuni.size()==0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(comuni);
		
	}
	
	/**
	 * Find all comuni inside a provincia
	 * @param regione
	 * @return
	 */
	@GetMapping(path="/byProvincia/{provincia}",produces = "application/json")
	public ResponseEntity<List<Comune>> comuniByProvincia(@PathVariable("provincia") String provincia){
		
		List<Comune> comuni = repo.findByProvincia(provincia,Sort.by(Direction.ASC, "comune"));
		if(comuni == null || comuni.size()==0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(comuni);
		
	}

}
