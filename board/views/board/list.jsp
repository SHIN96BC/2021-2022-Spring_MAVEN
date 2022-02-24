<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title> Spring Board </title>
	<meta charset="utf-8">
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
</head>
<body>
<center>
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3> Spring Board ( Spring5 + MyBatis )</h3>
<font color='gray' size='4' face='휴먼편지체'>
<a href='index.do'>인덱스</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='write.do'>글쓰기</a><br/>
</font>
<hr width='600' size='2' color='gray' noshade>

<TABLE border='2' width='600' align='center' noshade>
<TR size='2' align='center' noshade bgcolor='AliceBlue'>
	<th bgcolor='AliceBlue'>no</th>
	<th color='gray'>writer</th>
	<th color='gray'>e-mail</th>
	<th color='gray'>subject</th>
	<th color='gray'>date</th>
</TR>
<c:if test="${empty boardList}">
	<tr align="center" noshade>
	   <td colspan="5">데이터가 하나도 없음</td>
	</tr>
</c:if>
<c:forEach items="${boardList}" var="board">
	<TR align='center' noshade>
		<TD>${board.seq}</TD>
		<TD>${board.writer}</TD>
		<TD>${board.email}</TD>
	    <TD>
	      <a href="content.do?seq=${board.seq}">
		    ${board.subject}
		  </a>
		</TD>
		<TD>${board.rdate}</TD>
	   </TR> 
</c:forEach>      
</TABLE>

<c:choose>
	<c:when test="${empty serachResult}">
		<hr width='600' size='2' color='gray' noshade>
		<font color='gray' size='3' face='휴먼편지체'>
	    (총페이지수 : ${listResult.totalpagecount})
	    &nbsp;&nbsp;&nbsp;
	    <c:if test="${listResult.cp > 1}">
	    	<a id="back" style="display:inline-block" href="boardList.do?cp=${listResult.cp - 1}&ps=${listResult.ps}">◀이전</a>
	    </c:if>
	    |
	    <c:forEach begin="${listResult.nowminpage}" end="${listResult.nowmaxpage}" var="i">
	        <c:if test="${not doneLoop}">
	        <c:set var="doneLoop" value="false"/>
		        <c:if test="${i >= listResult.totalpagecount}">
		        	<c:set var="doneLoop" value="true"/>
		        </c:if>
		        <a href="boardList.do?cp=${i}&ps=${listResult.ps}">
		   			<c:choose>
		   			    <c:when test="${i==listResult.cp}">
		                	<strong>${i}</strong>
		                </c:when>
		                <c:otherwise>
		                    ${i}
		                </c:otherwise>
					</c:choose>
		    	</a>
		    	<c:if test="${i < listResult.totalpagecount and i < listResult.nowmaxpage}">
		        	&nbsp;
		        </c:if>
	    	</c:if>
	    </c:forEach>
	    |
	    <c:if test="${listResult.cp < listResult.totalpagecount}">
	    	<a id="front" style="display:inline-block" href="boardList.do?cp=${listResult.cp + 1}&ps=${listResult.ps}">이후▶</a>
	    </c:if>
	    ( TOTAL : ${listResult.totalcount} )
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	       페이지 싸이즈 : 
	    <select id="psId" name="ps" onchange="f(this)">
	    	<c:choose>
	    		<c:when test="${listResult.ps == 3}">
	    		   <option value="3" selected>3</option>
			       <option value="5">5</option>
			       <option value="10">10</option>
	    		</c:when>
	    		<c:when test="${listResult.ps == 5}">
	    		   <option value="3">3</option>
			       <option value="5" selected>5</option>
			       <option value="10">10</option>
	    		</c:when>
	    		<c:when test="${listResult.ps == 10}">
	    		   <option value="3">3</option>
			       <option value="5">5</option>
			       <option value="10" selected>10</option>
	    		</c:when>
	    	</c:choose>
	    </select>
	</c:when>
	
	<c:otherwise>
		<hr width='600' size='2' color='gray' noshade>
		<font color='gray' size='3' face='휴먼편지체'>
	    (총페이지수 : ${serachResult.totalpagecount})
	    &nbsp;&nbsp;&nbsp;
	    <c:if test="${serachResult.cp > 1}">
	    	<a id="back" style="display:inline-block" href="serach.do?cp=${serachResult.cp - 1}&ps=${serachResult.ps}&kategorie=${serachResult.columname}&keyword=${serachResult.likekey}">◀이전</a>
	    </c:if>
	    |
	    <c:forEach begin="${serachResult.nowminpage}" end="${serachResult.nowmaxpage}" var="i">
	        <c:if test="${not doneLoop}">
	        <c:set var="doneLoop" value="false"/>
		        <c:if test="${i >= serachResult.totalpagecount}">
		        	<c:set var="doneLoop" value="true"/>
		        </c:if>
		        <a href="serach.do?cp=${i}&ps=${serachResult.ps}&kategorie=${serachResult.columname}&keyword=${serachResult.likekey}">
		   			<c:choose>
		   			    <c:when test="${i==serachResult.cp}">
		                	<strong>${i}</strong>
		                </c:when>
		                <c:otherwise>
		                    ${i}
		                </c:otherwise>
					</c:choose>
		    	</a>
		    	<c:if test="${i < serachResult.totalpagecount and i < serachResult.nowmaxpage}">
		        	&nbsp;
		        </c:if>
	    	</c:if>
	    </c:forEach>
	    |
	    <c:if test="${serachResult.cp < serachResult.totalpagecount}">
	    	<a id="front" style="display:inline-block" href="serach.do?cp=${serachResult.cp + 1}&ps=${serachResult.ps}&kategorie=${serachResult.columname}&keyword=${serachResult.likekey}">이후▶</a>
	    </c:if>
	    ( TOTAL : ${serachResult.totalcount} )
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	       페이지 싸이즈 : 
	    <select id="psId" name="ps" onchange="f(this)">
	    	<c:choose>
	    		<c:when test="${serachResult.ps == 3}">
	    		   <option value="3" selected>3</option>
			       <option value="5">5</option>
			       <option value="10">10</option>
	    		</c:when>
	    		<c:when test="${serachResult.ps == 5}">
	    		   <option value="3">3</option>
			       <option value="5" selected>5</option>
			       <option value="10">10</option>
	    		</c:when>
	    		<c:when test="${serachResult.ps == 10}">
	    		   <option value="3">3</option>
			       <option value="5">5</option>
			       <option value="10" selected>10</option>
	    		</c:when>
	    	</c:choose>
	    </select>
	</c:otherwise>
</c:choose>

    
   
    
    
    
    <script language="javascript">
       function f(select){
           //var el = document.getElementById("psId");
           var ps = select.value;
           //alert("ps : " + ps);
           if(!${serachResult}){
           	  location.href="boardList.do?ps="+ps+"&cp=1";
       	   }else{
       		  location.href="serach.do.do?ps="+ps+"&cp=1&kategorie="+${serachResult.columname}+"&keyword="+${serachResult.likekey};
       	   }
       }
    </script>
    
</font>
<hr width='600' size='2' color='gray' noshade>
  
    <form name="input" method="post" action="serach.do">
      <select name="kategorie">
        <option value="subject">제목</option>
        <option value="writer">글쓴이</option>
        <option value="content">내용</option>
      </select>
      <c:if test="${serachResult ne null}">
      	<input type="hidden" name="ps" value="${serachResult.ps}"></input>
      	<input type="hidden" name="cp" value="${serachResult.cp}"></input>
      </c:if>
      <input type="text" name="keyword" /> <button>검색</button>
    </form>  

</center>
</body>
</html>