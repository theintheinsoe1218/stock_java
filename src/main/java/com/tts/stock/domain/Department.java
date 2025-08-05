package com.tts.stock.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tts.stock.dto.DepartmentDto;

@Entity
@Table(name = "department")
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int departmentId;
	private String departmentName;
	private Date created_at;
	private Date updated_at;
	
	public Department() {
		super();
	}

	public Department(DepartmentDto dto) {
		// TODO Auto-generated constructor stub
		this.departmentId = dto.getDepartmentId();
		this.departmentName = dto.getDepartmentName();
		this.created_at = dto.getCreated_at();
		this.updated_at = dto.getUpdated_at();
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
