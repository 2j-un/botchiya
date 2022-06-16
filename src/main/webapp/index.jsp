<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" %>
<%@page import = "jspstudy.domain.*" %>
<%
	ArrayList<BoardVo> alist = (ArrayList<BoardVo>) request.getAttribute("alist");
	PageMaker pm = (PageMaker)request.getAttribute("pm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
   body{

background-image: url("images/b1.png");


</style>
		
<title>이젠 툰</title>
	
</head>
<body>

	<h1>이젠 툰</h1>
	<%
		if (session.getAttribute("midx") != null) {
			out.println("회원 아이디: " + session.getAttribute("memberId") + "<br>" + "<br>");
			out.println("회원 이름: " + session.getAttribute("memberName") + "<br>" + "<br>");
			
			out.println("<a href='" + request.getContextPath()+ "/index.jsp'> 메인으로 </a>" + "<br>" + "<br>");
			out.println("<a href='" + request.getContextPath()+ "/member/memberLogout.do'> 로그아웃 </a>");
		}
	%>
	<hr>
	
	<a href="<%=request.getContextPath()%>/member/memberJoin.do">회원가입하기</a> 
	<a href="<%=request.getContextPath()%>/member/memberLogin.do">로그인</a>
	<a href="<%=request.getContextPath()%>/board/boardWrite.do">웹툰올리기</a>
	<a href="<%=request.getContextPath()%>/board/boardList.do">마이페이지</a>
	<hr>
	
<img src = "images/ee.png" width="590" height="200" alt="이젠 연놈">
	
	
	<br>
	<h4>웹툰을 자유롭게 보고 올릴 수 있는 사이트"</h4>
	
	
	
	<br>
	<hr>
	<a href="<%=request.getContextPath()%>/board/boardWrite.jsp"><h3>1화-이젠 교육</h3></a>
	<a href="<%=request.getContextPath()%>/board/boardWrite.jsp"> <img src = "images/ww.jpg" width="150" height="150" alt="이젠 연놈"> </a>
	
	<br>
	
	
	<hr>					
					
				</div>
				<hr class="main_bar" id="main_bar_bottom" style="margin-top:50px;">
				<div id="main_last">
					<span class="main_bottom_ment"></span>
					<span class="main_bottom_ment">
						<span class="main_bottom_tagset">웹툰문의(1234-1234)</span>
						<a href="#" class="main_bottom_tagset">이젠 툰 매제휴&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<a href="#" class="main_bottom_tagset">웹툰가이드&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</span>
				</div>
				<hr class="main_bar" id="main_bar_bottom">
			</article>
		</section>
		<footer>
			<div class="f_title_inner">
				<span id="f_menu_produce">
					<ul id="f_menu_produce">
						<li><a href="#" style="font-weight:bold;">(주)이젠 툰</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">전북 전주시 덕진구 백제대로 572 5층</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">대표이사:이정운</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">사업자등록번호: 127-93-11111&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						<li><a href="#">통신판매업 신고번호: 제1111-이젠이젠-1111호 <a href="#" style="font-weight:bold;">&nbsp;&nbsp;사업자정보확인>&nbsp;&nbsp;</a></a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
						<li><a href="#">고객센터(평일/주말 09:00~18:00): 1234-1234(유료)</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">호스팅제공자: (주)이젠 툰</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">&copy;ejen toon Corp. All rights reserved.</a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
					</ul>
				</span>
			</div>
		</footer>
	
	
	
	</body>

</html>