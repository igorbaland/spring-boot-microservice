package com.bal.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bal.orderservice.dto.OrderLineItemsDto;
import com.bal.orderservice.dto.OrderRequest;
import com.bal.orderservice.model.Order;
import com.bal.orderservice.model.OrderLineItems;
import com.bal.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		
		  List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
			.stream()
			.map(this::mapToDto)
			.toList();
		
		order.setOrderLineItemsList(orderLineItems);
		orderRepository.save(order);
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto oderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(oderLineItemsDto.getPrice());
		orderLineItems.setQuantity(oderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItems.getSkuCode());
		return orderLineItems;
	}
}
