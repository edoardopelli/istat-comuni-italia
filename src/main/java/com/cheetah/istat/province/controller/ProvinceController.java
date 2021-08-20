package com.cheetah.istat.province.controller;

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

import com.cheetah.istat.province.model.Provincia;
import com.cheetah.istat.province.repositories.ProvinceRepository;

@RestController
@RequestMapping("/province")
public class ProvinceController {

	@Autowired
	ProvinceRepository repo;
	
	@GetMapping
	public ResponseEntity<List<Provincia>> listAll() {
		
		List<Provincia> province = repo.findAll(Sort.by(Direction.ASC, "provincia"));
		if(province.size()==0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(province);

		
	}
	
	@GetMapping(path="/{provincia}",produces = "application/json")
	public ResponseEntity<Provincia> byName(@PathVariable("provincia") String provincia){
		
		Optional<Provincia> opt = repo.findByProvincia(provincia);
		if(!opt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(opt.get());
		
	}
	
	
	/**
	 * Find all province inside a regione
	 * @param regione
	 * @return
	 */
	@GetMapping(path="/byRegione/{regione}",produces = "application/json")
	public ResponseEntity<List<Provincia>> provinceByRegione(@PathVariable("regione") String regione){
		
		List<Provincia> province = repo.findByRegione(regione,Sort.by(Direction.ASC,"provincia"));
		if(province == null || province.size()==0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(province);
		
	}
}
