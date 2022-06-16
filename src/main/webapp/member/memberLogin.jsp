<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
	<title>로그인 페이지</title>
	<meta charset="UTF-8">
	<style>
		div {
			margin-bottom: 10px;
		}
		h4 {
			margin: 0;
		}
		.id {
			margin-top: -50px;
		}
		   body{

background-image: url("../images/b1.png");
		
	
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
			} else if(fm.memberPwd.value=="") {
				alert("비밀번호가 일치하지 않습니다.")
				fm.memberPwd.focus();
				return;
			}
			
				//가상 경로를 사용해서 페이지 이동시킨다.
			//	fm.action = "./MemberJoinOk.jsp";
				fm.action = "<%=request.getContextPath()%>/member/memberLoginAction.do";
				fm.method = "post";
				fm.submit();
				
			return;
		}
	</script>
</head>
	<body>
		<h1>로그인 </h1>
	    <form name="frm" action="./inputOk.jsp" method="post">
		    
		   <hr>
		    <div>
		    	<h4>
				<label>아이디</label>
				</h4>
			    <input type="text" name="memberId" maxlength="20" class="id">
			    <br>
		    </div>
		    <div>
		    	<h4>비밀번호</h4>
			    <input type="password" name="memberPwd" maxlength="20">
			    <br>
		    </div>
		    <div>
		    	<h4></h4>
		    	<input type="button" name="btn" value="확인" onclick="check();">
		    	<input type="reset" name="btn" value="다시작성">
		    <a href="<%=request.getContextPath()%>/member/memberJoin.do"><input type="button" name="btn" value=회원가입하기 ></a>
		    
		    
		    </div>
		    
		    	   <hr>
	    </form>
	    
	  		<%out.println("<a href='" + request.getContextPath()+ "/index.jsp'> 메인으로 </a>" + "<br>" + "<br>"); %>
	</body>
</html>


















