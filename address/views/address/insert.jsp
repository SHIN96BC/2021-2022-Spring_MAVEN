<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%		
		boolean flag = (Boolean)request.getAttribute("flag");
	
	%>
	<script>
		if(<%=flag%>){
			alert("입력 성공(mv)");
		}else{
			alert("입력 실패(mv)");
		}
		location.href='list.do';
	</script>
</body>
</html>