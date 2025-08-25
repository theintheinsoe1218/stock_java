package com.tts.stock.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tts.stock.dto.ReportFormatDto;
import com.tts.stock.dto.ReportStockBalanceDto;
import com.tts.stock.service.ReportService;



@RestController
@RequestMapping("/api/v1/")
public class ExcelController {
	@Autowired 
	ReportService reportService;
	
	
	@GetMapping("excel/stockbalance")
	@ResponseBody
	public HttpEntity<byte[]> excelStockBalanceReport(@RequestParam(name="fromDate",required=false)@DateTimeFormat(pattern="dd-MM-yyyy")Date fromDate,
			@RequestParam(name="toDate",required=false)@DateTimeFormat(pattern="dd-MM-yyyy")Date toDate,
			@RequestParam(name="departmentId",defaultValue = "0")int departmentId,
			@RequestParam(name="departmentName",defaultValue = "")String departmentName,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
		    @RequestParam(name = "itemPerPage", required = false, defaultValue = "0") int itemPerPage,
            @RequestParam(name="optionId",defaultValue = "0")int optionId){
		if(fromDate != null && toDate != null) {
			if(fromDate.getTime()>toDate.getTime()) {
				Date temp = fromDate;
				fromDate = toDate;
				toDate = temp;
			}
		}
		ReportFormatDto dtoList = reportService.getStockBalanceReport(fromDate,toDate,departmentId,
				departmentName,page,itemPerPage,optionId);
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		XSSFSheet sheet = workbook.createSheet("Daily Stock Balance");
        sheet.setColumnWidth(0,1500);
        sheet.setColumnWidth(1,4500);
        sheet.setColumnWidth(2,3500);
        sheet.setColumnWidth(3,3000);
        sheet.setColumnWidth(4,3500);
        sheet.setColumnWidth(5,4500);
        sheet.setColumnWidth(6,4500);
        sheet.setColumnWidth(7,4500);
        sheet.setColumnWidth(8,4500);
        sheet.setColumnWidth(9,4000);

        CellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);
        cs.setVerticalAlignment(CellStyle.ALIGN_LEFT);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        
       
        
        CellStyle csleft = workbook.createCellStyle();
        csleft.setWrapText(true);
        csleft.setVerticalAlignment(CellStyle.ALIGN_LEFT);
        csleft.setAlignment(CellStyle.ALIGN_LEFT);//main
        csleft.setBorderBottom(CellStyle.BORDER_THIN);
        csleft.setBorderLeft(CellStyle.BORDER_THIN);
        csleft.setBorderRight(CellStyle.BORDER_THIN);
        csleft.setBorderTop(CellStyle.BORDER_THIN);
        
        CellStyle csright = workbook.createCellStyle();
        csright.setWrapText(true);
        csright.setVerticalAlignment(CellStyle.ALIGN_RIGHT);
//        csright.setVerticalAlignment(CellStyle.ALIGN_CENTER_SELECTION);
        csright.setAlignment(CellStyle.ALIGN_RIGHT);//main
        csright.setBorderBottom(CellStyle.BORDER_THIN);
        csright.setBorderLeft(CellStyle.BORDER_THIN);
        csright.setBorderRight(CellStyle.BORDER_THIN);
        csright.setBorderTop(CellStyle.BORDER_THIN);

        CellStyle csrightPadded = workbook.createCellStyle();
        csrightPadded.cloneStyleFrom(csright);

        // Left padding
        csrightPadded.setIndention((short) 1);

        // Text ကို အပေါ်ကပ်ပေးမယ် => အောက်မှာ padding effect ဖြစ်မယ်
//        csrightPadded.setVerticalAlignment(VerticalAlignment.TOP);
        
        
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.BROWN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setVerticalAlignment(CellStyle.ALIGN_LEFT);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        
        Row row = sheet.createRow(0);
        
        Cell cell = row.createCell(0);
        cell.setCellValue("No.");
        cell.setCellStyle(style);
        
        cell = row.createCell(1);
        cell.setCellValue("Raw Material");
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("Net Weight");
        cell.setCellStyle(style);
        
        cell = row.createCell(3);
        cell.setCellValue("OB");
        cell.setCellStyle(style);
        
        cell = row.createCell(4);
        cell.setCellValue("In");
        cell.setCellStyle(style);
        
        cell = row.createCell(5);
        cell.setCellValue("Out");
        cell.setCellStyle(style);
        
        cell = row.createCell(6);
        cell.setCellValue("Waste");
        cell.setCellStyle(style);
        
        cell = row.createCell(7);
        cell.setCellValue("Adjust In");
        cell.setCellStyle(style);
        
        cell = row.createCell(8);
        cell.setCellValue("Adjust Out");
        cell.setCellStyle(style);
        
        cell = row.createCell(9);
        cell.setCellValue("Closing");
        cell.setCellStyle(style);
        
        
        row.setHeight((short) 1000);
        
        int rownum = 1;
        int no = 0;
        for (ReportStockBalanceDto  dto : dtoList.getItems())
        {
            	no = no+1;
                row = sheet.createRow(rownum++);
                row.setHeightInPoints(22);
              
                cell = row.createCell(0);
                cell.setCellValue(no);
                cell.setCellStyle(cs);
                
                cell = row.createCell(1);
                cell.setCellValue(dto.getItemDto().getItemName());
                cell.setCellStyle(csleft);
                
                cell = row.createCell(2);
                cell.setCellValue(dto.getItemDto().getUnitDto().getUnitName());
                cell.setCellStyle(csleft);
                
                cell = row.createCell(3);
                cell.setCellValue(dto.getOpening());
                cell.setCellStyle(csrightPadded);
                
                cell = row.createCell(4);
                cell.setCellValue(dto.getStockIn());
                cell.setCellStyle(csrightPadded);
                
                cell = row.createCell(5);
                cell.setCellValue(dto.getStockOut());
                cell.setCellStyle(csrightPadded); 
                
                cell = row.createCell(6);
                cell.setCellValue(dto.getWaste());
                cell.setCellStyle(csrightPadded);
                
                cell = row.createCell(7);
                cell.setCellValue(dto.getAdjustIn());
                cell.setCellStyle(csrightPadded);
                

                cell = row.createCell(8);
                cell.setCellValue(dto.getAdjustOut());
                cell.setCellStyle(csrightPadded);

                cell = row.createCell(9);
                cell.setCellValue(dto.getClosing());
                cell.setCellStyle(csrightPadded);
                
               
                
        }
        
        
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
            byte[] xls = out.toByteArray();
            out.close();
           HttpHeaders headers = new HttpHeaders();
           headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
           headers.setContentDisposition(ContentDisposition.parse("attachment; filename=\"+fileName"));
           return new HttpEntity<byte[]>(xls, headers);
        } catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
