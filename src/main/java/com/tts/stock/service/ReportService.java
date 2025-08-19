package com.tts.stock.service;

import java.util.Date;
import java.util.List;

import com.tts.stock.dto.ReportFormatDto;
import com.tts.stock.dto.ReportStockBalanceDto;

public interface ReportService {

	ReportFormatDto getStockBalanceReport(Date fromDate, Date toDate, int departmentId, String departmentName, int page, int itemPerPage);

}
