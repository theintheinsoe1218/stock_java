package com.tts.stock.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tts.stock.dao.StockMovementDao;
import com.tts.stock.domain.StockMovement;
import com.tts.stock.dto.StockFormatDto;
import com.tts.stock.dto.StockMovementDto;
import com.tts.stock.util.User;
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

    @Transactional(readOnly=false)
    @Override
    public StockMovementDto addStock(StockMovementDto dto) {
        // TODO Auto-generated method stub
        StockMovement st = new StockMovement();
        st.setItemId(dto.getItemDto().getItemId());
        st.setUserAccountId(User.getUserId());
        st.setMovementType(dto.getMovementType());
        if(dto.getFromDepartmentDto() != null){
            st.setFromDepartmentId(dto.getFromDepartmentDto().getDepartmentId());
        }
        st.setToDepartmentId(dto.getToDepartmentDto().getDepartmentId());
        st.setQty(dto.getQty());
        st.setRemark(dto.getRemark());
        st.setMovementDate(dto.getMovementDate());
        st.setStatus(true);
        st.setCreated_at(new Date());
        st.setUpdated_at(new Date());
        stMoveDao.addStock(st);
        return dto;
    }
    
}
