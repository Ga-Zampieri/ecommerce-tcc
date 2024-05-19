package com.zprmts.tcc.ecommerce.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zprmts.tcc.ecommerce.domain.Order;
import com.zprmts.tcc.ecommerce.domain.Perfume;
import com.zprmts.tcc.ecommerce.domain.User;
import com.zprmts.tcc.ecommerce.dto.order.OrderRequest;
import com.zprmts.tcc.ecommerce.dto.order.OrderResponse;
import com.zprmts.tcc.ecommerce.dto.order.OrderUpdate;
import com.zprmts.tcc.ecommerce.dto.perfume.PerfumeResponse;
import com.zprmts.tcc.ecommerce.exception.RegraDeNegocioException;
import com.zprmts.tcc.ecommerce.repository.OrderRepository;
import com.zprmts.tcc.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.zprmts.tcc.ecommerce.constants.ErrorMessage.ORDER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final UserServiceImpl usuarioService;
    private final PerfumeServiceImpl perfumeService;


    @Override
    public OrderResponse create(OrderRequest orderRequest) throws RegraDeNegocioException {
        User user =  usuarioService.getUserLogado();
        Order order = new Order();
        Double totalPrice = 0.0;
        List<Long> perfumeIds = orderRequest.getPerfumeList();
        for (Long perfumeId : perfumeIds) {
            Perfume perfume = perfumeService.getById(perfumeId);
            totalPrice += perfume.getPrice();
            order.getPerfumeList().add(perfume);
        }
        order.setTotalPrice(totalPrice);
        order.setUser(user);
        order = save(order);

        return getOrderResponse(order);
    }

    @Override
    public OrderResponse update(Long idOrder, OrderUpdate orderUpdate) throws RegraDeNegocioException {
        Order order = getById(idOrder);
        List<Long> perfumeIds = orderUpdate.getPerfumeList();
        Double totalPrice = 0.0;

        List<Perfume> perfumeListUpdate = new ArrayList<>();
        for (Long perfumeId : perfumeIds) {
            Perfume perfume = perfumeService.getById(perfumeId);
            totalPrice += perfume.getPrice();
            perfumeListUpdate.add(perfume);
        }
        order.setPerfumeList(perfumeListUpdate);
        order.setTotalPrice(totalPrice);
        order = save(order);

        return getOrderResponse(order);
    }

    @Override
    public Order getById(Long orderId) throws RegraDeNegocioException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RegraDeNegocioException(ORDER_NOT_FOUND));
    }

    @Override
    public OrderResponse findById(Long orderId) throws RegraDeNegocioException {
        Order order = getById(orderId);
        return getOrderResponse(order);
    }

    @Override
    public List<PerfumeResponse> getOrderItemsByOrderId(Long id) throws RegraDeNegocioException {
        Order order = getById(id);
        List<Perfume> perfumeList = order.getPerfumeList();
        List<PerfumeResponse> perfumeResponseList = new ArrayList<>();
        for (Perfume perfume : perfumeList) {
            perfumeResponseList.add(objectMapper.convertValue(perfume, PerfumeResponse.class));
        }
        return perfumeResponseList;

    }

    @Override
    public Page<OrderResponse> getUserOrders(Pageable pageable) throws RegraDeNegocioException {
        User user = usuarioService.getUserLogado();
        String email = user.getEmail();
        Page<Order> orders = orderRepository.findByUser_Email(email, pageable);
        List<Order> orderList = orders.getContent();
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order order : orderList) {
            orderResponseList.add(getOrderResponse(order));
        }
        return new PageImpl<>(orderResponseList);
    }

    @Override
    public Page<OrderResponse> list(Pageable pageable) {

        Page<Order> orders = orderRepository.findAll(pageable);
        List<Order> orderList = orders.getContent();
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order order : orderList) {
            orderResponseList.add(getOrderResponse(order));
        }
        return new PageImpl<>(orderResponseList);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public String delete(Long orderId) throws RegraDeNegocioException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RegraDeNegocioException(ORDER_NOT_FOUND));
        orderRepository.delete(order);
        return "Order deleted successfully";
    }

    private OrderResponse getOrderResponse(Order order) {
        List<PerfumeResponse> perfumeResponseList = new ArrayList<>();
        for (Perfume perfume : order.getPerfumeList()) {
            perfumeResponseList.add(objectMapper.convertValue(perfume, PerfumeResponse.class));
        }
        OrderResponse orderResponse = objectMapper.convertValue(order, OrderResponse.class);
        orderResponse.setPerfumeList(perfumeResponseList);

        return orderResponse;
    }
}
