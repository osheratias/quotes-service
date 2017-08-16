package com.poalim.openshift.company;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by osher on 20/7/17.
 */
public interface CompanyRepository extends MongoRepository<Company, String> {
    Optional<Company> findById(String id);
    Optional<List<Company>> findByNameIgnoreCaseContaining(String name);
}
