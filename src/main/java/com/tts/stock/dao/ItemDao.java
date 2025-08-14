package com.tts.stock.dao;

import java.util.List;

import com.tts.stock.domain.Item;
import com.tts.stock.dto.ItemDto;

public interface ItemDao {

	List<ItemDto> getItem(int page, int itemPerPage);

	void addItem(Item item);

	void updateItem(Item item);

	void deleteItem(int itemId);

}
