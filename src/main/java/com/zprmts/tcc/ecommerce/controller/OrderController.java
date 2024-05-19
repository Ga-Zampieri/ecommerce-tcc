package com.zprmts.tcc.ecommerce.controller;

import com.zprmts.tcc.ecommerce.controller.interfaces.OrderControllerInterface;
import com.zprmts.tcc.ecommerce.dto.order.OrderRequest;
import com.zprmts.tcc.ecommerce.dto.order.OrderResponse;
import com.zprmts.tcc.ecommerce.dto.order.OrderUpdate;
import com.zprmts.tcc.ecommerce.dto.perfume.PerfumeResponse;
import com.zprmts.tcc.ecommerce.exception.RegraDeNegocioException;
import com.zprmts.tcc.ecommerce.service.Impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController implements OrderControllerInterface {
    
    private final OrderServiceImpl orderService;

    @Override
    @PostMapping()
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest orderRequest) throws RegraDeNegocioException {
        return new ResponseEntity<>(orderService.create(orderRequest), HttpStatus.OK);
    }

    @Override
    @PutMapping("/{idOrder}")
    public ResponseEntity<OrderResponse> update(@Valid @PathVariable("idOrder") Long idOrder,
                                                  @Valid @RequestBody OrderUpdate orderUpdate) throws RegraDeNegocioException {
        return new ResponseEntity<>(orderService.update(idOrder, orderUpdate), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{idOrder}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long idOrder) throws RegraDeNegocioException {
        return new ResponseEntity<>(orderService.findById(idOrder), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}/itens")
    public ResponseEntity<List<PerfumeResponse>> getOrderItemsByOrderId(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(orderService.getOrderItemsByOrderId(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/logged-user")
    public ResponseEntity<Page<OrderResponse>> getUserOrders(@PageableDefault(size = 10) Pageable pageable) throws RegraDeNegocioException {
        return new ResponseEntity<>(orderService.getUserOrders(pageable), HttpStatus.OK);
    }
    
    @Override
    @GetMapping
    public ResponseEntity<Page<OrderResponse>> list(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) throws RegraDeNegocioException {
        return new ResponseEntity<>(orderService.list(pageable), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{idOrder}")
    public ResponseEntity<Void> delete(@PathVariable Long idOrder) throws RegraDeNegocioException {
        orderService.delete(idOrder);
        return ResponseEntity.noContent().build();
    }
}
