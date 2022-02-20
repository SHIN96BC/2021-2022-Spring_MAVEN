<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList, sbc.addr.domain.Address"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<style>
			table, th, td {
			   border: 1px solid black;
			   border-collapse: collapse;
			}
			th, td {
			   padding: 5px;
			}
			a { text-decoration:none }
		</style>
		<title>Insert title here</title>
	</head>
	<body>
		
		<center>
			<h1>
				Address List MVC
			</h1>
			<a href="/">인덱스</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="input.do">입력폼</a>
			<table border='1' cellpadding='7' cellspacing='2' width='50%'>
			    <tr>
				    <th>번호</th>
					<th>이름</th>
					<th>주소</th>
					<th>날짜</th>
					<th>삭제</th>
				</tr>
		<%	
			ArrayList<Address> addressList = (ArrayList<Address>)request.getAttribute("addressList");
			if(addressList != null){
				if(addressList.size() != 0){
					for(Address dto: addressList){
		%>
						<tr>
							<td align='center'><%=dto.getSeq()%></td>
							<td><%=dto.getName()%></td>
							<td><%=dto.getAddr()%></td>
							<td><%=dto.getRdate()%></td>
							<td align='center'><a href='delete.do?seq=<%=dto.getSeq()%>'>삭제</a></td>
						</tr>
		<%
					}
				}else{
		%>
					<tr>
						<td colspan="5" style="text-align:center">데이터가 하나도 없어요</td>
					</tr>
		<%
				}
			}
		%>
				
			</table>
		</center>
	</body>
</html>