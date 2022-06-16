<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" %>
<%@page import = "jspstudy.domain.*" %>
<%
	ArrayList<BoardVo> alist = (ArrayList<BoardVo>) request.getAttribute("alist");
	PageMaker pm = (PageMaker)request.getAttribute("pm");
%>

<%
	BoardVo bv = (BoardVo)request.getAttribute("bv");

	if (session.getAttribute("midx") == null) {
		session.setAttribute("saveUrl", request.getRequestURI());
		
		out.println("<script>alert('로그인하고 다시 시도해주세요.'); location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
		
			body{

background-image: url("../images/b1.png");
			table {
				border-collapse: collapse;
				margin-bottom: 50px;
				text-align:center;
				
				
				
			}
		</style>
		<script>
			
			 
		</script>
	</head>
	<body>
	<%
		if (session.getAttribute("midx") != null) {
			out.println("회원 아이디: " + session.getAttribute("memberId") + "<br>" + "<br>");
			out.println("회원 이름: " + session.getAttribute("memberName") + "<br>" + "<br>");
			
			out.println("<a href='" + request.getContextPath()+ "/index.jsp'>메인으로 </a>" + "<br>" + "<br>");
			out.println("<a href='" + request.getContextPath()+ "/member/memberLogout.do'> 로그아웃 </a>");
		}
	%>
	
		<h3>마이페이지 </h3>
		<hr>
		
	<img src = "../images/b2.png"> 
	
				</td>
			</tr>
		</table>
	</body>
</html>