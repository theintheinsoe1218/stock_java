package com.tts.stock.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tts.stock.domain.StockMovement;
import com.tts.stock.dto.DepartmentDto;
import com.tts.stock.dto.ItemDto;
import com.tts.stock.dto.ItemFormatDto;
import com.tts.stock.dto.StockFormatDto;
import com.tts.stock.dto.StockMovementDto;
import com.tts.stock.dto.UnitDto;
import com.tts.stock.dto.UserAccountDto;
@Repository
public class StockMovementDaoImpl implements StockMovementDao{
    @Autowired
	SessionFactory sessionFactory;

    @Override
    public StockFormatDto getStock(int page, int itemPerPage, Date fromDate, Date toDate) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
		int offset = (page - 1 ) * itemPerPage;
		
		String sqlData = "SELECT st.stockMovementId, st.itemId,it.itemName,it.unitId,u.unitName,st.userAccountId,ua.profileName,ua.userName,st.movementType,st.fromDepartmentId, fde.departmentName as fromDepartmentName,st.toDepartmentId,tde.departmentName as toDepartmentName,\r\n" + 
                        "st.qty,st.remark,st.movementDate,st.created_at,st.updated_at,st.status\r\n" + 
                        "FROM stockmovement st\r\n" + 
                        "LEFT JOIN item it on it.itemId = st.itemId\r\n" + 
                        "LEFT JOIN unit u on u.unitId = it.unitId\r\n" + 
                        "LEFT JOIN useraccount ua on ua.userAccountId = st.userAccountId\r\n" + 
                        "LEFT JOIN department fde on fde.departmentId = st.fromDepartmentId\r\n" + 
                        "LEFT JOIN department tde on tde.departmentId = st.toDepartmentId\r\n" + 
                        "WHERE st.movementDate BETWEEN :fromDate AND :toDate\r\n" + 
                        "AND st.status = 1\r\n" +
                        "ORDER BY st.movementDate\r\n" + 
                        "LIMIT :itemPerPage\r\n" + 
                        "OFFSET :offset";
		
		String sqlDataAll = "SELECT st.stockMovementId, st.itemId,it.itemName,it.unitId,u.unitName,st.userAccountId,ua.profileName,ua.userName,st.movementType,st.fromDepartmentId, fde.departmentName as fromDepartmentName,st.toDepartmentId,tde.departmentName as toDepartmentName,\r\n" + //
                        "st.qty,st.remark,st.movementDate,st.created_at,st.updated_at,st.status\r\n" + 
                        "FROM stockmovement st\r\n" + 
                        "LEFT JOIN item it on it.itemId = st.itemId\r\n" + 
                        "LEFT JOIN unit u on u.unitId = it.unitId\r\n" + 
                        "LEFT JOIN useraccount ua on ua.userAccountId = st.userAccountId\r\n" + 
                        "LEFT JOIN department fde on fde.departmentId = st.fromDepartmentId\r\n" + 
                        "LEFT JOIN department tde on tde.departmentId = st.toDepartmentId\r\n" + 
                        "WHERE st.movementDate BETWEEN :fromDate AND :toDate\r\n" + 
                        "AND st.status = 1\r\n" +
                        "ORDER BY st.movementDate";
		List<Object[]> objList = null;
		if(itemPerPage == 0) {
			objList = session.createNativeQuery(sqlDataAll)
                            .setParameter("fromDate", fromDate)
					        .setParameter("toDate", toDate)
							.getResultList();
		}else {
			objList = session.createNativeQuery(sqlData)
                    .setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate)
					.setParameter("itemPerPage", itemPerPage)
					.setParameter("offset", offset)
					.getResultList();
		}
		
		
		List<StockMovementDto> dtoList = new ArrayList<StockMovementDto>();
		for(Object[] obj:objList) {
			int stockMovementId = Integer.parseInt(obj[0].toString());
            int itemId = Integer.parseInt(obj[1].toString());
			String itemName = (String)obj[2];
            int unitId = Integer.parseInt(obj[3].toString());
			String unitName = (String)obj[4];
            int userAccountId = Integer.parseInt(obj[5].toString());
			String profileName = (String)obj[6];
			String userName = (String)obj[7];
			String movementType = (String)obj[8];
            int fromDepartmentId = 0;
            if(obj[9] != null){

                fromDepartmentId = Integer.parseInt(obj[9].toString());
            }
			String fromDepartmentName = (String)obj[10];
            int toDepartmentId = Integer.parseInt(obj[11].toString());
			String toDepartmentName = (String)obj[12];
			int qty = Integer.parseInt(obj[13].toString());
			String remark = (String)obj[14];
            Date movementDate = (Date)obj[15];
			Date created_at = (Date)obj[16];
			Date updated_at = (Date)obj[17];
			Boolean status = (Boolean)obj[18];
			StockMovementDto dto = new StockMovementDto(stockMovementId,movementType,qty,remark,movementDate,created_at,updated_at);
			dto.setStatus(status);
			dto.setItemDto(new ItemDto(itemId,itemName,unitId,unitName));
            dto.setUserAccountDto(new UserAccountDto(userAccountId,profileName,userName));
            dto.setFromDepartmentDto(new DepartmentDto(fromDepartmentId,fromDepartmentName));
            dto.setToDepartmentDto(new DepartmentDto(toDepartmentId,toDepartmentName));
			dtoList.add(dto);
		}
		// Query for total count
	    String sqlCount = "SELECT COUNT(*) \r\n" + 
                        "FROM stockmovement st\r\n" + 
                        "WHERE st.movementDate BETWEEN :fromDate AND :toDate";
	    long totalCount = ((Number) session.createNativeQuery(sqlCount)
                                            .setParameter("fromDate", fromDate)
                                            .setParameter("toDate", toDate)
                                            .getSingleResult()).longValue();

	    return new StockFormatDto(dtoList, totalCount);
    }

    @Override
    public void addStock(StockMovement st) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
		session.save(st);
    }

	@Override
	public void updateStock(StockMovement st) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(st);
	}

	@Override
	public void deleteStock(int stockMovementId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("UPDATE stockmovement s SET s.status=0 WHERE s.`status`=1 AND s.stockMovementId=:stockMovementId ")
	       .setParameter("stockMovementId", stockMovementId).executeUpdate();
	}
    
}
