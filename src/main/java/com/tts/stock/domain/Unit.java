package com.tts.stock.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tts.stock.dto.UnitDto;

@Entity
@Table(name = "unit")
public class Unit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int unitId;
	private String unitName;
	private Date created_at;
	private Date updated_at;
	
	public Unit() {
		super();
	}

	public Unit(UnitDto dto) {
		// TODO Auto-generated constructor stub
		this.unitId = dto.getUnitId();
		this.unitName = dto.getUnitName();
		this.created_at = dto.getCreated_at();
		this.updated_at = dto.getUpdated_at();
	}

	public int getUnitId() {
		return unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
