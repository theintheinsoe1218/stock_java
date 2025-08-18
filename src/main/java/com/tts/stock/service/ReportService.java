package com.tts.stock.service;

import java.util.Date;
import java.util.List;

import com.tts.stock.dto.ReportStockBalanceDto;

public interface ReportService {

	List<ReportStockBalanceDto> getStockBalanceReport(Date fromDate, Date toDate, int departmentId);

}
