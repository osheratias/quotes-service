package com.poalim.openshift.quote;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by osher on 19/7/17.
 */

public interface QuoteRepository extends MongoRepository<Quote, String> {
    Optional<Quote> findByCompanyId(String companyId);
    Optional<List<Quote>> findByCompanyIdIgnoreCaseContaining(String companyId);
}

