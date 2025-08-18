package com.tts.stock.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tts.stock.dto.ItemDto;
import com.tts.stock.dto.ReportStockBalanceDto;
import com.tts.stock.util.ConvertDate;

@Repository
public class ReportDaoImpl implements ReportDao{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<ReportStockBalanceDto> getStockBalanceReport(Date fromDate, Date toDate, int departmentId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String strfromDate = ConvertDate.convertDateToStringYearMonthDay(fromDate);
		String strtoDate = ConvertDate.convertDateToStringYearMonthDay(toDate);
		String sqlCu = "";
		
		int opening = 0;
		double re = 0;
		double receiptAmount = 0;

		String sqlOpening = "SELECT st.stockMovementId,st.movementDate,st.itemId, it.itemName, it.unitId, un.unitName, st.toDepartmentId,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'OPENING' \r\n"
				+ "          AND st.toDepartmentId = :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS opening\r\n"
				+ "FROM stockmovement st\r\n"
				+ "LEFT JOIN item it ON it.itemId = st.itemId\r\n"
				+ "LEFT JOIN unit un ON un.unitId = it.unitId\r\n"
				+ "WHERE st.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "GROUP BY st.movementDate, it.itemId\r\n"
				+ "ORDER BY st.movementDate";
		List<Object[]> openingList = session.createNativeQuery(sqlOpening)
				.setParameter("departmentId", departmentId)
				.setParameter("fromDate", strfromDate)
				.setParameter("toDate", strtoDate)
				.getResultList();

		for(Object[] obj:openingList) {
		Date movementDate = (Date)obj[1];
		int open = 0;

		
		
		if(obj[7]!=null)
		open = Integer.parseInt(obj[7].toString());

		
		opening = open;
		
		}
		
		
		
		String sqlOne = "SELECT st.stockMovementId,st.movementDate,st.itemId, it.itemName, it.unitId, un.unitName, st.toDepartmentId,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'IN' \r\n"
				+ "          AND st.toDepartmentId = :departmentId\r\n"
				+ "          OR  st.fromDepartmentId <> :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS stockIN,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'out'\r\n"
				+ "          AND st.fromDepartmentId = :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS stockOut,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'ADJUST_IN'\r\n"
				+ "          AND st.toDepartmentId = :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS adjustIn,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'ADJUST_OUT'\r\n"
				+ "          AND st.toDepartmentId = :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS adjustOut,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'WASTE'\r\n"
				+ "          AND st.toDepartmentId = :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS waste, st.remark\r\n"
				+ "FROM stockmovement st\r\n"
				+ "LEFT JOIN item it ON it.itemId = st.itemId\r\n"
				+ "LEFT JOIN unit un ON un.unitId = it.unitId\r\n"
				+ "WHERE st.movementDate  < :fromDate\r\n"
				+ "GROUP BY st.movementDate, it.itemId\r\n"
				+ "ORDER BY st.movementDate";
		
		
		List<Object[]> opList = session.createNativeQuery(sqlOne)
										.setParameter("departmentId", departmentId)
										.setParameter("fromDate", strfromDate)
										.getResultList();
		if(opening == 0) {
			for(Object[] obj:opList) {
				Date movementDate = (Date)obj[1];
				int stockIn = 0;
				int stockOut = 0;
				int adjustIn = 0;
				int adjustOut = 0;
				int waste = 0;
				 
				 
				if(obj[7]!=null)
					stockIn = Integer.parseInt(obj[7].toString());
				if(obj[8]!=null)
					stockOut = Integer.parseInt(obj[8].toString());
				if(obj[9]!=null)
					adjustIn = Integer.parseInt(obj[9].toString());
				if(obj[10]!=null)
					adjustOut = Integer.parseInt(obj[10].toString());
				if(obj[11]!=null)
					waste = Integer.parseInt(obj[11].toString());
			
				opening += stockIn-stockOut+adjustIn-adjustOut-waste;
				
			}
		}
		
		
		String sqlTwo = "SELECT st.stockMovementId,st.movementDate,st.itemId, it.itemName, it.unitId, un.unitName, st.toDepartmentId,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'IN' \r\n"
				+ "          AND st.toDepartmentId = :departmentId\r\n"
				+ "          OR  st.fromDepartmentId <> :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS stockIN,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'out'\r\n"
				+ "          AND st.fromDepartmentId = :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS stockOut,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'ADJUST_IN'\r\n"
				+ "          AND st.toDepartmentId = :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS adjustIn,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'ADJUST_OUT'\r\n"
				+ "          AND st.toDepartmentId = :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS adjustOut,\r\n"
				+ "SUM(\r\n"
				+ "      CASE \r\n"
				+ "          WHEN st.movementType = 'WASTE'\r\n"
				+ "          AND st.toDepartmentId = :departmentId\r\n"
				+ "          THEN st.qty\r\n"
				+ "          ELSE 0\r\n"
				+ "        END\r\n"
				+ "    ) AS waste, st.remark\r\n"
				+ "FROM stockmovement st\r\n"
				+ "LEFT JOIN item it ON it.itemId = st.itemId\r\n"
				+ "LEFT JOIN unit un ON un.unitId = it.unitId\r\n"
				+ "WHERE st.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "GROUP BY st.movementDate, it.itemId\r\n"
				+ "ORDER BY st.movementDate";
		List<ReportStockBalanceDto> dtoList = new ArrayList<ReportStockBalanceDto>();
		List<Object[]> objList = session.createNativeQuery(sqlTwo)
										.setParameter("departmentId", departmentId)
										.setParameter("fromDate", strfromDate)
										.setParameter("toDate", strtoDate)
										.getResultList();
								
		for(Object[] obj:objList) {
			int stockMovementId = Integer.parseInt(obj[0].toString());
			Date movementDate = (Date)obj[1];
			int itemId = Integer.parseInt(obj[2].toString());
			String itemName = (String)obj[3];
			int unitId = Integer.parseInt(obj[4].toString());
			String unitName = (String)obj[5];
			int stockIn = 0;
			int stockOut = 0;
			int adjustIn = 0;
			int adjustOut = 0;
			int waste = 0;
			 
			 
			if(obj[7]!=null)
				stockIn = Integer.parseInt(obj[7].toString());
			if(obj[8]!=null)
				stockOut = Integer.parseInt(obj[8].toString());
			if(obj[9]!=null)
				adjustIn = Integer.parseInt(obj[9].toString());
			if(obj[10]!=null)
				adjustOut = Integer.parseInt(obj[10].toString());
			if(obj[11]!=null)
				waste = Integer.parseInt(obj[11].toString());
			String remark = (String)obj[12];
		

			ReportStockBalanceDto dto = new ReportStockBalanceDto(stockMovementId,opening,stockIn,stockOut,adjustIn,adjustOut,waste,remark);
			dto.setMovementDate(movementDate);
			dto.setItemDto(new ItemDto(itemId,itemName,unitId,unitName));
			
			dto.setClosing(dto.getOpening());
			opening = dto.getClosing();
			dtoList.add(dto);
		}
		

		
	
		return dtoList;
	}
}
