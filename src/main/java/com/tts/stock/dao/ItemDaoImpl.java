package com.tts.stock.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.tts.stock.domain.Item;
import com.tts.stock.dto.ItemDto;
import com.tts.stock.dto.UnitDto;

@Repository
public class ItemDaoImpl implements ItemDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<ItemDto> getItem(int page, int itemPerPage) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		int offset = (page - 1 ) * itemPerPage;
		
		String sqlData = "SELECT i.itemId,i.itemName,i.itemCode, i.unitId, u.unitName, i.reorderLevel,\r\n"
				+ "i.remark,i.created_at,i.updated_at\r\n"
				+ "FROM item i\r\n"
				+ "LEFT JOIN unit u ON u.unitId = i.unitId\r\n"
				+ "ORDER BY i.itemName\r\n"
				+ "LIMIT :itemPerPage\r\n"
				+ "OFFSET :offset\r\n";
		List<Object[]> objList = session.createNativeQuery(sqlData)
										.setParameter("itemPerPage", itemPerPage)
										.setParameter("offset", offset)
										.getResultList();
		List<ItemDto> dtoList = new ArrayList<ItemDto>();
		for(Object[] obj:objList) {
			
			
			int itemId = Integer.parseInt(obj[0].toString());
			String itemName = (String)obj[1];
			String itemCode = (String)obj[2];
			int unitId = Integer.parseInt(obj[3].toString());
			String unitName = (String)obj[4];
			int reorderLevel = Integer.parseInt(obj[5].toString());
			String remark = (String)obj[6];
			Date created_at = (Date)obj[7];
			Date updated_at = (Date)obj[8];
			
			ItemDto dto = new ItemDto(itemId,itemName,itemCode,reorderLevel,remark,created_at,updated_at);
			dto.setUnitDto(new UnitDto(unitId,unitName));
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(item);
	}

	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(item);
	}

	@Override
	public void deleteItem(int itemId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("Delete FROM item WHERE itemId=:itemId")
		.setParameter("itemId", itemId).executeUpdate();
	}

}
