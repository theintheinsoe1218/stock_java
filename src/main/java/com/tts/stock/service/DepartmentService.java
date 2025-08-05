package com.tts.stock.service;

import java.util.List;

import com.tts.stock.dto.DepartmentDto;

public interface DepartmentService {

	List<DepartmentDto> getDepartment();

	DepartmentDto addDepartment(DepartmentDto dto);

	DepartmentDto updateDepartment(DepartmentDto dto);

	int deleteDepartment(int departmentId);

}
