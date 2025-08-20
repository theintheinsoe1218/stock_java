package com.tts.stock.dao;

import java.util.Date;

import com.tts.stock.domain.StockMovement;
import com.tts.stock.dto.StockFormatDto;

public interface StockMovementDao {

    StockFormatDto getStock(int page, int itemPerPage, Date fromDate, Date toDate);

    void addStock(StockMovement st);

	void updateStock(StockMovement st);

	void deleteStock(int stockMovementId);
    
}
