package com.tts.stock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tts.stock.dao.ReportDao;
import com.tts.stock.dto.ReportFormatDto;
import com.tts.stock.dto.ReportStockBalanceDto;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	ReportDao reportDao;
	
	@Transactional(readOnly=true)
	@Override
	public ReportFormatDto getStockBalanceReport(Date fromDate, Date toDate, int departmentId,
			String deparmentName, int page, int itemPerPage, int optionId) {
		// TODO Auto-generated method stub
		
		if("Store".equals(deparmentName)) {
			return reportDao.getStoreBalanceReport(fromDate,toDate,departmentId,page,itemPerPage, optionId);
		}else {
			
			return reportDao.getOtherBalanceReport(fromDate,toDate,departmentId,page,itemPerPage);
		}
	}
	
	
	
}
