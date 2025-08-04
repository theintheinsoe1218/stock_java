package com.tts.stock.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tts.stock.domain.Unit;
import com.tts.stock.dto.UnitDto;

@Repository
public class UnitDaoImpl implements UnitDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<UnitDto> getUnit() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Unit> unitList=session.createQuery("SELECT u FROM Unit u ORDER BY u.unitName").getResultList();
		List<UnitDto> unitDtoList=new ArrayList<>();
		for(Unit unit:unitList) {
			UnitDto dto = new UnitDto(unit);
			unitDtoList.add(dto);
		}
		return unitDtoList;
	}

	@Override
	public void addUnit(Unit unit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(unit);
	}

	@Override
	public void updateUnit(Unit unit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(unit);
	}

	@Override
	public void deleteUnit(int unitId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("Delete FROM unit WHERE unitId=:unitId")
		.setParameter("unitId", unitId).executeUpdate();
	}

}
