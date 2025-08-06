package com.tts.stock.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tts.stock.domain.Item;
import com.tts.stock.util.DateTimeFormatDeserializer;
import com.tts.stock.util.DateTimeFormatSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
	
	public ItemDto(int itemId, String itemName, String itemCode, int reorderLevel, String remark, Date created_at,
			Date updated_at) {
		// TODO Auto-generated constructor stub
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.reorderLevel = reorderLevel;
		this.remark = remark;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	private int itemId;
	private String itemName;
	private String itemCode;
	private UnitDto unitDto;
	private int reorderLevel;
	private String remark;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date created_at;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date updated_at;
}
