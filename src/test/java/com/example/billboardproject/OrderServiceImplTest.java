package com.example.billboardproject;

import com.example.billboardproject.model.Order;
import com.example.billboardproject.repository.OrderRepository;
import com.example.billboardproject.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrdersByBillboardId() {
        Long billboardId = 1L;
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        when(orderRepository.findOrdersByBillboard_Id(billboardId)).thenReturn(orders);
        List<Order> result = orderService.getAllOrdersByBillboardId(billboardId);
        verify(orderRepository).findOrdersByBillboard_Id(billboardId);
        assertEquals(orders, result);
    }

    @Test
    void testGetOrderById() {
        Long orderId = 1L;
        Order order = new Order();
        when(orderRepository.getReferenceById(orderId)).thenReturn(order);
        Order result = orderService.getOrderById(orderId);
        verify(orderRepository).getReferenceById(orderId);
        assertEquals(order, result);
    }




    @Test
    void testEditOrder() {
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);
        Order result = orderService.editOrder(order);
        verify(orderRepository).save(order);
        assertEquals(order, result);
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        when(orderRepository.findAll()).thenReturn(orders);
        List<Order> result = orderService.getAllOrders();
        assertEquals(orders, result);
    }

    @Test
    void testGetAllOrdersByUserId() {
        Long userId = 1L;
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        when(orderRepository.findOrdersByUser_Id(userId)).thenReturn(orders);
        List<Order> result = orderService.getAllOrdersByUserId(userId);
        verify(orderRepository).findOrdersByUser_Id(userId);
        assertEquals(orders, result);
    }

    @Test
    void testAddOrder() {
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);
        boolean result = orderService.addOrder(order);
        verify(orderRepository).save(order);
        assertEquals(true, result);
    }
}

