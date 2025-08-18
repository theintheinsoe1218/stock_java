package com.tts.stock.dao;

import java.util.Date;
import java.util.List;

import com.tts.stock.dto.ReportStockBalanceDto;

public interface ReportDao {

	List<ReportStockBalanceDto> getStockBalanceReport(Date fromDate, Date toDate, int departmentId);

}
