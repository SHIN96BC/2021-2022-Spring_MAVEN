<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Sp03 Index</title>
	</head>
	<body style="text-align:center">
		<h1>
			Sp03 for Spring MVC Controller
		</h1>
			
		<P>
			<a href="address/list.do">주소록</a><br/><br/>
			<a href="board/boardList.do">게시판</a><br/><br/>
			<a href="file/list.do">파일폼</a><br/><br/>
			
			<a href="ajax/test01.do">ajax01</a><br/><br/>
			<a href="ajax/test02.do">ajax02</a><br/><br/>
			<a href="ajax/test03.do">ajax03</a><br/><br/>
			<a href="ajax/test04.do">ajax04</a><br/><br/>
			
			<h3>Ajax 응용</h3>
			<a href="file/form_dd.do">drag_drop</a><br/><br/>
			<a href="auto/auto.do">address 자동완성</a><br/><br/>
			<a href="chart/chart.do">address 차트</a><br/><br/>
			
			<h3>RestFul(Address) 구축</h3>
			<a href="rest_addr/write.do">rest_addr/write.do</a><br/><br/>
		</P>
	</body>
</html>
