package com.tts.stock.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tts.stock.domain.Department;
import com.tts.stock.util.DateTimeFormatDeserializer;
import com.tts.stock.util.DateTimeFormatSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
	public DepartmentDto(Department department) {
		// TODO Auto-generated constructor stub
		this.departmentId = department.getDepartmentId();
		this.departmentName = department.getDepartmentName();
		this.created_at = department.getCreated_at();
		this.updated_at = department.getUpdated_at();
	}
	private int departmentId;
	private String departmentName;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date created_at;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date updated_at;
}
