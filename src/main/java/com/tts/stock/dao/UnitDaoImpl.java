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
	public List<UnitDto> getUnit(int start, int end) {
	    Session session = sessionFactory.getCurrentSession();

	    if (start == 0 && end == 0) {
	        List<Unit> unitList = session.createQuery(
	                "SELECT u FROM Unit u ORDER BY u.unitName", Unit.class)
	            .getResultList();

	        List<UnitDto> unitDtoList = new ArrayList<>();
	        for (Unit unit : unitList) {
	            unitDtoList.add(new UnitDto(unit));
	        }
	        return unitDtoList;
	    }


	    int maxResults = end - start;
	    if (maxResults < 0) maxResults = 0;

	    List<Unit> unitList = session.createQuery(
	            "SELECT u FROM Unit u ORDER BY u.unitName", Unit.class)
	        .setFirstResult(start)
	        .setMaxResults(maxResults)
	        .getResultList();

	    List<UnitDto> unitDtoList = new ArrayList<>();
	    for (Unit unit : unitList) {
	        unitDtoList.add(new UnitDto(unit));
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


	@Override
	public long getTotalUnits() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
	    return session.createQuery("SELECT COUNT(u) FROM Unit u", Long.class).getSingleResult();
	}

}
