package com.tts.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.tts.stock.dto.UnitDto;
import com.tts.stock.service.UnitService;



@RestController
@RequestMapping("/api/v1/")
public class UnitController {
	@Autowired
	UnitService unitService;
	
	@GetMapping("unit")
	public List<UnitDto> getUnit(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		    @RequestParam(name = "end", required = false, defaultValue = "0") int end){
		if (start < 0) start = 0;
	    if (end < 0) end = 0;
		return unitService.getUnit(start,end);
	}
	
	@PostMapping("unit")
	public UnitDto addUnit(@RequestBody UnitDto dto) {
		try {
			return unitService.addUnit(dto);
		}catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException("Add,Unit Error!", e);
		}
	}
	
	@PutMapping("unit/{unitId}")
	public UnitDto updateUnit(@PathVariable("unitId")int unitId,@RequestBody UnitDto dto) {
		try 
		{
		    dto.setUnitId(unitId);
			return unitService.updateUnit(dto);	
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Update,Unit Error!", e);
		}
	}
	
	@DeleteMapping("unit/{unitId}")
	public int deleteUnit(@PathVariable("unitId")int unitId) {
		try
		{
			return unitService.deleteUnit(unitId);
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Delete Error!", e);
		}
	}
}
