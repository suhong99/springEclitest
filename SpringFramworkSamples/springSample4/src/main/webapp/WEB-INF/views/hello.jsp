<%@page import="ssg.com.a.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello.jsp</title>
</head>
<body>
<%
	List<MemberDto> list = (List<MemberDto>)request.getAttribute("list");
%>

<h2>Hello</h2>
<table border="1">
<tr>
	<th>번호</th><th>아이디</th><th>패스워드</th><th>이름</th><th>이메일</th>
</tr>
<%
if(list != null && list.size() > 0){
	for(int i = 0;i < list.size(); i++){
		MemberDto dto = list.get(i);
		%>
		<tr>
			<th><%=i+1 %></th>
			<td><%=dto.getId() %></td>
			<td><%=dto.getPwd() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getEmail() %></td>
		</tr>
		<%
	}
}
%>
</table>
<br>
<hr>
<br>

<form action="find.do">
id:<input type="text" name="id" size="20">
<input type="submit" value="회원정보찾기">
</form>

<%
	MemberDto mem = (MemberDto)request.getAttribute("mem");
	if(mem != null){
		%>
			회원정보:<input type="text" value="<%=mem.getName() %>">
		<%
	}
%>

</body>
</html>








