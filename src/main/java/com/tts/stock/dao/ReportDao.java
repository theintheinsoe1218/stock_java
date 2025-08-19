package com.tts.stock.dao;

import java.util.Date;
import java.util.List;

import com.tts.stock.dto.ReportFormatDto;
import com.tts.stock.dto.ReportStockBalanceDto;

public interface ReportDao {


	ReportFormatDto getStoreBalanceReport(Date fromDate, Date toDate, int departmentId, int page, int itemPerPage);

	ReportFormatDto getOtherBalanceReport(Date fromDate, Date toDate, int departmentId, int page, int itemPerPage);

}
