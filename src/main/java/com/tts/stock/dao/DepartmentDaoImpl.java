package com.tts.stock.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tts.stock.domain.Department;
import com.tts.stock.dto.DepartmentDto;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<DepartmentDto> getDepartment() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Department> departmentList=session.createQuery("SELECT d FROM Department d ORDER BY d.departmentName").getResultList();
		List<DepartmentDto> departmentDtoList=new ArrayList<>();
		for(Department department:departmentList) {
			DepartmentDto dto = new DepartmentDto(department);
			departmentDtoList.add(dto);
		}
		return departmentDtoList;
	}

	@Override
	public void addDepartment(Department department) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(department);
	}

	@Override
	public void updateDepartment(Department department) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(department);
	}

	@Override
	public void deleteDepartment(int departmentId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("Delete FROM department WHERE departmentId=:departmentId")
		.setParameter("departmentId", departmentId).executeUpdate();
	}

}
