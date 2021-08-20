package com.cheetah.istat.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetah.istat.raw.data.Root;

public interface RootRepository extends MongoRepository<Root, String> {

	Optional<Root> findByLastUpdate(int last_update);

}
