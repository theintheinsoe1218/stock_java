package com.tts.stock.dao;

import java.util.List;

import com.tts.stock.domain.Unit;
import com.tts.stock.dto.UnitDto;

public interface UnitDao {

	List<UnitDto> getUnit(int start, int end);

	void addUnit(Unit unit);

	void updateUnit(Unit unit);

	void deleteUnit(int unitId);

	long getTotalUnits();

}
