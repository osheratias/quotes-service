package com.poalim.openshift.quote;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

/**
 * Created by osher on 19/7/17.
 */

@Data
public class Quote {

    @Id
    private String companyId;

    private BigDecimal lastTradeValue;
}
