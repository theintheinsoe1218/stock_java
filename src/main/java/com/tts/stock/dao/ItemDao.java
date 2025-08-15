package com.tts.stock.dao;

import java.util.List;

import com.tts.stock.domain.Item;
import com.tts.stock.dto.ItemDto;
import com.tts.stock.dto.ItemFormatDto;

public interface ItemDao {

	ItemFormatDto getItem(int page, int itemPerPage);

	void addItem(Item item);

	void updateItem(Item item);

	void deleteItem(int itemId);

	long getTotalItem();

	List<ItemDto> getItemSearch(String search);

}
