package com.tts.stock.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tts.stock.util.DateTimeFormatDeserializer;
import com.tts.stock.util.DateTimeFormatSerializer;
import com.tts.stock.util.DateFormatDeserializer;
import com.tts.stock.util.DateFormatSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class StockMovementDto {
    public StockMovementDto(int stockMovementId, String movementType, int qty, String remark, Date movementDate,
            Date created_at, Date updated_at) {
        //TODO Auto-generated constructor stub
        this.stockMovementId = stockMovementId;
        this.movementType = movementType;
        this.qty = qty;
        this.remark = remark;
        this.movementDate = movementDate;
        this.created_at = created_at;
        this.updated_at = updated_at;

    }
    private int stockMovementId;
	private ItemDto itemDto;
	private UserAccountDto userAccountDto;
	private String movementType;
    private DepartmentDto fromDepartmentDto;
    private DepartmentDto toDepartmentDto;
	private int qty;
	private String remark;
    @JsonSerialize(using = DateFormatSerializer.class)
	@JsonDeserialize(using = DateFormatDeserializer.class)
    private Date movementDate;
    private boolean status;
    @JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
    private Date created_at;
    @JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date updated_at;

}

