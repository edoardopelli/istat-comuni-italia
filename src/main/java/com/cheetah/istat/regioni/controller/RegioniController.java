package com.cheetah.istat.regioni.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cheetah.istat.raw.data.Root;
import com.cheetah.istat.regioni.model.Regione;
import com.cheetah.istat.regioni.repositories.RegioniRepository;
import com.cheetah.istat.repositories.RootRepository;

@RestController
public class RegioniController {

	
	@Autowired
	RegioniRepository repo;
	
	@Autowired
	RootRepository rootRepo;
	/**
	 * Returns all regions sorted by regione
	 * @return
	 */
	@GetMapping(path="/regioni/listAll",produces = "application/json")
	public ResponseEntity<List<Regione>> listAll(){
		
		List<Regione> regioni = repo.findAll(Sort.by(Direction.ASC, "regione"));
		if(regioni.size()==0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(regioni);
		
	}

	@GetMapping(path="/regioni/byName/{regione}",produces = "application/json")
	public ResponseEntity<Regione> byNmae(@PathVariable("regione") String regione){
		
		Optional<Regione> opt = repo.findByRegione(regione);
		if(!opt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(opt.get());
		
	}
	
	@GetMapping(path="/regioni/rawdata",produces = "application/json")
	public ResponseEntity<List<Root>> rawdata(){
		
		List<Root> opt = rootRepo.findAll();
		if(opt==null || opt.size()==0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(opt);
		
	}


}
