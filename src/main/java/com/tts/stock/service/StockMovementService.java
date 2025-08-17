package com.tts.stock.service;

import java.util.Date;

import com.tts.stock.dto.StockFormatDto;
import com.tts.stock.dto.StockMovementDto;

public interface StockMovementService {

    StockFormatDto getStock(int page, int itemPerPage, Date fromDate, Date toDate);

    StockMovementDto addStock(StockMovementDto dto);
    
}
