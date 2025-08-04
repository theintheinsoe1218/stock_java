package com.tts.stock.service;

import java.util.List;

import com.tts.stock.dto.UnitDto;

public interface UnitService {

	List<UnitDto> getUnit();


	UnitDto addUnit(UnitDto dto);


	UnitDto updateUnit(UnitDto dto);


	int deleteUnit(int unitId);

}
