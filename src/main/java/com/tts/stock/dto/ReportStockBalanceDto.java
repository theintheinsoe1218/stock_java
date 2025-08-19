package com.tts.stock.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tts.stock.util.DateFormatDeserializer;
import com.tts.stock.util.DateFormatSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class ReportStockBalanceDto {
	
	
	public ReportStockBalanceDto(int opening, int stockIn, int stockOut, int adjustIn, int adjustOut, int waste,
			int closing) {
		// TODO Auto-generated constructor stub
		this.opening = opening;
		this.stockIn = stockIn;
		this.stockOut = stockOut;
		this.adjustIn = adjustIn;
		this.adjustOut = adjustOut;
		this.closing = closing;
	}

	private int stockMovementId;
	@JsonSerialize(using = DateFormatSerializer.class)
	@JsonDeserialize(using = DateFormatDeserializer.class)
	private Date movementDate;
	private ItemDto itemDto; 
	private int opening;
	private int stockIn;
	private int stockOut;
	private int adjustIn;
	private int adjustOut;
	private int waste;
	private int closing;
	private String remark;
	
}
