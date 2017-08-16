package com.poalim.openshift.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by osher on 20/7/17.
 */

@RestController
@RequestMapping("/company")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<Company> findById(@PathVariable("companyId") final String companyId) {

        logger.info("CompanyController-findById: {}", companyId);
        return new ResponseEntity<>(this.companyService.findById(companyId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Company>> findByName(
                @RequestParam(value="name", required=false) String name) {
        logger.info("CompanyController-findByName: {}", name);
        return new ResponseEntity<>(this.companyService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> createCompany(@RequestBody Company company,
                                                UriComponentsBuilder builder) {

        logger.info("CompanyController-createCompany: {}", company);

        String companyId = this.companyService.createCompany(company);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(builder.path("/company/{id}").buildAndExpand(companyId).toUri());
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }
}
