package com.zprmts.tcc.ecommerce.dto.perfume;

import com.zprmts.tcc.ecommerce.domain.FotoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfumeResponse {
    private String description;
    private String name;
    private Double price;
    private String categories;
    private FotoEntity foto;
}
