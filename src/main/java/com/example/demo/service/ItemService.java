package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public Item fineOne(Long itemId){
		return itemRepository.findOne(itemId);
	}
	
	public List<Item> fineAll(){
		return itemRepository.findAll();
	}

}
