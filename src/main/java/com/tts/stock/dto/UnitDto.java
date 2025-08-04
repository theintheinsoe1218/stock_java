package com.tts.stock.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tts.stock.domain.Unit;
import com.tts.stock.util.DateTimeFormatDeserializer;
import com.tts.stock.util.DateTimeFormatSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class UnitDto {
	public UnitDto(Unit unit) {
		// TODO Auto-generated constructor stub
		this.unitId = unit.getUnitId();
		this.unitName = unit.getUnitName();
		this.created_at = unit.getCreated_at();
		this.updated_at = unit.getUpdated_at();
	}
	private int unitId;
	private String unitName;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date created_at;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date updated_at;
}
