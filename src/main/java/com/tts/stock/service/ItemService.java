package com.tts.stock.service;

import java.util.List;

import com.tts.stock.dto.ItemDto;

public interface ItemService {

	List<ItemDto> getItem(int page, int itemPerPage);

	ItemDto addItem(ItemDto dto);

	ItemDto updateItem(ItemDto dto);

	int deleteItem(int itemId);

}
