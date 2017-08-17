package com.poalim.openshift.company;

import com.poalim.openshift.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by osher on 20/7/17.
 */

@Service
public class CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        logger.debug("CompanyService-findAll");
        return Optional.ofNullable(companyRepository.findAll()).orElse(Collections.emptyList()) ;
    }

    public Company findById(String id) {
        logger.debug("CompanyService-findById: id={}", id);
        return companyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Company not found:" + id));
    }

    public List<Company> findAllByNameContaining(String name) {
        logger.debug("CompanyService-findAllByNameContaining: name={}", name);
        return this.companyRepository.findByNameIgnoreCaseContaining(name)
                .orElse(Collections.emptyList());
    }

    @Transactional
    public String createCompany(Company company) {
        logger.debug("CompanyService-createAccount: account: {}", company.toString());
        return this.save(company).getId();
    }

    @Transactional
    private Company save(Company company){
        return companyRepository.save(company);
    }

}
