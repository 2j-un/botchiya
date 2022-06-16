<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String bidx = (String)request.getAttribute("bidx"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판 글삭제</title>
		<script src="https://kit.fontawesome.com/ec2ef67e6c.js" crossorigin="anonymous"></script>
			<script>
				function check() {
				
					var fm = document.frm;
				
					if (fm.bidx == ""){
						alert("bidx가 없습니다.");
						fm.bidx.focus();
						return;
					}
					
					fm.action = "<%=request.getContextPath()%>/board/boardDeleteAction.do";
					fm.method = "post";
					fm.submit();
				}
			</script>
		<style>
			h1 {
				text-align: center;
				margin-top: 50px;
				margin-bottom: 50px;
			}
			
			table {
				border: 1px solid #cdcdcdcd;
				border-radius: 10px;
				margin: 0 auto;
				width: 500px;
				height: 100px;
			}
			
			td {
				text-align: center;
				width: 100px;
			}
			
		</style>
	</head>
		<body>
			<h1>게시판 글삭제</h1>
			
			<table>
				<form name="frm">
				<input type="hidden" name="bidx" value="<%=bidx%>">
				<tr>
					<td colspan=2> <i class="fa-solid fa-circle-exclamation" title="다시 한번 생각해 보세요."></i> 삭제하시겠습니까?</td>
				</tr>
				<tr>
					<td colspan=2>
						<input type="button" name="btn" value="확인" onclick="check();">
						<input type="button" name="concel" value="취소" onclick="location.href='<%=request.getContextPath() %>/board/boardContent.do?bidx=<%=bidx%>'">
					</td>
				</tr>
			</table>
		</body>
</html>