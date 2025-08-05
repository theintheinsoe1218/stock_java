package com.tts.stock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tts.stock.dao.DepartmentDao;
import com.tts.stock.domain.Department;
import com.tts.stock.dto.DepartmentDto;
@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	DepartmentDao departmentDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<DepartmentDto> getDepartment() {
		// TODO Auto-generated method stub
		return departmentDao.getDepartment();
	}

	@Transactional(readOnly=false)
	@Override
	public DepartmentDto addDepartment(DepartmentDto dto) {
		// TODO Auto-generated method stub
		Department department = new Department(dto);
		department.setCreated_at(new Date());
		department.setUpdated_at(new Date());
		departmentDao.addDepartment(department);
		return dto;
	}

	@Transactional(readOnly=false)
	@Override
	public DepartmentDto updateDepartment(DepartmentDto dto) {
		// TODO Auto-generated method stub
		Department department = new Department(dto);
		department.setCreated_at(dto.getCreated_at());
		department.setUpdated_at(new Date());
		departmentDao.updateDepartment(department);
		return dto;
	}

	@Transactional(readOnly=false)
	@Override
	public int deleteDepartment(int departmentId) {
		// TODO Auto-generated method stub
		departmentDao.deleteDepartment(departmentId);
		return departmentId;
	}

}
