package com.tts.stock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.tts.stock.domain.Unit;
import com.tts.stock.dao.UnitDao;
import com.tts.stock.dto.UnitDto;
@Service
public class UnitServiceImpl implements UnitService{
	@Autowired
	UnitDao unitDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<UnitDto> getUnit() {
		// TODO Auto-generated method stub
		return unitDao.getUnit();
	}
	
	@Transactional(readOnly=false)
	@Override
	public UnitDto addUnit(UnitDto dto) {
		// TODO Auto-generated method stub
		Unit unit = new Unit(dto);
		unit.setCreated_at(new Date());
		unit.setUpdated_at(new Date());
		unitDao.addUnit(unit);
		dto.setUnitId(unit.getUnitId());
		return dto;
	}

	@Transactional(readOnly=false)
	@Override
	public UnitDto updateUnit(UnitDto dto) {
		// TODO Auto-generated method stub
		Unit unit = new Unit(dto);
		unit.setCreated_at(dto.getCreated_at());
		unit.setUpdated_at(new Date());
		unitDao.updateUnit(unit);
		return dto;
	}

	@Transactional(readOnly=false)
	@Override
	public int deleteUnit(int unitId) {
		// TODO Auto-generated method stub
		unitDao.deleteUnit(unitId);
		return unitId;
	}

	

}
