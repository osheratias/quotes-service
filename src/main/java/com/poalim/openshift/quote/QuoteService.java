package com.poalim.openshift.quote;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.poalim.openshift.company.CompanyService;
import com.poalim.openshift.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by osher on 19/7/17.
 */

@Service
public class QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private final ModelMapper modelMapper = new ModelMapper(); // Make it a bean and autowire

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private CompanyService companyService;

    public List<QuoteDTO> findAll() {
        logger.debug("CompanyService-findAll");
        return Optional.ofNullable(quoteRepository.findAll()).orElse(Collections.emptyList()).
                stream().map(quote -> convertToDto(quote)).collect(Collectors.toList());
    }

    public QuoteDTO findByCompanyId(String companyId) {
        logger.debug("QuoteService-findByCompanyId: companyId={}", companyId);
        return this.quoteRepository.findByCompanyId(companyId).
                map(quote -> convertToDto(quote)).orElseThrow(() ->
                new ResourceNotFoundException("Quote not found:" + companyId));
    }

    public List<QuoteDTO> queryByCompanyId(String companyId) {
        logger.debug("QuoteService-queryByCompanyId: companyId={}", companyId);
        List<Quote> quotes = this.quoteRepository.findByCompanyIdIgnoreCaseContaining(companyId).
                                orElse(Collections.emptyList());
        return quotes.stream().map(quote -> convertToDto(quote)).collect(Collectors.toList());
    }

    @Transactional
    public String createQuate(QuoteDTO quoteDTO) {
        logger.debug("QuoteService-createQuate: quoteDTO={}", quoteDTO);
        // Make sure this company exist
        companyService.findById(quoteDTO.getCompanyId());
        return this.save(this.convertToEntity(quoteDTO));
    }

    @Transactional
    private String save(Quote quote) {
        return quoteRepository.save(quote).getCompanyId();
    }

    private QuoteDTO convertToDto(Quote quote) {
        QuoteDTO quoteDTO = modelMapper.map(quote, QuoteDTO.class);
        quoteDTO.setCompanyName(companyService.findById(quote.getCompanyId()).getName());
        return quoteDTO;
    }

    private Quote convertToEntity(QuoteDTO quoteDTO) {
        return modelMapper.map(quoteDTO, Quote.class);
    }
}
