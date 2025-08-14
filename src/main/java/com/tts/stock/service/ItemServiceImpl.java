package com.tts.stock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tts.stock.dao.ItemDao;
import com.tts.stock.domain.Item;
import com.tts.stock.dto.ItemDto;
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	ItemDao itemDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<ItemDto> getItem(int page, int itemPerPage) {
		// TODO Auto-generated method stub
		return itemDao.getItem(page, itemPerPage);
	}

	@Transactional(readOnly=false)
	@Override
	public ItemDto addItem(ItemDto dto) {
		// TODO Auto-generated method stub
		Item item = new Item(dto);
		item.setCreated_at(new Date());
		item.setUpdated_at(new Date());
		itemDao.addItem(item);
		return dto;
	}

	@Transactional(readOnly=false)
	@Override
	public ItemDto updateItem(ItemDto dto) {
		// TODO Auto-generated method stub
		Item item = new Item(dto);
		item.setCreated_at(dto.getCreated_at());
		item.setUpdated_at(new Date());
		itemDao.updateItem(item);
		return dto;
	}

	@Transactional(readOnly=false)
	@Override
	public int deleteItem(int itemId) {
		// TODO Auto-generated method stub
		itemDao.deleteItem(itemId);
		return itemId;
	}

}
