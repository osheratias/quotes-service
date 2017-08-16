package com.poalim.openshift.company;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Created by osher on 20/7/17.
 */

@Data
@NoArgsConstructor
public class Company {

    @Id
    private String id;

    private String name;
    private String description;
}
