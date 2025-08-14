package com.tts.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.tts.stock.dto.ItemDto;
import com.tts.stock.service.ItemService;

@RestController
@RequestMapping("/api/v1/")
public class ItemController {
	@Autowired
	ItemService itemService;
	
	@GetMapping("item")
	public List<ItemDto> getItem(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
		    @RequestParam(name = "itemPerPage", required = false, defaultValue = "10") int itemPerPage){
		return itemService.getItem(page,itemPerPage);
	}
	
	@PostMapping("item")
	public ItemDto addItem(@RequestBody ItemDto dto) {
		try {
			return itemService.addItem(dto);
		}catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException("Add,Item Error!", e);
		}
	}
	
	
	@PutMapping("item/{itemId}")
	public ItemDto updateItem(@PathVariable("itemId")int itemId,@RequestBody ItemDto dto) {
		try 
		{
		    dto.setItemId(itemId);
			return itemService.updateItem(dto);	
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Update,Item Error!", e);
		}
	}
	
	@DeleteMapping("item/{itemId}")
	public int deleteItem(@PathVariable("itemId")int itemId) {
		try
		{
			return itemService.deleteItem(itemId);
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Delete Error!", e);
		}
	}
	
	@GetMapping("item/count")
	public List<ItemDto> getTotalItem(){
//		return itemService.getTotalItem();
		return null;
	}
	
}
