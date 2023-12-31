package com.bal.inventoryservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bal.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
	
	private final InventoryService inventoryService;
	
	@GetMapping("/{sku-code}")	
	@ResponseStatus(HttpStatus.OK)
	public boolean inStock(@PathVariable("sku-code") String skuCode) {
		return inventoryService.isInStock(skuCode);
	}

}
