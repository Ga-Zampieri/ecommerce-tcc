package com.zprmts.tcc.ecommerce.dto.order;

import com.zprmts.tcc.ecommerce.dto.perfume.PerfumeResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private Double totalPrice;
    private LocalDate date;
    private List<PerfumeResponse> perfumeList;
}
