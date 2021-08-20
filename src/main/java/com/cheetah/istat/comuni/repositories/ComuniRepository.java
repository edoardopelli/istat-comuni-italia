package com.cheetah.istat.comuni.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cheetah.istat.comuni.model.Comune;

public interface ComuniRepository extends MongoRepository<Comune, String> {

	@Query(value = "{'regione': {$regex : ?0, $options: 'i'}}")
	List<Comune> findByRegione(String regione,Sort sort);

	@Query(value = "{'provincia': {$regex : ?0, $options: 'i'}}")
	List<Comune> findByProvincia(String provincia, Sort sort);

	@Query(value = "{'comune': {$regex : ?0, $options: 'i'}}")
	Optional<Comune> findByComune(String comune);

}
