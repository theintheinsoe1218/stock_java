package com.tts.stock.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
    private static SimpleDateFormat format = null;
    public static String convertDateToString(Date date){
        if (format == null)
        format = new SimpleDateFormat("dd-MM-yyyy@HHmmss");
        String strDate = format.format(date);
        return strDate;
    }
    public static String convertDateToStringYearMonthDay(Date date){//month
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatDate.format(date);
        return strDate;
    }
    public static String convertDateToStringYearMonth(Date date){//month
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
        String strDate = formatDate.format(date);
        return strDate;
    }
    public static String convertDateToStringYear(Date date){//year
    	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
        String strDate = formatDate.format(date);
        return strDate;
    }
    public static String convertyymmddhhmmss(Date date) {//voucherId or productId or advertise 0
 	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
 		//SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
 	   String strDate = dateFormat.format(date);
 return strDate;
 }
   public static String convertStringToString(String dateStr,String type){//date,month,year
        	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        	Date currentDate;
        	String strDate = null;
			try {
				if("date".equals(type)){//day
				currentDate = formatDate.parse(dateStr);
				SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy");
					strDate = dayFormat.format(currentDate);
				}else if("month".equals(type)){
					dateStr = dateStr+"-01";
					currentDate = formatDate.parse(dateStr);
					SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM yyyy");
					strDate = monthFormat.format(currentDate);	
				}else {//year
					dateStr = dateStr+"-01-01";
					currentDate = formatDate.parse(dateStr);
					SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
					strDate = yearFormat.format(currentDate);
				}	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return strDate;
    }
   public static String convertStringToStringForServer(String dateStr,String type){

       	Date currentDate;
       	String strDate = null;
			try {
				if("date".equals(type)){//day
					SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
				currentDate = formatDate.parse(dateStr);
				SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
					strDate = dayFormat.format(currentDate);
				}else if("month".equals(type)){
					SimpleDateFormat formatDate = new SimpleDateFormat("MMMM yyyy");
					dateStr = dateStr+"-01";
					currentDate = formatDate.parse(dateStr);
					SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
					strDate = monthFormat.format(currentDate);	
				}else {//year
//					dateStr = dateStr+"-01-01";
//					currentDate = formatDate.parse(dateStr);
//					SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//					strDate = yearFormat.format(currentDate);
					strDate = dateStr;
				}	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       return strDate;
   }
public static int diffDates(Date fromDate,Date toDate) {
	// TODO Auto-generated method stub
	long diff = toDate.getTime() - fromDate.getTime();
	float days = (diff / (1000*60*60*24));//+1;
	int daysCount = (int)days;
  return daysCount;
}
public static int diffAge(Date fromDate,Date toDate) {
	// TODO Auto-generated method stub
	long diff = toDate.getTime() - fromDate.getTime();
	float days = (diff / (1000*60*60*24));//+1;
	int daysCount = (int)days;
  return daysCount/365;
}
public static Date convertStringToDate(String dateStr){//date,month,year
	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	try {
		Date date = formatDate.parse(dateStr);
		return date;
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String convertStringToDate(Date date) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		//SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
     String strDate = dateFormat.format(date);
     return strDate;
}
public static String convertHourMinuteSecondToDate(Date date) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
		//SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
  String strDate = dateFormat.format(date);
  return strDate;
}
public static String convertStringToDateHour(Date date) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH");
		//SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
  String strDate = dateFormat.format(date);
  return strDate;
}
public static String convertVoucherCode(Date date,int voucherId) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		//SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
	   String strDate = dateFormat.format(date);
return strDate+voucherId;
}
public static String createVoucherCode(Date date,int voucherId) {//voucherId or productId or advertise 0
	   SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
		//SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
	   String strDate = dateFormat.format(date);
return strDate+voucherId;
}
public static String convertDateToStringDayMonthYear(Date date){//month
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
    String strDate = formatDate.format(date);
    return strDate;
}
public static String createSaleVoucher(String staticUi,int saleId) {//voucherId or productId or advertise 0
	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
	   String strDate = dateFormat.format(new Date());
	   if("cnd".equals(staticUi)) {
		   staticUi = "MTLS";
	   } else if("k10".equals(staticUi)) {
		   staticUi = "MDYS";
	   }else {
		   staticUi = "MKNS";
	   }
return staticUi+strDate+saleId;
}

public static String createServiceVoucher(int serviceId) {
	// TODO Auto-generated method stub
	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
	   String strDate = dateFormat.format(new Date());
	  
	   return strDate+serviceId;
}
public static String createSaleVoucher(int saleId) {
	// TODO Auto-generated method stub
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
	   String strDate = dateFormat.format(new Date());
	   return "TM"+strDate+saleId;
}
public static String convertDateToTime(Date date) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		//SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
  String strDate = dateFormat.format(date);
  return strDate;
}
public static long compareTodayLimiteTime(Date startDate,Date endDate) {//minutes
	// TODO Auto-generated method stub
	long sat = startDate.getTime()-endDate.getTime();
	return sat/60000;
}
public static Date convertTimeToDate(String datetime){//date,month,year
	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dateDate = new SimpleDateFormat("yyyy-MM-dd");
	try {
		String currentDay = dateDate.format(new Date());
		String currentDayTime = currentDay+" "+datetime;
		Date date = formatDate.parse(currentDayTime);
		return date;
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String convertDayHourMinutesSecond(int minutes) {
	   String message = "";
	   int day = 0;
	   int hours = 0;
	   if(minutes>1440) {//day
		  day =  minutes/1440;
		  minutes = minutes-(1440*day);
		  message = day+" Day, ";
	   }
	   if(minutes>60) {//hours
		   hours = minutes/60;
		   minutes = minutes-(60*hours);
		   message = message+hours+" Hours, ";
	   }
	   message = message+minutes+" Minutes.";
	   return message;
}
public static String convertHourMinuteAmPmToDate(Date date) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		//SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
String strDate = dateFormat.format(date);
return strDate;
}

public static String convertDateToStringDayMonthYearMMM(Date date){//month
    SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yyyy");
    String strDate = formatDate.format(date);
    return strDate;
}
}
