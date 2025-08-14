package com.tts.stock.service;

import java.util.List;

import com.tts.stock.dto.UnitDto;

public interface UnitService {

	List<UnitDto> getUnit(int start, int end);


	UnitDto addUnit(UnitDto dto);


	UnitDto updateUnit(UnitDto dto);


	int deleteUnit(int unitId);


	long getTotalUnits();

}
