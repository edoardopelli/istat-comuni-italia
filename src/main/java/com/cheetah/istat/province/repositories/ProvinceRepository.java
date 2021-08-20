package com.cheetah.istat.province.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cheetah.istat.province.model.Provincia;

public interface ProvinceRepository extends MongoRepository<Provincia, String> {

	@Query(value = "{'provincia': {$regex : ?0, $options: 'i'}}")
	Optional<Provincia> findByProvincia(String provincia);

	@Query(value = "{'regione': {$regex : ?0, $options: 'i'}}")
	List<Provincia> findByRegione(String regione, Sort sort);

}
