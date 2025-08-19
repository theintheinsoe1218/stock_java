package com.tts.stock.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tts.stock.dto.ItemDto;
import com.tts.stock.dto.ReportFormatDto;
import com.tts.stock.dto.ReportStockBalanceDto;
import com.tts.stock.dto.StockFormatDto;
import com.tts.stock.util.ConvertDate;

@Repository
public class ReportDaoImpl implements ReportDao{
	@Autowired
	SessionFactory sessionFactory;

	

	@Override
	public ReportFormatDto getStoreBalanceReport(Date fromDate, Date toDate, int departmentId,int page, int itemPerPage) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String strfromDate = ConvertDate.convertDateToStringYearMonthDay(fromDate);
		String strtoDate = ConvertDate.convertDateToStringYearMonthDay(toDate);
		int offset = (page - 1 ) * itemPerPage;
		String sqlLimit = "";
		if(itemPerPage != 0) {
			sqlLimit += "LIMIT "+ itemPerPage+"\r\n"+"OFFSET "+offset+"\r\n";
		}
		
		
		String sqlData = "SELECT \r\n"
				+ "    it.itemId,\r\n"
				+ "    it.itemName,\r\n"
				+ "     it.unitId,\r\n"
				+ "    un.unitName,\r\n"
				+ "IFNULL((\r\n"
				+ "    \r\n"
				+ "    SELECT SUM(sm.qty)\r\n"
				+ "    FROM stockmovement sm\r\n"
				+ "    WHERE sm.itemId = it.itemId\r\n"
				+ "      AND sm.toDepartmentId = :departmentId\r\n"
				+ "      AND sm.movementType = 'OPENING'\r\n"
				+ "      AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "), \r\n"
				+ "(\r\n"
				+ "   \r\n"
				+ "    SELECT \r\n"
				+ "        IFNULL(SUM(CASE WHEN sm.toDepartmentId = :departmentId AND sm.movementType IN ('IN','ADJUST_IN','OPENING') \r\n"
				+ "                        THEN sm.qty ELSE 0 END),0)\r\n"
				+ "      - IFNULL(SUM(CASE WHEN sm.fromDepartmentId = :departmentId AND sm.movementType IN ('OUT','ADJUST_OUT','WASTE') \r\n"
				+ "                        THEN sm.qty ELSE 0 END),0)\r\n"
				+ "    FROM stockmovement sm\r\n"
				+ "    WHERE sm.itemId = it.itemId\r\n"
				+ "      AND sm.movementDate < :fromDate\r\n"
				+ ")\r\n"
				+ ") AS opening_balance,\r\n"
				+ "    IFNULL((\r\n"
				+ "        SELECT SUM(sm.qty)\r\n"
				+ "        FROM stockmovement sm\r\n"
				+ "        WHERE sm.itemId = it.itemId\r\n"
				+ "          AND sm.toDepartmentId = :departmentId\r\n"
				+ "          AND sm.movementType = 'IN'\r\n"
				+ "          AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "    ),0) AS stock_in,\r\n"
				+ "    IFNULL((\r\n"
				+ "        SELECT SUM(sm.qty)\r\n"
				+ "        FROM stockmovement sm\r\n"
				+ "        WHERE sm.itemId = it.itemId\r\n"
				+ "          AND sm.fromDepartmentId = :departmentId\r\n"
				+ "          AND sm.movementType = 'OUT'\r\n"
				+ "          AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "    ),0) AS stock_out,\r\n"
				+ "    IFNULL((\r\n"
				+ "        SELECT SUM(sm.qty)\r\n"
				+ "        FROM stockmovement sm\r\n"
				+ "        WHERE sm.itemId = it.itemId\r\n"
				+ "          AND sm.toDepartmentId = :departmentId\r\n"
				+ "          AND sm.movementType = 'ADJUST_IN'\r\n"
				+ "          AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "    ),0) AS adjust_in,\r\n"
				+ "    IFNULL((\r\n"
				+ "        SELECT SUM(sm.qty)\r\n"
				+ "        FROM stockmovement sm\r\n"
				+ "        WHERE sm.itemId = it.itemId\r\n"
				+ "          AND sm.fromDepartmentId = :departmentId\r\n"
				+ "          AND sm.movementType = 'ADJUST_OUT'\r\n"
				+ "          AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "    ),0) AS adjust_out,\r\n"
				+ "    IFNULL((\r\n"
				+ "        SELECT SUM(sm.qty)\r\n"
				+ "        FROM stockmovement sm\r\n"
				+ "        WHERE sm.itemId = it.itemId\r\n"
				+ "          AND sm.fromDepartmentId = :departmentId\r\n"
				+ "          AND sm.movementType = 'WASTE'\r\n"
				+ "          AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "    ),0) AS waste,\r\n"
				+ "    (\r\n"
				+ "        (\r\n"
				+ "            IFNULL((\r\n"
				+ "                SELECT SUM(\r\n"
				+ "                    CASE \r\n"
				+ "                        WHEN sm.toDepartmentId = :departmentId AND sm.movementType IN ('OPENING','IN','ADJUST_IN') \r\n"
				+ "                            THEN sm.qty\r\n"
				+ "                        WHEN sm.fromDepartmentId = :departmentId AND sm.movementType IN ('OUT','ADJUST_OUT','WASTE') \r\n"
				+ "                            THEN -sm.qty\r\n"
				+ "                        ELSE 0\r\n"
				+ "                    END\r\n"
				+ "                )\r\n"
				+ "                FROM stockmovement sm\r\n"
				+ "                WHERE sm.itemId = it.itemId\r\n"
				+ "                  AND sm.movementDate <= '2025-08-18'\r\n"
				+ "            ),0)\r\n"
				+ "        )\r\n"
				+ "    ) AS closing_balance"
				+ "\r\n"
				+ "FROM item it\r\n"
				+ "LEFT JOIN unit un ON un.unitId = it.unitId\r\n"
				+ "ORDER BY it.itemName\r\n"
				+ sqlLimit;
		List<ReportStockBalanceDto> dtoList = new ArrayList<ReportStockBalanceDto>();
		
		
		List<Object[]> objList = session.createNativeQuery(sqlData)
										.setParameter("departmentId", departmentId)
										.setParameter("fromDate", strfromDate)
										.setParameter("toDate", strtoDate)
										.getResultList();
					
		for(Object[] obj:objList) {

			int itemId = Integer.parseInt(obj[0].toString());
			String itemName = (String)obj[1];
			int unitId = Integer.parseInt(obj[2].toString());
			String unitName = (String)obj[3];
			int opening = Integer.parseInt(obj[4].toString());
			int stockIn = Integer.parseInt(obj[5].toString());
			int stockOut = Integer.parseInt(obj[6].toString());
			int adjustIn = Integer.parseInt(obj[7].toString());
			int adjustOut = Integer.parseInt(obj[8].toString());
			int waste = Integer.parseInt(obj[9].toString());
			int closing = Integer.parseInt(obj[10].toString());
			ReportStockBalanceDto dto = new ReportStockBalanceDto(opening,stockIn,stockOut,adjustIn,adjustOut,waste,closing);

			dto.setItemDto(new ItemDto(itemId,itemName,unitId,unitName));
			
			dtoList.add(dto);

		}
		

	    String sqlCount = "SELECT COUNT(*) \r\n" + 
                        "FROM item it";
	    long totalCount = ((Number) session.createNativeQuery(sqlCount)
                                            .getSingleResult()).longValue();

	    return new ReportFormatDto(dtoList, totalCount);
		

	}

	@Override
	public ReportFormatDto getOtherBalanceReport(Date fromDate, Date toDate, int departmentId, int page, int itemPerPage) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String strfromDate = ConvertDate.convertDateToStringYearMonthDay(fromDate);
		String strtoDate = ConvertDate.convertDateToStringYearMonthDay(toDate);
		int offset = (page - 1 ) * itemPerPage;
		
		String sqlData = "SELECT \r\n"
				+ "    it.itemId,\r\n"
				+ "    it.itemName,\r\n"
				+ "    it.unitId,\r\n"
				+ "    un.unitName,\r\n"
				+ "IFNULL((\r\n"
				+ "    \r\n"
				+ "    SELECT SUM(sm.qty)\r\n"
				+ "    FROM stockmovement sm\r\n"
				+ "    WHERE sm.itemId = it.itemId\r\n"
				+ "      AND sm.toDepartmentId = :departmentId\r\n"
				+ "      AND sm.movementType = 'OPENING'\r\n"
				+ "      AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "), \r\n"
				+ "(\r\n"
				+ "   \r\n"
				+ "    SELECT \r\n"
				+ "        IFNULL(SUM(CASE WHEN sm.toDepartmentId = :departmentId AND sm.movementType IN ('OUT','ADJUST_IN','OPENING') \r\n"
				+ "                        THEN sm.qty ELSE 0 END),0)\r\n"
				+ "      - IFNULL(SUM(CASE WHEN sm.fromDepartmentId = :departmentId AND sm.movementType IN ('OUT','ADJUST_OUT','WASTE') \r\n"
				+ "                        THEN sm.qty ELSE 0 END),0)\r\n"
				+ "    FROM stockmovement sm\r\n"
				+ "    WHERE sm.itemId = it.itemId\r\n"
				+ "      AND sm.movementDate < :fromDate\r\n"
				+ ")\r\n"
				+ ") AS opening_balance,\r\n"
				+ "    IFNULL((\r\n"
				+ "        SELECT SUM(sm.qty)\r\n"
				+ "        FROM stockmovement sm\r\n"
				+ "        WHERE sm.itemId = it.itemId\r\n"
				+ "          AND sm.toDepartmentId = :departmentId\r\n"
				+ "          AND sm.movementType = 'OUT'\r\n"
				+ "          AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "    ),0) AS stock_in,\r\n"
				+ "    IFNULL((\r\n"
				+ "        SELECT SUM(sm.qty)\r\n"
				+ "        FROM stockmovement sm\r\n"
				+ "        WHERE sm.itemId = it.itemId\r\n"
				+ "          AND sm.fromDepartmentId = :departmentId\r\n"
				+ "          AND sm.movementType = 'OUT'\r\n"
				+ "          AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "    ),0) AS stock_out,\r\n"
				+ "    IFNULL((\r\n"
				+ "        SELECT SUM(sm.qty)\r\n"
				+ "        FROM stockmovement sm\r\n"
				+ "        WHERE sm.itemId = it.itemId\r\n"
				+ "          AND sm.toDepartmentId = :departmentId\r\n"
				+ "          AND sm.movementType = 'ADJUST_IN'\r\n"
				+ "          AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "    ),0) AS adjust_in,\r\n"
				+ "    IFNULL((\r\n"
				+ "        SELECT SUM(sm.qty)\r\n"
				+ "        FROM stockmovement sm\r\n"
				+ "        WHERE sm.itemId = it.itemId\r\n"
				+ "          AND sm.fromDepartmentId = :departmentId\r\n"
				+ "          AND sm.movementType = 'ADJUST_OUT'\r\n"
				+ "          AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "    ),0) AS adjust_out,\r\n"
				+ "    IFNULL((\r\n"
				+ "        SELECT SUM(sm.qty)\r\n"
				+ "        FROM stockmovement sm\r\n"
				+ "        WHERE sm.itemId = it.itemId\r\n"
				+ "          AND sm.fromDepartmentId = :departmentId\r\n"
				+ "          AND sm.movementType = 'WASTE'\r\n"
				+ "          AND sm.movementDate BETWEEN :fromDate AND :toDate\r\n"
				+ "    ),0) AS waste,\r\n"
				+ "    (\r\n"
				+ "        (\r\n"
				+ "            IFNULL((\r\n"
				+ "                SELECT SUM(\r\n"
				+ "                    CASE \r\n"
				+ "                        WHEN sm.toDepartmentId = :departmentId AND sm.movementType IN ('OPENING','OUT','ADJUST_IN') \r\n"
				+ "                            THEN sm.qty\r\n"
				+ "                        WHEN sm.fromDepartmentId = :departmentId AND sm.movementType IN ('OUT','ADJUST_OUT','WASTE') \r\n"
				+ "                            THEN -sm.qty\r\n"
				+ "                        ELSE 0\r\n"
				+ "                    END\r\n"
				+ "                )\r\n"
				+ "                FROM stockmovement sm\r\n"
				+ "                WHERE sm.itemId = it.itemId\r\n"
				+ "                  AND sm.movementDate <= :fromDate\r\n"
				+ "            ),0)\r\n"
				+ "        )\r\n"
				+ "    ) AS closing_balance\r\n"
				+ "\r\n"
				+ "FROM item it\r\n"
				+ "LEFT JOIN unit un ON un.unitId = it.unitId\r\n"
				+ "ORDER BY it.itemName\r\n"
				+ "LIMIT :itemPerPage\r\n"
				+ "OFFSET :offset";
		List<ReportStockBalanceDto> dtoList = new ArrayList<ReportStockBalanceDto>();
		List<Object[]> objList = session.createNativeQuery(sqlData)
										.setParameter("departmentId", departmentId)
										.setParameter("fromDate", strfromDate)
										.setParameter("toDate", strtoDate)
										.setParameter("itemPerPage", itemPerPage)
										.setParameter("offset", offset)
										.getResultList();
								
		for(Object[] obj:objList) {
			int itemId = Integer.parseInt(obj[0].toString());
			String itemName = (String)obj[1];
			int unitId = Integer.parseInt(obj[2].toString());
			String unitName = (String)obj[3];
			int opening = Integer.parseInt(obj[4].toString());
			int stockIn = Integer.parseInt(obj[5].toString());
			int stockOut = Integer.parseInt(obj[6].toString());
			int adjustIn = Integer.parseInt(obj[7].toString());
			int adjustOut = Integer.parseInt(obj[8].toString());
			int waste = Integer.parseInt(obj[9].toString());
			int closing = Integer.parseInt(obj[10].toString());
			ReportStockBalanceDto dto = new ReportStockBalanceDto(opening,stockIn,stockOut,adjustIn,adjustOut,waste,closing);

			dto.setItemDto(new ItemDto(itemId,itemName,unitId,unitName));
			
			dtoList.add(dto);
		}
		

	    String sqlCount = "SELECT COUNT(*) \r\n" + 
                        "FROM item it";
	    long totalCount = ((Number) session.createNativeQuery(sqlCount)
                                            .getSingleResult()).longValue();

	    return new ReportFormatDto(dtoList, totalCount);
	}
}
