<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8"/>
		<title>Ajax Test03</title>
		<script type="text/javascript" language="javascript" 
		     src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
		<script type="text/javascript">
			/*$(document).ready(function(){
			});*/
			$(function() {
				$("#seq").on("keyup", function(){
					$.ajax({
						url: "../ajax04/search01.json",
						type: "GET",
						data: {seq: $("#seq").val()},
						success: function(data){
							//let jsObj = JSON.parse(data); // json -> js
							//let json = JSON.stringfy(jsObj) // js -> json
							console.log("#data name: " + data.name);
							console.log("#data: " + data);
							if(!data){
								alert("존재하지 않는 SEQ");
								return false;
							}
							
							let html = "";
							html += "<form id='ajax'>";
							html += "번호 <input name='seq' value='"+data.seq+"'/>";
							html += "이름 <input name='name' value='"+data.name+"'/>";
							html += "주소 <input name='addr' value='"+data.addr+"'/>";
							html += "날짜 <input name='rdate' value='"+data.rdate+"'/>";
							html += "</form>";
							
							$("#name").val("");
							$("#container").html(html);
						}
					});
				});
				
				$("#searchOk02").on("click", function(){
					$.ajax({
						url: "../ajax04/search02.json",
						type: "POST",
						data: {name: $("#name").val()},
						success: function(data){
							//let jsObj = JSON.parse(data); // json -> js
							//let json = JSON.stringfy(jsObj) // js -> json
							console.log("#data: " + data.name);
							if(!data){
								alert("존재하지 않는 NAME");
								return false;
							}
							
							let html = "";
							html += "<table border='1' width='50%'>";
							html += "<tr>";
							html += "<th>번호</th>";
							html += "<th>이름</th>";
							html += "<th>주소</th>";
							html += "<th>날짜</th>";
							html += "</tr>";
							
							if(data.length === 0){
								html += "<tr>";
								html += "<td colspan='4' align='center'>그런 이름을 가진 멤버는 없음</td>";
								html += "</tr>";
							}else{
								for(let address of data){
									html += "<tr>";
									html += "<th align='center'>"+address.seq+"</th>";
									html += "<th align='center'>"+address.name+"</th>";
									html += "<th align='center'>"+address.addr+"</th>";
									html += "<th align='center'>"+address.rdate+"</th>";
									html += "</tr>";
								}
							}
							html += "</table>";
							
							$("#seq").val("");
							$("#container").html(html);
						}
					});
				});
			});
		</script>
	</head>
	<body>
	    <center>
	    <h2>(방법4) @RestController</h2>
	    
	    번호: <input type="text" name="seq" id="seq"/>
	    <input type="button" value="번호 검색" id="searchOk01"/>
	    <br/>
	    
	    이름: <input type="text" name="name" id="name"/>
	    <input type="button" value="이름 검색" id="searchOk02"/>
	 
        <br/><br/>
		<div id="container"></div>
		<br/><br/>
		
		<a href="../">인덱스</a><br/><br/>
		</center>
	</body>
</html>