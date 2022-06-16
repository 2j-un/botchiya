<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import = "jspstudy.domain.BoardVo" %>
    
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
		<title>1화</title>
			<script>
				function check() {
				
					var fm = document.frm;
				
					if (fm.subject.value == ""){
						alert("제목을 입력해주세요.");
						fm.subject.focus();
						return;
					} else if (fm.content.value == ""){
						alert("내용을 입력해주세요");
						fm.content.focus();
						return;
					} else if (fm.writer.value == ""){
						alert("작성자를 입력해주세요");
						fm.writer.focus();
						return;
					} 
					
					fm.action = "<%=request.getContextPath()%>/board/boardWriteAction.do";
					fm.method = "post";
					fm.enctype= "multipart/form-data";
					fm.submit();
					
					return;
				}
			</script>
		<style>
			  body{
text-align: center;
        }
        img{
         

        }
        h1{
            color: rgb(4, 4, 49);
        }

        button{ 
width: 100;
            height: 30px;
            background-color: rgb(93, 190, 228);
        }
        </style>
		
	</head>
		<body>
			<%
		if (session.getAttribute("midx") != null) {
			out.println("회원 아이디: " + session.getAttribute("memberId") + "<br>" + "<br>");
			out.println("회원 이름: " + session.getAttribute("memberName") + "<br>" + "<br>");
			
			out.println("<a href='" + request.getContextPath()+ "/index.jsp'> 메인으로 </a>" + "<br>" + "<br>");
			out.println("<a href='" + request.getContextPath()+ "/member/memberLogout.do'> 로그아웃 </a>");
		}
	%>
			<h1>1화</h1>
			<hr>
		<img src = "../images/dan.jpg">
		
		<%out.println("<a href='" + request.getContextPath()+ "/index.jsp'> 메인으로 </a>" + "<br>" + "<br>"); %>
		</body>
		
		
</html>