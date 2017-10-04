<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% response.setHeader("Cache-Control", "no-cache"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="Pragma" content="no-cache">
    	<meta http-equiv="Cache-Control" content="no-cache">
    	<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
		<%@ page isELIgnored="false"%>
		
		<spring:url value="/resources/resources/css/ext-all.css" var="mainCss" />
		<spring:url value="/resources/ext-all.js" var="extJs" />
		<spring:url value="/images/delete.png" var="deleteIcon" />
		<spring:url value="/images/view-details.png" var="detailsIcon" />
		<spring:url value="/images/plus.png" var="plusIcon" />
		<spring:url value="/js/app.js" var="appJs" />
		<style type="text/css">
			.icon-delete {
				background-image: url(${deleteIcon}) !important;
			}
			
			.icon-details {
				background-image: url(${detailsIcon}) !important;
			}
			.icon-plus {
				background-image: url(${plusIcon}) !important;
			}
		</style>
		<link href="${mainCss}" rel="stylesheet" />
		<script src="${extJs}"></script>
		<script src="${appJs}"></script>
		<title>Listagem de Clientes</title>
	
	</head>
	<body>
		<c:url var="logoutUrl" value="/customer/logout"/>
		<form id="dynForm" action="${logoutUrl}" method="post">
    		<!-- <input type="submit" value="Logout"/> -->
    	</form>
	</body>
</html>