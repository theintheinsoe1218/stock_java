package com.tts.stock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tts.stock.dao.ReportDao;
import com.tts.stock.dto.ReportStockBalanceDto;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	ReportDao reportDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<ReportStockBalanceDto> getStockBalanceReport(Date fromDate, Date toDate, int departmentId) {
		// TODO Auto-generated method stub
		return reportDao.getStockBalanceReport(fromDate,toDate,departmentId);
	}
	
	
	
}
