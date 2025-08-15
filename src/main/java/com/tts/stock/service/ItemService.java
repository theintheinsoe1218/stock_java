package com.tts.stock.service;

import java.util.List;

import com.tts.stock.dto.ItemDto;
import com.tts.stock.dto.ItemFormatDto;

public interface ItemService {

	ItemFormatDto getItem(int page, int itemPerPage);

	ItemDto addItem(ItemDto dto);

	ItemDto updateItem(ItemDto dto);

	int deleteItem(int itemId);

	long getTotalItem();



	List<ItemDto> getItemSearch(String search);



}
