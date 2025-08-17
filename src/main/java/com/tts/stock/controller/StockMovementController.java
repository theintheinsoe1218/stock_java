package com.tts.stock.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.tts.stock.dto.StockFormatDto;
import com.tts.stock.dto.StockMovementDto;
import com.tts.stock.service.StockMovementService;

@RestController
@RequestMapping("/api/v1/")
public class StockMovementController {
    @Autowired
    StockMovementService stMoveService;

    @GetMapping("stock")
	public StockFormatDto getStock(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
		    @RequestParam(name = "itemPerPage", required = false, defaultValue = "0") int itemPerPage,
            @RequestParam(name="fromDate",required=false)@DateTimeFormat(pattern="dd-MM-yyyy")Date fromDate,
			@RequestParam(name="toDate",required=false)@DateTimeFormat(pattern="dd-MM-yyyy")Date toDate){
		return stMoveService.getStock(page,itemPerPage,fromDate,toDate);
	}

	@PostMapping("stock")
	public StockMovementDto addStock(@RequestBody StockMovementDto dto) {
		try {
			return stMoveService.addStock(dto);
		}catch (Exception e) {
				// TODO: handle exception
			throw new RuntimeException("Add,Stock Error!", e);
		}
	}

}
