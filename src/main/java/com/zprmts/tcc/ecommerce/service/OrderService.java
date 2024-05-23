package com.zprmts.tcc.ecommerce.service;

import com.zprmts.tcc.ecommerce.domain.Order;
import com.zprmts.tcc.ecommerce.dto.order.OrderRequest;
import com.zprmts.tcc.ecommerce.dto.order.OrderResponse;
import com.zprmts.tcc.ecommerce.dto.order.OrderUpdate;
import com.zprmts.tcc.ecommerce.dto.perfume.PerfumeResponse;
import com.zprmts.tcc.ecommerce.exception.RegraDeNegocioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest orderRequest) throws RegraDeNegocioException;

    OrderResponse update(Long idOrder, OrderUpdate orderUpdate) throws RegraDeNegocioException;

    Order getById(Long orderId) throws RegraDeNegocioException;

    OrderResponse findById(Long orderId) throws RegraDeNegocioException;

    List<PerfumeResponse> getOrderItemsByOrderId(Long orderId) throws RegraDeNegocioException;

    Page<OrderResponse> getUserOrders(Pageable pageable) throws RegraDeNegocioException;

    Page<OrderResponse> list(Pageable pageable);

    Order save(Order order);

    String delete(Long orderId) throws RegraDeNegocioException;

    OrderResponse finalizarPedido() throws RegraDeNegocioException;

    OrderResponse adicionarPerfume(Long idPerfume) throws RegraDeNegocioException;
}
