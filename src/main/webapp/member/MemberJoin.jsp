<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
	<title>input test</title>
	<meta charset="UTF-8">
	<style>
	
			   


	body{
text-align: center;
background-image: url("../images/b1.png");
        }
		div {
			margin-bottom: 10px;
		}
		h4 {
			margin: 0;
		}
		.id {
			margin-top: -50px;
		}
	</style>
	<script>
		function check() {
//			alert("테스트");
			
			var fm = document.frm;
			//frm은 form을 뜻한다.
			
			if (fm.memberId.value=="") {
				alert("아이디를 입력하세요")
				document.frm.memberId.focus();
				return;
			} else if(fm.memberPwd.value=="") {
				alert("비밀번호를 입력하세요")
				fm.memberPwd.focus();
				return;
			} else if(fm.memberPwd2.value=="") {
				alert("비밀번호를 확인을 입력하세요")
				fm.memberPwd2.focus();
				return;
			} else if(fm.memberPwd.value=="") {
				alert("비밀번호가 일치하지 않습니다.")
				fm.memberPwd.focus();
				return;
			} else if(fm.memberName.value=="") {
				alert("이름을 입력하세요")
				fm.memberName.focus();
				return;
			} else if(fm.memberEmail.value=="") {
				alert("이메일을 입력하세요")
				fm.memberEmail.focus();
				return;
			} else if(fm.memberPhone.value=="") {
				alert("연락처를 입력하세요")
				fm.memberPhone.focus();
				return;
			} else if(fm.memberJumin.value=="") {
				alert("주민번호를 입력하세요")
				fm.memberJumin.focus();
				return;
			} else if(fm.memberAddr.value=="") {
				alert("주소를 입력하세요")
				fm.memberAddr.focus();
				return;
			} else {
				
				var chkYn = false; //기본값
				for(var i=0; i<fm.memberHobby.length; i++){
					if(fm.memberHobby[i].checked == true) { //체크가 되어 있다면
						chkYn = true;
						break;
					}
				}
				if (chkYn == false) {
					alert("취미를 한개이상 선택해주세요")
					return;
				}
				
			}
			
				//가상 경로를 사용해서 페이지 이동시킨다.
			//	fm.action = "./MemberJoinOk.jsp";
				fm.action = "<%=request.getContextPath()%>/member/memberJoinAction.do";
				fm.method = "post";
				fm.submit();
				
			return;
			
			
		}
	</script>
</head>
	<body>
		<h1>회원가입</h1>
		<hr>
	    <form name="frm" action="./inputOk.jsp" method="post">
		    <div>
		    	<h4>
				<label>아이디</label>
				</h4>
			    <input type="text" name="memberId" maxlength="20" class="id">
			    <br>
		    </div>
		    <div>
		    	<h4>이름</h4>
		    	<input type="text" name="memberName" maxlength="20">
			    <br>
		    </div>
		    <div>
		    	<h4>비밀번호</h4>
			    <input type="password" name="memberPwd" maxlength="20">
			    <br>
		    </div>
		    <div>
		    	<h4>비밀번호 재확인</h4>
		    	<input type="password" name="memberPwd2" maxlength="20">
			    <br>
		    </div>
		    <div>
		    	<h4>이메일</h4>
		    	<input type="email" name="memberEmail" placeholder="test@naver.com" maxlength="20">
			    <br>
		    </div>
		    <div>
		    	<h4>성별</h4>
			   	<select name="memberGender">
				    <option value="M" >M</option>
				    <option value="F" >F</option>
				</select>
		    </div>
		    <div>
		    	<h4>주민번호</h4>
		    	<input type="text" name="memberJumin" placeholder="예)123456-7890123" maxlength="20">
		    </div>
		    <div>
		    	<h4>주소</h4>
		    	<input type="text" name="memberAddr" placeholder="예) 평화아파트 101동 305호">
		    </div>
		    <div>
		    	<h4>전화번호</h4>
		    	<input type="text" name="memberPhone" placeholder="예)010-1111-2222">
		    </div>
		    <div>
		    	<h4>취미</h4>
		    	<input type="checkbox" name="memberHobby" value="야구">야구
		    	<input type="checkbox" name="memberHobby" value="축구">축구
		    	<input type="checkbox" name="memberHobby" value="농구">농구
		    </div>
		    <div>
		    	<h4>버튼</h4>
		    	<input type="button" name="btn" value="확인" onclick="check();">
		    	<input type="reset" name="btn" value="다시작성">
		    </div>
	    </form>
	    
	    <%
	    out.println("<a href='" + request.getContextPath()+ "/index.jsp'> 메인으로 </a>" + "<br>" + "<br>");%>
	    <hr>
	</body>
</html>


















