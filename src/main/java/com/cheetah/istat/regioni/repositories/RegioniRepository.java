package com.cheetah.istat.regioni.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cheetah.istat.regioni.model.Regione;

public interface RegioniRepository extends MongoRepository<Regione, String> {

	@Query(value = "{'regione': {$regex : ?0, $options: 'i'}}")
	Optional<Regione> findByRegione(String regione);

}
