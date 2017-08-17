package com.poalim.openshift.quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by osher on 19/7/17.
 */
@RestController
@RequestMapping("/quote")
public class QuoteController {

    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

    @Autowired
    private QuoteService quoteService;

    @RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<QuoteDTO> findById(@PathVariable("companyId") final String companyId) {

        logger.info("QuoteController-findById: {}", companyId);
        return new ResponseEntity<>(this.quoteService.findByCompanyId(companyId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<QuoteDTO>> queryByCompanyName(
            @RequestParam(value="name", required=false) String name) {

        logger.info("QuoteController-queryByCompanyName: {}", name);
        List<QuoteDTO> result = (name == null) ? this.quoteService.findAll() :
                                                this.quoteService.queryByCompanyId(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> createQuote(@RequestBody QuoteDTO quoteDTO,
                                                UriComponentsBuilder builder) {

        logger.info("QuoteController-createQuote: {}", quoteDTO);

        String companyId = this.quoteService.createQuate(quoteDTO);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(builder.path("/quote/{companyId}").buildAndExpand(companyId).toUri());
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

}
