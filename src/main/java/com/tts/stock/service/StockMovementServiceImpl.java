package com.tts.stock.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tts.stock.dao.StockMovementDao;
import com.tts.stock.dto.StockFormatDto;
@Service
public class StockMovementServiceImpl implements StockMovementService{
    @Autowired
    StockMovementDao stMoveDao;

    @Transactional(readOnly=true)
    @Override
    public StockFormatDto getStock(int page, int itemPerPage, Date fromDate, Date toDate) {
        // TODO Auto-generated method stub
       return stMoveDao.getStock(page,itemPerPage,fromDate,toDate);
    }
    
}
