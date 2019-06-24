<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, com.abrstech.sql.*"
%>

<%@ page errorPage="showError.jsp" %>


 
<% 

	boolean foundOk = false;
	String vin = "";
	
	String email = request.getParameter( "e" ).trim();
	String question = request.getParameter( "q" ).trim();
	String answer = request.getParameter( "a" ).trim();
	
	if( email == null || question == null || answer == null) {
		foundOk = false;
	} else {
		
		searchVehicle newSrch = new searchVehicle();
		foundOk = newSrch.isExisting( email, question, answer);
		newSrch.CloseDB();
	
		vin = newSrch.getVin().trim();
	}
	
	// save to DB
	if( !foundOk ) {
		out.println( "vin=" );		
	} else {
		out.println( "vin=" + vin );	
	} 
%>
		
