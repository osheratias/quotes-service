package com.poalim.openshift.quote;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by osher on 16/8/17.
 */
@Data
public class QuoteDTO {

    private String companyId;
    private BigDecimal lastTradeValue;
    private String companyName;
}
