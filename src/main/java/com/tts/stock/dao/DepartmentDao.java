package com.tts.stock.dao;

import java.util.List;

import com.tts.stock.domain.Department;
import com.tts.stock.dto.DepartmentDto;

public interface DepartmentDao {

	List<DepartmentDto> getDepartment();

	void addDepartment(Department department);

	void updateDepartment(Department department);

	void deleteDepartment(int departmentId);

}
