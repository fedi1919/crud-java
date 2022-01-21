package com.fedi.crudjava.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fedi.crudjava.entities.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
}
