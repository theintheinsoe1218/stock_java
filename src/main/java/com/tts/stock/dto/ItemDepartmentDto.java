package com.tts.stock.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class ItemDepartmentDto {
    private int itemDepartmentId;
	private int itemId;
	private int departmentId;
    
}
