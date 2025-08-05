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
import org.springframework.web.bind.annotation.RestController;

import com.tts.stock.dto.DepartmentDto;
import com.tts.stock.dto.UnitDto;
import com.tts.stock.service.DepartmentService;

@RestController
@RequestMapping("/api/v1/")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("department")
	public List<DepartmentDto> getDepartment(){
		return departmentService.getDepartment();
	}
	
	@PostMapping("department")
	public DepartmentDto addDepartment(@RequestBody DepartmentDto dto) {
		try {
			return departmentService.addDepartment(dto);
		}catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException("Add,Department Error!", e);
		}
	}
	
	@PutMapping("department/{departmentId}")
	public DepartmentDto updateDepartment(@PathVariable("departmentId")int departmentId,@RequestBody DepartmentDto dto) {
		try 
		{
		    dto.setDepartmentId(departmentId);
			return departmentService.updateDepartment(dto);	
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Update,Department Error!", e);
		}
	}
	
	
	@DeleteMapping("department/{departmentId}")
	public int deleteDepartment(@PathVariable("departmentId")int departmentId) {
		try
		{
			return departmentService.deleteDepartment(departmentId);
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Delete Error!", e);
		}
	}
	
	
}
