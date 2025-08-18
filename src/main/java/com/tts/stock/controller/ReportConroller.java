package com.tts.stock.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.tts.stock.dto.ReportStockBalanceDto;
import com.tts.stock.service.ReportService;

@RestController
@RequestMapping("/api/v1/")
public class ReportConroller {
	@Autowired
	ReportService reportService;
	
	@GetMapping("report/stockbalance")
	public  List<ReportStockBalanceDto> getStockBalanceReport(@RequestParam(name="fromDate",required=false)@DateTimeFormat(pattern="dd-MM-yyyy")Date fromDate,
			@RequestParam(name="toDate",required=false)@DateTimeFormat(pattern="dd-MM-yyyy")Date toDate,
			@RequestParam(name="departmentId",defaultValue = "0")int departmentId){
		try {
			return reportService.getStockBalanceReport(fromDate,toDate,departmentId);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Error, Stock Balance Report", e);
		}
		
	}
}
