package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	public void saveItem(Item item) {
		log.info("ItemService.saveItem");
		itemRepository.save(item);
	}
	
	public Item fineOne(Long itemId){
		return itemRepository.findOne(itemId);
	}
	
	public List<Item> fineAll(){
		return itemRepository.findAll();
	}

	public void updateItem(Long itemId, int price, String name, int stockQuantity) {
		
		Item findItem = itemRepository.findOne(itemId);
		findItem.setPrice(price);
		findItem.setName(name);
		findItem.setStockQuantity(stockQuantity);
		itemRepository.save(findItem);
	}

}
